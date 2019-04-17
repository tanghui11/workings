package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.domain.PaperSizeDO;

import java.util.List;
import java.util.Map;

/**
 * 卷袋设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
public interface PaperSizeRegionService {
	
	PaperSizeDO get(Long id);
	
	List<PaperSizeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaperSizeDO paperSizeRegion);
	
	int update(PaperSizeDO paperSizeRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
