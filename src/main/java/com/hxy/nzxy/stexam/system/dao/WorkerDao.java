package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.WorkerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 职员管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-23 11:47:43
 */
@Mapper
public interface WorkerDao {

    WorkerDO get(String id);

    WorkerDO getByMphone(String id);

    List<WorkerDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(WorkerDO worker);

    int update(WorkerDO worker);

    int remove(Long id);
}