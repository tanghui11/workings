package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.system.dao.HelpDao;
import com.hxy.nzxy.stexam.system.domain.HelpDO;
import com.hxy.nzxy.stexam.system.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.HelpDao;
import com.hxy.nzxy.stexam.system.domain.HelpDO;
import com.hxy.nzxy.stexam.system.service.HelpService;


@Service
public class HelpServiceImpl implements HelpService {
    @Autowired
    private HelpDao helpDao;

    @Override
    public HelpDO get(Map<String, Object> map) {
        return helpDao.get(map);
    }

    @Override
    public int save(HelpDO help) {
        return helpDao.save(help);
    }

    @Override
    public int update(HelpDO help) {
        return helpDao.update(help);
    }

    @Override
    public int remove(String menuid) {
        return helpDao.remove(menuid);
    }
}
