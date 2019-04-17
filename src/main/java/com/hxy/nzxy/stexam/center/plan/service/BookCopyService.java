package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.BookCopyDO;

import java.util.List;
import java.util.Map;

/**
 * 教材管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface BookCopyService {
	
	BookCopyDO get(Long id);
	
	List<BookCopyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BookCopyDO bookCopy);
	
	int update(BookCopyDO bookCopy);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
