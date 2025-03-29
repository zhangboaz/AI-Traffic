package com.zhangbt.config;

import com.zhangbt.interceptor.JwtTokenInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configurable
@Slf4j
@AllArgsConstructor
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private final JwtTokenInterceptor jwtTokenInterceptor;

    /**
     *注册自定义拦截器
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");

        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login");
    }

}
