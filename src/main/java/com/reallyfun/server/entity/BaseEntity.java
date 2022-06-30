package com.reallyfun.server.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 */
public abstract class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void modifiedBy(String user) {
        Date now = new Date();
        this.setModifiedTime(now);
        this.setModifiedUser(user);
    }

    public void createBy(String user) {
        Date now = new Date();
        this.setCreatedTime(now);
        this.setCreatedUser(user);
        this.setModifiedTime(now);
        this.setModifiedUser(user);
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