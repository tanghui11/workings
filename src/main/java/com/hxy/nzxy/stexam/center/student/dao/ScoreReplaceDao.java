package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 免考成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface ScoreReplaceDao {

	ScoreReplaceDO get(Long id);

	List<ScoreReplaceDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ScoreReplaceDO scoreReplace);

	int update(ScoreReplaceDO scoreReplace);

	int remove(Long id);

	int batchRemove(Long[] ids);

	int updateStatus(Map<String, Object> params);
}
