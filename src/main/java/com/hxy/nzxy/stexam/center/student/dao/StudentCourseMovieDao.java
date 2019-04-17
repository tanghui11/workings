package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.StudentCourseMovieDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentCourseMovieDao {

    List<StudentCourseMovieDO> list(Map<String, Object> map);
//  int update(Map<Map<String, Object>, List> map);
    boolean update(@Param("newRegionid") Long a,@Param("newChileRegionid") Long b, @Param("id") List list);
    int count(Map<String, Object> map);

//    @Select("select ssc.id,ssc.studentid,ss.name,eec.courseid,ssc.type,ssc.regionid,ssc.child_regionid from stu_student_course ssc, stu_student ss, exa_exam_course eec" +
//            "        where ssc.studentid = ss.id " +
//            "        and ssc.exam_courseid = eec.id " +
//            "and eec.exam_taskid =1 " +
//            "and ssc.exam_courseid = 37 " +
//            "and ssc.regionid = '7' " +
//            "and ssc.child_regionid = '13'")
//    List get();
}
