package com.reallyfun.server.entity;

import java.util.Date;

public class Comment extends BaseEntity {
    private Integer id;
    private Integer userId;
    private Integer gameId;
    private String content;
    private Date moment;
    private Integer directReplyId;
    private Integer rootReplyId;
    private Integer likeCount;
    private Integer replyCount;

    private String userName;
    private String userAvatar;
    private Integer replyUserId;
    private String replyUserName;
    private Boolean isLiked;

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

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
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
                ", likeCount=" + likeCount +
                ", replyCount=" + replyCount +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", replyUserId=" + replyUserId +
                ", replyUserName='" + replyUserName + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }
}
