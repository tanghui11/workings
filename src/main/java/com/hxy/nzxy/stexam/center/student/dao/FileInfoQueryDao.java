package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.center.student.domain.FileInfoQueryDO;
import com.hxy.nzxy.stexam.common.utils.Query;

import java.util.List;

public interface FileInfoQueryDao {
    List<FileInfoQueryDO> list(Query query);

    int count(Query query);

    FileInfoQueryDO get(Long id);

    int update(FileInfoQueryDO fileInfoQuery);

    int save(FileInfoQueryDO fileInfoQuery);

    List<FileInfoQueryDO> listDiploma(Query query);

    int countDiploma(Query query);
}
