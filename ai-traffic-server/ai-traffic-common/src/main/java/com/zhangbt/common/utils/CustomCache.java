package com.zhangbt.common.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// 定义缓存数据结构
class CacheEntry<V> {

    V value;
    long expiryTime;

    public CacheEntry(V value, long expiryTime) {

        this.value = value;
        this.expiryTime = expiryTime;
    }

    public boolean isExpired() {

        return System.currentTimeMillis() > expiryTime;
    }
}

// 自定义缓存类
public class CustomCache<K, V> {


    // 缓存存储数据的 Map
    private final Map<K, CacheEntry<V>> cacheMap;
    // 锁对象，确保线程安全
    private final ReentrantLock lock;
    // 最大缓存容量
    private final int maxCapacity;
    // 定时清理过期缓存的线程池
    private final ScheduledExecutorService cleanupExecutor;

    // 构造方法，初始化缓存容量和定时任务
    public CustomCache(int maxCapacity) {

        this.cacheMap = new LinkedHashMap<>();
        this.lock = new ReentrantLock();
        this.maxCapacity = maxCapacity;
        this.cleanupExecutor = Executors.newSingleThreadScheduledExecutor();

        // 定期清理过期缓存，每分钟执行一次
        this.cleanupExecutor.scheduleAtFixedRate(this::cleanExpiredCache, 1, 1, TimeUnit.MINUTES);
    }

    // 存储数据到缓存中，设置过期时间（单位：毫秒）
    public void put(K key, V value, long expiryTimeMillis) {

        lock.lock();
        try {

            if (cacheMap.size() >= maxCapacity) {

                removeLeastRecentlyUsed();
            }

            long expiryTime = System.currentTimeMillis() + expiryTimeMillis;
            CacheEntry<V> cacheEntry = new CacheEntry<>(value, expiryTime);
            cacheMap.put(key, cacheEntry);
        } finally {

            lock.unlock();
        }
    }

    // 获取缓存中的数据，若数据不存在或过期则返回 null
    public V get(K key) {

        lock.lock();
        try {

            CacheEntry<V> entry = cacheMap.get(key);
            if (entry == null || entry.isExpired()) {

                cacheMap.remove(key); // 清除过期或不存在的数据
                return null;
            }
            return entry.value;
        } finally {

            lock.unlock();
        }
    }

    // 清除缓存中所有过期的数据
    private void cleanExpiredCache() {

        lock.lock();
        try {

            Iterator<Map.Entry<K, CacheEntry<V>>> iterator = cacheMap.entrySet().iterator();
            while (iterator.hasNext()) {

                Map.Entry<K, CacheEntry<V>> entry = iterator.next();
                if (entry.getValue().isExpired()) {

                    iterator.remove();
                }
            }
        } finally {

            lock.unlock();
        }
    }

    // 移除最少使用的缓存项（LRU 算法）
    private void removeLeastRecentlyUsed() {

        long leastUsedTime = Long.MAX_VALUE;
        K leastUsedKey = null;

        for (Map.Entry<K, CacheEntry<V>> entry : cacheMap.entrySet()) {

            long lastAccessTime = entry.getValue().expiryTime;  // 通过过期时间模拟最不常用的数据
            if (lastAccessTime < leastUsedTime) {

                leastUsedTime = lastAccessTime;
                leastUsedKey = entry.getKey();
            }
        }

        if (leastUsedKey != null) {

            cacheMap.remove(leastUsedKey);
        }
    }

    // 获取缓存当前大小
    public int size() {

        return cacheMap.size();
    }

    // 清空缓存
    public void clear() {

        lock.lock();
        try {

            cacheMap.clear();
        } finally {

            lock.unlock();
        }
    }

    // 关闭定时清理任务
    public void shutdown() {

        cleanupExecutor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {

        // 创建缓存实例，最大容量为 3
        CustomCache<String, String> cache = new CustomCache<>(3);

        // 存入数据，设置过期时间为 5 秒
        cache.put("key1", "value1", 5000);
        cache.put("key2", "value2", 5000);
        cache.put("key3", "value3", 5000);

        // 等待 6 秒，确保缓存数据过期
        Thread.sleep(6000);

        // 访问缓存，检查过期情况
        System.out.println("key1: " + cache.get("key1"));  // null，已过期
        System.out.println("key2: " + cache.get("key2"));  // null，已过期
        System.out.println("key3: " + cache.get("key3"));  // null，已过期

        // 添加新数据，容量已满，触发 LRU 清理
        cache.put("key4", "value4", 5000);
        cache.put("key5", "value5", 5000);

        // 输出缓存大小
        System.out.println("Cache size: " + cache.size()); // 输出缓存的当前大小

        // 清空缓存
        cache.clear();
        System.out.println("Cache size after clearing: " + cache.size()); // 输出清空后的缓存大小
    }
}
