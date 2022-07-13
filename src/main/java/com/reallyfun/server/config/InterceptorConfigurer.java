package com.reallyfun.server.config;

import com.reallyfun.server.interceptor.AuthInterceptor;
import com.reallyfun.server.interceptor.ExampleInterceptor;
import com.reallyfun.server.interceptor.LoginInterceptor;
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
public class InterceptorConfigurer implements WebMvcConfigurer {
    // 拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建并注册拦截器
//        new ExampleInterceptor().register(registry);
        new LoginInterceptor().register(registry);
        new AuthInterceptor().register(registry);
    }
}