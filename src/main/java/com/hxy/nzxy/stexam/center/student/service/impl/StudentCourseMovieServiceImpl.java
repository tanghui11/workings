package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseMovieDao;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseMovieService;
import com.hxy.nzxy.stexam.domain.StudentCourseMovieDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentCourseMovieServiceImpl implements StudentCourseMovieService {

    @Autowired
    private StudentCourseMovieDao studentCourseMovieDao;

    @Override
    public List<StudentCourseMovieDO> list(Map<String, Object> map) {
        return studentCourseMovieDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return studentCourseMovieDao.count(map);
    }

//    @Override
//    public int update(Map<Map<String, Object>, List> map){
//        return studentCourseMovieDao.update(map);
//    }
    @Override
    public boolean update(Long a, Long b, List list){
        return studentCourseMovieDao.update(a, b, list);
    }

}
