package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.OrgDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机构管理
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-19 09:39:15
 */
@Mapper
public interface OrgDao {

    OrgDO get(String id);

    OrgDO getByCode(String code);
	
	List<OrgDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OrgDO org);
	
	int update(OrgDO org);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<OrgDO> listAllOrgAndDeptForTree(Map<String, Object> map);

	List<OrgDO> listOrgForTree(Map<String, Object> map);

	List<OrgDO> verifyChildrenByOrgid(Map<String, Object> map);

	List<OrgDO> listOrgsByParentid(String[] ids);

	List<OrgDO> listOrgForTreeByIds(String[] ids);
}
