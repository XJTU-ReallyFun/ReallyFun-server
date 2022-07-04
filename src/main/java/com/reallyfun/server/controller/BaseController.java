package com.reallyfun.server.controller;

import com.reallyfun.server.service.ex.*;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制器类的基类
 */
public class BaseController {
    /**
     * ⽤于统⼀处理⽅法抛出的异常
     *
     * @param e 捕捉到的错误
     * @return 错误信息，若无匹配则返回null
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult<Void> handleException(Throwable e) {
        ResponseResult responseResult = null;
        if (e instanceof ExampleException) {
            responseResult = ResponseResult.getResponseResult(4000, "示例错误");
        } else if (e instanceof UserException) {
            responseResult = ResponseResult.getResponseResult(4000, e.getMessage());
        } else {
            responseResult = ResponseResult.getResponseResult(1000, "未知错误");
        }
        return responseResult;
    }

    /**
     * 从HttpSession对象中获取uid
     *
     * @param session HttpSession对象
     * @return 当前登录的⽤户的id
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 从HttpSession对象中获取⽤户名
     *
     * @param session HttpSession对象
     * @return 当前登录的⽤户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}