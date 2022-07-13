package com.reallyfun.server.entity;

import java.util.Date;

public class Game extends BaseEntity {
    private Integer id;
    private Integer userId;
    private String title;
    private String intro;
    private String tutorial;
    private String entry;
    private String bundle;
    private String thumb;
    private Boolean isHidden;
    private Double rating;
    private Date moment;

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

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
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
                ", bundle='" + bundle + '\'' +
                ", thumb='" + thumb + '\'' +
                ", isHidden=" + isHidden +
                ", rating=" + rating +
                ", moment=" + moment +
                '}';
    }
}
