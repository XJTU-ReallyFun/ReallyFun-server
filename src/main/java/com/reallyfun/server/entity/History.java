package com.reallyfun.server.entity;

import java.util.Date;

public class History extends BaseEntity {
    private Integer userId;
    private Integer gameId;
    private Integer totalTime;
    private Date lastMoment;

    private Game game;

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

    public Date getLastMoment() {
        return lastMoment;
    }

    public void setLastMoment(Date lastMoment) {
        this.lastMoment = lastMoment;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "History{" +
                "userId=" + userId +
                ", gameId=" + gameId +
                ", totalTime=" + totalTime +
                ", lastMoment=" + lastMoment +
                ", game=" + game +
                '}';
    }
}
