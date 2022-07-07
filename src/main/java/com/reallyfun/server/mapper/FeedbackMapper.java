package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Feedback;

public interface FeedbackMapper {
    /**
     * @param gameId,category,content
     * @return 受影响的行数
     */
    Integer submitFeedback(Integer gameId, String category, String content);
    /**
     * @param id
     * return 匹配到的用户数据
     */
    Feedback findById(Integer id);
    /**
     * @param id,handlerId,handleComment
     * @return 受影响的行数
     */
    Integer handleFeedback(Integer id, String handleComment);
    /**
     * @param
     */
}
