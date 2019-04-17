package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.FileInfoQueryDao;
import com.hxy.nzxy.stexam.center.student.domain.FileInfoQueryDO;
import com.hxy.nzxy.stexam.center.student.service.FileInfoQueryService;
import com.hxy.nzxy.stexam.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ypp
 * @Title: FileInfoQueryServiceImpl
 * @Description:
 * @date 2018/12/2820:18
 */
@Service
public class FileInfoQueryServiceImpl implements FileInfoQueryService {
    @Autowired
    private FileInfoQueryDao fileInfoQueryDao;

    @Override
    public List<FileInfoQueryDO> list(Query query) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = "";
        String time2 = "";
        if ("a".equals(query.get("year"))){
            time1 = query.get("time")+"-01-01 00:00:00";
            time2 = query.get("time")+"-06-30 23:59:59";
        }
        if("b".equals(query.get("year"))){
            time1 = query.get("time")+"-07-01 00:00:00";
            time2 = query.get("time")+"-12-31 23:59:59";
        }
        query.put("time1",time1);
        query.put("time2",time2);
        return fileInfoQueryDao.list(query);
    }

    @Override
    public int count(Query query) {
        return fileInfoQueryDao.count(query);
    }

    @Override
    public FileInfoQueryDO get(Long id) {
        return fileInfoQueryDao.get(id);
    }
    @Override
    public int update(FileInfoQueryDO fileInfoQuery) {
        return fileInfoQueryDao.update(fileInfoQuery);
    }

    @Override
    public int save(FileInfoQueryDO fileInfoQuery) {
        return fileInfoQueryDao.save(fileInfoQuery);
    }

    @Override
    public List<FileInfoQueryDO> listDiploma(Query query) {
        return fileInfoQueryDao.listDiploma(query);
    }

    @Override
    public int countDiploma(Query query) {
        return fileInfoQueryDao.countDiploma(query);
    }
}
