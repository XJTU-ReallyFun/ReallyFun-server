package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Rating;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IRatingService;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 处理评分相关请求的控制器类
 */
@RestController
@RequestMapping("rating")
public class RatingController extends BaseController {
    @Autowired
    IRatingService ratingService;

    /**
     * 为游戏评分
     *
     * @param rating  评分值
     * @param session HttpSession对象
     * @return 响应结果
     */
    @PostMapping("")
    public ResponseResult<Void> rating(@RequestBody Rating rating, HttpSession session) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 评分并返回结果
        ratingService.evaluate(rating.getGameId(), user.getId(), rating.getRating());
        return ResponseResult.getResponseResult("评分成功！");
    }
}