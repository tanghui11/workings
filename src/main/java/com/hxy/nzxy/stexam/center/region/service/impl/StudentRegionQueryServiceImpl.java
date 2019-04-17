package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.center.region.dao.StudentRegionQueryDao;
import com.hxy.nzxy.stexam.center.region.domain.StudentRegionQueryDO;
import com.hxy.nzxy.stexam.center.region.service.StudentRegionQueryService;
import com.hxy.nzxy.stexam.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ypp
 * @Title: StudentRegionQueryServiceImpl
 * @Description:
 * @date 2018/12/1213:40
 */
@Service
public class StudentRegionQueryServiceImpl implements StudentRegionQueryService {
    @Autowired
    private StudentRegionQueryDao studentRegionQueryDao;

    @Override
    public List<StudentRegionQueryDO> list(Query query) {
            return studentRegionQueryDao.list(query);
    }

    @Override
    public int count(Query query) {
        return studentRegionQueryDao.count(query);
    }
}
