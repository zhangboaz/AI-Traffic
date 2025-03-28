package com.zhangbt.config;

import com.zhangbt.common.utils.CustomCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CacheConfig {
    @Bean
    public CustomCache<String, Object> customCache() {
        log.info("配置CustomCache");
        return new CustomCache<>(3);
    }
}
