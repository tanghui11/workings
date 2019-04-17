package com.hxy.nzxy.stexam.center.student.domain;

/**
 * @author ypp
 * @Title: ScoreCheckoutDO
 * @Description:
 * @date 2018/11/2914:10
 */
public class ThirdScoreCheckoutDO {
    //考点代码
    private String examSiteid;
    //考点名称
    private String examSiteName;
    //考场号
    private String examRoomid;
    //课程代码
    private String courseid;
    //课程名称
    private String courseName;
    //考试任务
    private String examTaskid;
    //座位号
    private String seatSeq;
    //准考证号
    private String studentid;
    //姓名
    private String studentName;


    public String getExamSiteid() {
        return examSiteid;
    }

    public void setExamSiteid(String examSiteid) {
        this.examSiteid = examSiteid;
    }

    public String getExamSiteName() {
        return examSiteName;
    }

    public void setExamSiteName(String examSiteName) {
        this.examSiteName = examSiteName;
    }

    public String getExamRoomid() {
        return examRoomid;
    }

    public void setExamRoomid(String examRoomid) {
        this.examRoomid = examRoomid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSeatSeq() {
        return seatSeq;
    }

    public void setSeatSeq(String seatSeq) {
        this.seatSeq = seatSeq;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getExamTaskid() {
        return examTaskid;
    }

    public void setExamTaskid(String examTaskid) {
        this.examTaskid = examTaskid;
    }
}