package com.reallyfun.server.entity;

public class Rating extends BaseEntity {
    private Integer gameId;
    private Integer userId;
    private Integer rating;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "gameId=" + gameId +
                ", userId=" + userId +
                ", rating=" + rating +
                '}';
    }
}
