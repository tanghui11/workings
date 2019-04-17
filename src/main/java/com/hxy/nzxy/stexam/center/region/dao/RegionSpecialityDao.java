package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.BookDO;
import com.hxy.nzxy.stexam.domain.RegionSpecialityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考区专业课程报考
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
@Mapper
public interface RegionSpecialityDao {

	RegionSpecialityDO get(Long id);
	
	List<RegionSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RegionSpecialityDO regionSpeciality);
	
	int update(RegionSpecialityDO regionSpeciality);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<RegionSpecialityDO> seachSubjectIdlist(Map<String, Object> map);

	int batchSave(List<RegionSpecialityDO> list);

    int countSubject(Map<String,Object> map);

    void saveBatch(List<RegionSpecialityDO> userKnowledgeBaseList);

	List<RegionSpecialityDO> listCZ(List<RegionSpecialityDO> userKnowledgeBaseList);
}
