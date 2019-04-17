package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.BookDO;
import com.hxy.nzxy.stexam.domain.OldCourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教材管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:48
 */
@Mapper
public interface BookDao {

	BookDO get(Long id);
	
	List<BookDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BookDO book);
	
	int update(BookDO book);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<BookDO> seachBookList(Map<String, Object> map);

    void saveBatch(List<BookDO> userKnowledgeBaseList);

	List<BookDO> listCZ(List<BookDO> userKnowledgeBaseList);
}
