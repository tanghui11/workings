package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.CourseExemptionDO;

import java.util.List;

/**
 * @author ypp
 * @Title: CourseExemptionDao
 * @Description:
 * @date 2018/11/2014:49
 */
public interface  CourseExemptionDao {

    public   CourseExemptionDO get(Long id) ;

    public  int count(Query query);

    public  List<CourseExemptionDO> list(Query query);

    public  int save(CourseExemptionDO courseExemption);

    public  int update(CourseExemptionDO courseExemption);

    public  int remove(Long id);

    public  int batchRemove(Long[] ids);
}
