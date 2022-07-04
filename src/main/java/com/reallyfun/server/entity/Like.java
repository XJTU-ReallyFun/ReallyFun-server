package com.reallyfun.server.entity;

public class Like extends BaseEntity {
    private Integer userId;
    private Integer commentId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Like{" +
                "userId=" + userId +
                ", commentId=" + commentId +
                '}';
    }
}
