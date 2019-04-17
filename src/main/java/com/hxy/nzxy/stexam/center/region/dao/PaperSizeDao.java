package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.PaperSizeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 卷袋设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
@Mapper
public interface PaperSizeDao {

	PaperSizeDO get(Long id);
	
	List<PaperSizeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaperSizeDO paperSize);
	
	int update(PaperSizeDO paperSize);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
