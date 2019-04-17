package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.WarehousingScoreEditDao;
import com.hxy.nzxy.stexam.center.student.service.WarehousingScoreEditService;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditDO;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ypp
 * @Title: WarehousingScoreEditServiceImpl
 * @Description:
 * @date 2018/11/2721:08
 */
@Service
public class WarehousingScoreEditServiceImpl implements WarehousingScoreEditService {

    @Autowired
    private WarehousingScoreEditDao warehousingScoreEditDao;
    @Override
    public List<WarehousingScoreEditDO> list(Map<String, Object> map) {
        return warehousingScoreEditDao.list(map);
    }

    @Override
    public int count(Query query) {
        return warehousingScoreEditDao.count(query);
    }

    @Override
    public int update(WarehousingScoreEditRecordDO warehousingScoreEditDO) {
        return warehousingScoreEditDao.update(warehousingScoreEditDO);
    }

    @Override
    public int saveRecord(WarehousingScoreEditRecordDO warehousingScoreEdit) {
        return warehousingScoreEditDao.saveRecord(warehousingScoreEdit);
    }

    @Override
    public List<WarehousingScoreEditRecordDO> warehousingScoreEditRecordList(Map<String, Object> map) {
        return warehousingScoreEditDao.warehousingScoreEditRecordList(map);
    }

    @Override
    public int warehousingScoreEditRecordCount(Query query) {
        return warehousingScoreEditDao.warehousingScoreEditRecordCount(query);
    }

    @Override
    public int recordRemove(Long id) {
        return warehousingScoreEditDao.recordRemove(id);
    }



}