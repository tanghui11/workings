package com.hxy.nzxy.stexam.center.student.domain;

/**
 * @author ypp
 * @Title: PrintDiplomaDO
 * @Description:
 * @date 2018/12/2816:05
 */
public class PrintDiplomaDO extends com.hxy.nzxy.stexam.domain.CertificateReplaceDO {
    //姓名
    private String name;
    //籍贯省
    private String province;
    //市
    private String city;
    //专业
    private String Speciality;
    //专业层次
    private String specialityLevels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public String getSpecialityLevels() {
        return specialityLevels;
    }

    public void setSpecialityLevels(String specialityLevels) {
        this.specialityLevels = specialityLevels;
    }
}
