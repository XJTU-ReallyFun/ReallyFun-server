package com.reallyfun.server.interceptor;

import com.reallyfun.server.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义处理器拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
    // 该方法将在请求处理之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println(">>>AuthInterceptor.preHandle");
        // 获取当前用户对象
        User user = (User) request.getSession().getAttribute("user");

        // 若用户权限不足，则返回错误信息并结束
        if (user.getAuth() == 0) {
            response.getWriter().write("{\"code\":3001,\"message\":\"You are not authorized.\"}");
            response.setContentType("application/json");
            response.setStatus(200);
            return false;
        }

        // 否则继续进行
        return true;
    }

    public void register(InterceptorRegistry registry) {
        // 通过注册工具添加拦截器
        registry.addInterceptor(this)
                .addPathPatterns("/tag**")
                .excludePathPatterns("/tag/bind")
                .excludePathPatterns("/tag/unbind");
    }
}