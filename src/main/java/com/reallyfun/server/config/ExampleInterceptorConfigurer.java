package com.reallyfun.server.config;

import com.reallyfun.server.interceptor.ExampleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册处理器拦截器
 */
@Configuration
public class ExampleInterceptorConfigurer implements WebMvcConfigurer {
    // 拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器对象
        HandlerInterceptor interceptor = new ExampleInterceptor();
        // ⽩名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/example/*");

        // 通过注册⼯具添加拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}