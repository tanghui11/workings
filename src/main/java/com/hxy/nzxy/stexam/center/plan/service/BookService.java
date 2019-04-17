package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.BookDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 教材管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:48
 */
public interface BookService {
	
	BookDO get(Long id);
	
	List<BookDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BookDO book);
	
	int update(BookDO book);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String batchImport(String fileName, MultipartFile file);

	void saveBatch(List<BookDO> userKnowledgeBaseList);


    List<BookDO> seachBookList(Map<String, Object> map);
}
