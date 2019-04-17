package com.hxy.nzxy.stexam.system.service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import org.springframework.stereotype.Service;

import com.hxy.nzxy.stexam.system.domain.RoleDO;

@Service
public interface RoleService {

	RoleDO get(String id);

	List<RoleDO> list(Map<String,Object> map);

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(String id);

	List<RoleDO> list(String operatorId,String userId);
}
