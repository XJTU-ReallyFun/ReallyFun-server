package com.reallyfun.server.entity;

public class Example extends BaseEntity {
    private Integer eid;
    private Integer eint;
    private String estr;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getEint() {
        return eint;
    }

    public void setEint(Integer eint) {
        this.eint = eint;
    }

    public String getEstr() {
        return estr;
    }

    public void setEstr(String estr) {
        this.estr = estr;
    }

    @Override
    public String toString() {
        return "Example{" +
                "eid=" + eid +
                ", eint=" + eint +
                ", estr='" + estr + '\'' +
                '}';
    }
}
