package com.hxy.nzxy.stexam.center.region.domain;

/**报考数据查询
 * @author ypp
 * @Title: StudentInfoQueryDO
 * @Description:
 * @date 2018/12/1210:00
 */
public class StudentInfoQueryDO {
    //名称
    private String id;
    private String name;
    private String studentName;
    //报考人数
    private Long studentCount;
    //报考科次
    private Long courseCount;
    //考试任务
    private String examTaskid;
    //考生类别
    private String studentType;
    //报考类别
    private String type;
    //学校id
    private String schoolid;
    //地区id
    private String regionid;
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getExamTaskid() {
        return examTaskid;
    }

    public void setExamTaskid(String examTaskid) {
        this.examTaskid = examTaskid;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    public Long getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Long courseCount) {
        this.courseCount = courseCount;
    }
    //考生类别

}
