package com.reallyfun.server.service;

import com.reallyfun.server.entity.Feedback;

import java.util.List;

public interface IFeedbackService {
    void submitFeedback(Integer gameId, String category, String content);
    void handleFeedback(Integer id,String handleComment);
    List<Feedback> getFeedback(Integer pageSize, Integer pageNum, Integer userId);
}
