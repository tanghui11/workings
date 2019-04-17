package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemDO;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemNewDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 通用顶替课程
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface CommonCourseReplaceItemDao {

	CommonCourseReplaceItemDO get(Long id);
	
	List<CommonCourseReplaceItemDO> list(Map<String, Object> map);

	List<CommonCourseReplaceItemNewDO> itemList(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommonCourseReplaceItemDO commonCourseReplaceItem);

	int tyiSave(CommonCourseReplaceItemDO commonCourseReplaceItem);

	int update(CommonCourseReplaceItemDO commonCourseReplaceItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<CommonCourseReplaceItemDO> listTYI(List<CommonCourseReplaceItemDO> userKnowledgeBaseList);
}
