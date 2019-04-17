package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentCourseMovieDO;

import java.util.List;
import java.util.Map;

public interface StudentCourseMovieService {

    List<StudentCourseMovieDO> list(Map<String, Object> map);
   // int update(Map<Map<String, Object>, List> map);
    boolean update(Long a , Long b , List list);
    int count(Map<String, Object> map);
}
