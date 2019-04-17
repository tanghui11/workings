package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.KnowledgeModuleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 知识模块
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-03 09:25:06
 */
@Mapper
public interface KnowledgeModuleDao {

    KnowledgeModuleDO get(String id);

    Integer getMaxSeqFromParentid(Map<String, Object> map);

    List<KnowledgeModuleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(KnowledgeModuleDO knowledgeModule);

    int update(KnowledgeModuleDO knowledgeModule);

    int remove(String id);

    int updateSeqUp(Map<String, Object> map);

    int updateSeqDown(Map<String, Object> map);
}
