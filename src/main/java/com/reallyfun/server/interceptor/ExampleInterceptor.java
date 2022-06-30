package com.reallyfun.server.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义处理器拦截器
 */
public class ExampleInterceptor implements HandlerInterceptor {
    // 该⽅法将在请求处理之前被调⽤
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getRequestURL().indexOf("example") != -1) {
            // 重定向到指定的⻚⾯
            response.sendRedirect("/path/to/redirect");
            // 当返回false时，表示请求结束，后续的Interceptor和Controller都不会再执⾏
            return false;
        }
        // 当返回值true时，就会继续调⽤下⼀个Interceptor的preHandle⽅法，如果已经是最后⼀个
        // Interceptor的时，就会调⽤当前请求的Controller⽅法
        return true;
    }
}
