package com.hxy.nzxy.stexam.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.domain.WorkerDO;
import com.hxy.nzxy.stexam.system.vo.UserVO;
import com.hxy.nzxy.stexam.system.vo.UserVO;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import org.springframework.stereotype.Service;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.system.domain.UserDO;

@Service
public interface UserService {
	UserDO get(String id);

	UserDO getByWorkerid(String workerid);

	UserDO getByName(String name);

	List<UserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserDO user);

	int update(UserDO user);

	int remove(String userId);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	Tree<DeptDO> getTree();

	List<UserDO> listRegion(Query query);

	List<UserDO> listSchool(Query query);

	List<UserDO> listCollege(Query query);

	int countRegion(Query query);

	int countSchool(Query query);

	int countCollege(Query query);
}
