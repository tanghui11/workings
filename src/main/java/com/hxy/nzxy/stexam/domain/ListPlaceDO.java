package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;

public class ListPlaceDO implements Serializable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialityid() {
        return specialityid;
    }

    public void setSpecialityid(String specialityid) {
        this.specialityid = specialityid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String name;
    private String specialityid;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
}
