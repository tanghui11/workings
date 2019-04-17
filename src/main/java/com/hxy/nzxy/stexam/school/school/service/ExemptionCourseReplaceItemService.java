package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExemptionCourseReplaceItemDO;

import java.util.List;

public interface ExemptionCourseReplaceItemService {

    List<ExemptionCourseReplaceItemDO> list(Query query);

    int count(Query query);

    ExemptionCourseReplaceItemDO get(Long id);

    int save(ExemptionCourseReplaceItemDO exemptionCourseReplaceItem);

    int update(ExemptionCourseReplaceItemDO exemptionCourseReplaceItem);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<ExemptionCourseReplaceItemDO> addList(Query query);

    int addCount(Query query);
}