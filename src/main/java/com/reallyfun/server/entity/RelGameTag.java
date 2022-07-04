package com.reallyfun.server.entity;

public class RelGameTag extends BaseEntity {
    private Integer gameId;
    private Integer tagId;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "RelGameTag{" +
                "gameId=" + gameId +
                ", tagId=" + tagId +
                '}';
    }
}
