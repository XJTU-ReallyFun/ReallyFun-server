package com.reallyfun.server.entity;

public class Favorite extends BaseEntity {
    private Integer userId;
    private Integer gameId;

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

    @Override
    public String toString() {
        return "Favorite{" +
                "userId=" + userId +
                ", gameId=" + gameId +
                '}';
    }
}
