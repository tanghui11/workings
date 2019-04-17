package com.hxy.nzxy.stexam.center.school.dao;

import com.hxy.nzxy.stexam.domain.TeachSiteDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教学点管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
@Mapper
public interface TeachSiteDao {

	TeachSiteDO get(Long id);
	
	List<TeachSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TeachSiteDO teachSite);
	
	int update(TeachSiteDO teachSite);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int batchUpdateAudit(Map map);

	int updateAudit(Map<String,Object> params);

    List<TeachSiteDO> listCZ(List<TeachSiteDO> userKnowledgeBaseList);

    void saveBatch(List<TeachSiteDO> userKnowledgeBaseList);
}
