package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentOutScoreDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *功能描述 考籍转出 成绩
 * @author ypp
 * @date 2018/11/28
 * @param
 * @return
 */
@Mapper
public interface StudentOutScoreDao {

    StudentOutScoreDO get(Long id);

    List<StudentOutScoreDO> list(Query query);

    int count(Query query);
}
