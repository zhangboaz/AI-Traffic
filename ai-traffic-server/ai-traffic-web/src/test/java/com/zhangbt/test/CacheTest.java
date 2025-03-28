package com.zhangbt.test;

import com.zhangbt.common.utils.CustomCache;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

@SpringBootTest
@AllArgsConstructor
public class CacheTest {

    @Autowired
    CustomCache<String, Object> customCache;

    @Test
    public void test() throws InterruptedException {
        // 创建缓存实例，最大容量为 3
        // 存入数据，设置过期时间为 5 秒
        customCache.put("key1", "value1", 5000);
        customCache.put("key2", "value2", 5000);
        customCache.put("key3", "value3", 5000);

        // 等待 6 秒，确保缓存数据过期
        Thread.sleep(6000);

        // 访问缓存，检查过期情况
        System.out.println("key1: " + customCache.get("key1"));  // null，已过期
        System.out.println("key2: " + customCache.get("key2"));  // null，已过期
        System.out.println("key3: " + customCache.get("key3"));  // null，已过期

        // 添加新数据，容量已满，触发 LRU 清理
        customCache.put("key4", "value4", 5000);
        customCache.put("key5", "value5", 5000);

        // 输出缓存大小
        System.out.println("Cache size: " + customCache.size()); // 输出缓存的当前大小

        // 清空缓存
        customCache.clear();
        System.out.println("Cache size after clearing: " + customCache.size()); // 输出清空后的缓存大小
    }

}
