package com.reallyfun.server.entity;

public class Feedback extends BaseEntity {
    private Integer id;
    private Integer userId;
    private Integer gameId;
    private Integer category;
    private String content;
    private Integer handlerId;
    private String handleComment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandleComment() {
        return handleComment;
    }

    public void setHandleComment(String handleComment) {
        this.handleComment = handleComment;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", userId=" + userId +
                ", gameId=" + gameId +
                ", category=" + category +
                ", content='" + content + '\'' +
                ", handlerId=" + handlerId +
                ", handleComment='" + handleComment + '\'' +
                '}';
    }
}
