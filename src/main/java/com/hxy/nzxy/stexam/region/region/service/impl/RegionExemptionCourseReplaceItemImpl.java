package com.hxy.nzxy.stexam.region.region.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExemptionCourseReplaceItemDO;
import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;
import com.hxy.nzxy.stexam.region.region.dao.RegionExemptionCourseReplaceItemDao;
import com.hxy.nzxy.stexam.region.region.service.ExemptionCourseReplaceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ypp
 * @Title: ExemptionCourseReplaceItemImpl
 * @Description:
 * @date 2018/12/69:28
 */
@Service
public class RegionExemptionCourseReplaceItemImpl implements ExemptionCourseReplaceItemService {

    @Autowired
    private RegionExemptionCourseReplaceItemDao exemptionCourseReplaceItemDao;
    @Override
    public List<ExemptionCourseReplaceItemDO> list(Query query) {
        return exemptionCourseReplaceItemDao.list(query);
    }

    @Override
    public int count(Query query) {
        return exemptionCourseReplaceItemDao.count(query);
    }

    @Override
    public ExemptionCourseReplaceItemDO get(Long id) {
        return exemptionCourseReplaceItemDao.get(id);
    }

    @Override
    public int save(ScoreReplaceDO exemptionCourseReplaceItem) {
        return exemptionCourseReplaceItemDao.save(exemptionCourseReplaceItem);
    }

    @Override
    public int update(ExemptionCourseReplaceItemDO exemptionCourseReplaceItem) {
        return exemptionCourseReplaceItemDao.update(exemptionCourseReplaceItem);
    }

    @Override
    public int remove(Long id) {
        return exemptionCourseReplaceItemDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return exemptionCourseReplaceItemDao.batchRemove(ids);
    }

    @Override
    public List<ExemptionCourseReplaceItemDO> addList(Query query) {
        return exemptionCourseReplaceItemDao.addList(query);
    }

    @Override
    public int addCount(Query query) {
        return exemptionCourseReplaceItemDao.addCount(query);
    }
}
