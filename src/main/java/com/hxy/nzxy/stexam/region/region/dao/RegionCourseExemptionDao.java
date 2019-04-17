package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RegionCourseExemptionDO;
import com.hxy.nzxy.stexam.domain.StudentDO;

import java.util.List;

/**
 * @author ypp
 * @Title: CourseExemptionDao
 * @Description:
 * @date 2018/11/2014:49
 */
public interface  RegionCourseExemptionDao {

    public RegionCourseExemptionDO get(Long id) ;

    public  int count(Query query);

    public  List<StudentDO> list(Query query);

    public  int save(RegionCourseExemptionDO courseExemption);

    public  int update(RegionCourseExemptionDO courseExemption);

    public  int remove(Long id);

    public  int batchRemove(Long[] ids);
}
