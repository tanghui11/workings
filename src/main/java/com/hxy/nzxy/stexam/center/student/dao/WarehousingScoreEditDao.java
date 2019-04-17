package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditDO;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditRecordDO;

import java.util.List;
import java.util.Map;

public interface WarehousingScoreEditDao {

    List<WarehousingScoreEditDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<WarehousingScoreEditRecordDO> warehousingScoreEditRecordList(Map<String, Object> map);

    int warehousingScoreEditRecordCount(Query query);

    int  saveRecord(WarehousingScoreEditRecordDO warehousingScoreEdit);

    int recordRemove(Long id);

    int update(WarehousingScoreEditRecordDO warehousingScoreEditDO);

    int saveGrade(WarehousingScoreEditRecordDO warehousingScoreEditRecord);
}
