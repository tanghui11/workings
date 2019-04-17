package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditDO;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditRecordDO;

import java.util.List;
import java.util.Map;

public interface WarehousingScoreEditService {

    List<WarehousingScoreEditDO> list(Map<String, Object> map);

    int count(Query query);

    int update(WarehousingScoreEditRecordDO warehousingScoreEdit);

    int saveRecord(WarehousingScoreEditRecordDO warehousingScoreEdit);

    List<WarehousingScoreEditRecordDO> warehousingScoreEditRecordList(Map<String, Object> map);

    int warehousingScoreEditRecordCount(Query query);

    int recordRemove(Long id);


}
