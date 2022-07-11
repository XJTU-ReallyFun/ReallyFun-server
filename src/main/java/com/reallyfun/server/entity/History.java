package com.reallyfun.server.entity;

import java.util.Date;

public class History extends BaseEntity {
    private Integer userId;
    private Integer gameId;
    private Integer totalTime;
    private Date lastMonent;

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

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Date getLastMonent() {
        return lastMonent;
    }

    public void setLastMonent(Date lastMonent) {
        this.lastMonent = lastMonent;
    }

    @Override
    public String toString() {
        return "History{" +
                "userId=" + userId +
                ", gameId=" + gameId +
                ", totalTime=" + totalTime +
                ", lastMonent=" + lastMonent +
                '}';
    }
}
