package com.reallyfun.server.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 */
public abstract class BaseEntity implements Serializable {
    private Integer createdUser;
    private Date createdTime;
    private Integer modifiedUser;
    private Date modifiedTime;

    public Integer getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Integer createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(Integer modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void modifiedBy(Integer userId) {
        Date now = new Date();
        this.setModifiedTime(now);
        this.setModifiedUser(userId);
    }

    public void createBy(Integer userId) {
        Date now = new Date();
        this.setCreatedTime(now);
        this.setCreatedUser(userId);
        this.setModifiedTime(now);
        this.setModifiedUser(userId);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdUser='" + createdUser + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}