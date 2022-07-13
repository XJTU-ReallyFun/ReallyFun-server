package com.reallyfun.server.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义处理器拦截器
 */
public class ExampleInterceptor implements HandlerInterceptor {
    // 该⽅法将在请求处理之前被调⽤
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println(">>>ExampleInterceptor.preHandle");
        if (request.getRequestURL().indexOf("example") != -1) {
            // 重定向到指定的页面
            response.sendRedirect("/path/to/redirect");
            // 当返回false时，表示请求结束，后续的Interceptor和Controller都不会再执行
            return false;
        }
        // 当返回值true时，就会继续调用下⼀个Interceptor的preHandle方法，如果已经是最后⼀个
        // Interceptor的时候，就会调用当前请求的Controller方法
        return true;
    }

    public void register(InterceptorRegistry registry) {
        // 白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/example/*");

        // 通过注册工具添加拦截器
        registry.addInterceptor(this)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
