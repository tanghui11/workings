package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;

public class CountDO implements Serializable{
    private static final long serialVersionUID = 1L;
    private String count1;
    private String count2;
    private String count3;

    public String getCount1() {
        return count1;
    }

    public void setCount1(String count1) {
        this.count1 = count1;
    }

    public String getCount2() {
        return count2;
    }

    public void setCount2(String count2) {
        this.count2 = count2;
    }

    public String getCount3() {
        return count3;
    }

    public void setCount3(String count3) {
        this.count3 = count3;
    }
}

