package com.reallyfun.server.service;

import com.reallyfun.server.entity.Feedback;

public interface IFeedbackService {
    void submitFeedback(Integer gameId, String category, String content);
    void handleFeedback(Integer id,String handleComment);
}
