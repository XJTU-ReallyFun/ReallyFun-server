package com.reallyfun.server.entity;

import java.sql.Timestamp;

public class Comment extends BaseEntity {
    private Integer id;
    private Integer userId;
    private Integer gameId;
    private String content;
    private Timestamp moment;
    private Integer directReplyId;
    private Integer rootReplyId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getMoment() {
        return moment;
    }

    public void setMoment(Timestamp moment) {
        this.moment = moment;
    }

    public Integer getDirectReplyId() {
        return directReplyId;
    }

    public void setDirectReplyId(Integer directReplyId) {
        this.directReplyId = directReplyId;
    }

    public Integer getRootReplyId() {
        return rootReplyId;
    }

    public void setRootReplyId(Integer rootReplyId) {
        this.rootReplyId = rootReplyId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", gameId=" + gameId +
                ", content='" + content + '\'' +
                ", moment=" + moment +
                ", directReplyId=" + directReplyId +
                ", rootReplyId=" + rootReplyId +
                '}';
    }
}
