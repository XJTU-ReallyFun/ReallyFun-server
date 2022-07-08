package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Feedback;
import com.reallyfun.server.service.IFeedbackService;
import com.reallyfun.server.service.ex.InsertException;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;
    @PostMapping("")
    public ResponseResult<Void> submitFeedback (@RequestParam("game_id") Integer gameId, String category, String content) {
        // 调⽤业务对象执⾏注册
        feedbackService.submitFeedback(gameId, category, content);
        // 响应成功
        return ResponseResult.getResponseResult("提交成功");
    }
    @PostMapping("handle")
    public ResponseResult<Void> handleFeedback (Integer id, String handleComment) {
        feedbackService.handleFeedback(id, handleComment);
        // 响应成功
        return ResponseResult.getResponseResult("处理成功");
    }
    @GetMapping("feedbacks")
    public ResponseResult<List<Feedback>> getFeedback(@RequestParam("page_size") Integer pageSize, @RequestParam("page_num") Integer pageNum, @RequestParam("user_id") Integer userId) {
        List<Feedback> list = feedbackService.getFeedback(pageSize,pageNum,userId);
        return ResponseResult.getResponseResult(list);
    }
}
