package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExemptionCourseReplaceItemDO;
import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface RegionExemptionCourseReplaceItemDao {

    ExemptionCourseReplaceItemDO get(Long id);

    List<ExemptionCourseReplaceItemDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ScoreReplaceDO exemptionCourseReplaceItem);

    int update(ExemptionCourseReplaceItemDO exemptionCourseReplaceItem);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<ExemptionCourseReplaceItemDO> addList(Query query);

    int addCount(Query query);
}
