package com.reallyfun.server.entity;

public class Game extends BaseEntity {
    private Integer id;
    private Integer userId;
    private String title;
    private String intro;
    private String tutorial;
    private String entry;
    private String source;
    private String thumb;
    private Boolean isHidden;
    private Double rating;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", tutorial='" + tutorial + '\'' +
                ", entry='" + entry + '\'' +
                ", source='" + source + '\'' +
                ", thumb='" + thumb + '\'' +
                ", isHidden=" + isHidden +
                ", rating=" + rating +
                '}';
    }
}
