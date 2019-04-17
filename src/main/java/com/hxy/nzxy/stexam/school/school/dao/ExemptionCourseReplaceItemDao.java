package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExemptionCourseReplaceItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ExemptionCourseReplaceItemDao {

    ExemptionCourseReplaceItemDO get(Long id);

    List<ExemptionCourseReplaceItemDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ExemptionCourseReplaceItemDO exemptionCourseReplaceItem);

    int update(ExemptionCourseReplaceItemDO exemptionCourseReplaceItem);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<ExemptionCourseReplaceItemDO> addList(Query query);

    int addCount(Query query);
}
