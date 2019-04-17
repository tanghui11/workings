package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentCourseMovieService;
import com.hxy.nzxy.stexam.common.utils.DateUtils;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import com.hxy.nzxy.stexam.domain.StudentCourseMovieDO;
import com.hxy.nzxy.stexam.common.utils.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
@Controller
@RequestMapping("/student/studentCourseMovie")
public class StudentCourseMovieController extends SystemBaseController {

    @Autowired
    private StudentCourseMovieService studentCourseMovieService;

    /**
     *
     * @return
     */
    @GetMapping()
    @RequiresPermissions("student:studentCourseMovie:studentCourseMovie")
    String StudentCourseMovie(){
        return "center/student/studentCourseMovie/studentCourseMovie";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("student:studentCourseMovie:studentCourseMovie")
    public PageUtils list(@RequestParam Map<String, Object> params){
        List<StudentCourseMovieDO> studentCourseMovieList = studentCourseMovieService.list(params);
        for (StudentCourseMovieDO item : studentCourseMovieList) {
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
            item.setType(FieldDictUtil.get(getAppName(), "stu_student_course", "type",item.getType() ));
        }
        int total = studentCourseMovieService.count(params);
        PageUtils pageUtils = new PageUtils(studentCourseMovieList,total);

        return pageUtils;
    }


    @ResponseBody
        @Transactional(rollbackFor = Exception.class)
        @GetMapping("/newPlace")
        @RequiresPermissions("student:studentCourseMovie:studentCourseMovie")
        public int putNewPlace(@RequestParam Map<String, Object> params){
            int i = 0;
            boolean res = false;
            Map<Map<String, Object>, List> map = new HashMap<Map<String, Object>, List>();
            List<StudentCourseMovieDO> studentCourseMovieList = studentCourseMovieService.list(params);

            List<Long> lists = new ArrayList<Long>();
            for (StudentCourseMovieDO item : studentCourseMovieList) {
                lists.add(item.getId());
            }
        try{
//          map.put(params,lists);
//          i = studentCourseMovieService.update(map);
            res = studentCourseMovieService.update( Long.valueOf(String.valueOf(params.get("newRegionid"))),  Long.valueOf(String.valueOf(params.get("newChileRegionid"))), lists);
           if (res == true){
               i = 1;
           }else {
               i = 0;
           }
            }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            i = 0;
        }
        return i;
    }

}
