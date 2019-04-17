package com.hxy.nzxy.stexam.system.service;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.system.domain.OrgDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 机构管理
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-19 09:39:15
 */
public interface OrgService {

    OrgDO get(String id);

    OrgDO getByCode(String code);
	
	List<OrgDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrgDO org);
	
	int update(OrgDO org);
	
	int remove(String id);

	List<TreeEx<OrgDO>> getTree(Map<String, Object> map);

	Tree<OrgDO> getTreeAll(Map<String, Object> map);

	List<OrgDO> listOrgForTree(Map<String, Object> map);

	List<OrgDO> verifyChildrenByOrgid(Map<String, Object> map);
}
