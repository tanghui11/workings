package com.hxy.nzxy.stexam.system.service.impl;

import java.util.*;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.dao.DeptDao;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.dao.UserRoleDao;
import com.hxy.nzxy.stexam.system.domain.*;
import com.hxy.nzxy.stexam.system.service.UserService;
import com.hxy.nzxy.stexam.system.vo.UserVO;
import com.hxy.nzxy.stexam.common.utils.MD5Utils;
import com.hxy.nzxy.stexam.system.vo.UserVO;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.BuildTree;
import com.hxy.nzxy.stexam.system.dao.DeptDao;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.dao.UserRoleDao;
import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.domain.UserRoleDO;
import com.hxy.nzxy.stexam.system.service.UserService;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.BuildTree;
import com.hxy.nzxy.stexam.system.dao.DeptDao;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.dao.UserRoleDao;
import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.domain.UserRoleDO;
import com.hxy.nzxy.stexam.system.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userMapper;

    @Autowired
    UserRoleDao userRoleMapper;

    @Autowired
    DeptDao deptMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDO get(String id) {
        List<RoleDO> roleIds = userRoleMapper.listRoleId(id);
        List<String> arrRoles = new ArrayList<>();
        for (RoleDO item : roleIds) {
            arrRoles.add(item.getId());
        }
        UserDO user = userMapper.getUser(id);
        String type = user.getType();
        if (type.equals("1")) {
            user = userMapper.get(id);
        } else if (type.equals("2")) {
            user = userMapper.getRegion(id);

        } else if (type.equals("3")) {
            user = userMapper.getSchool(id);

        } else if (type.equals("4")) {
            user = userMapper.getCollege(id);

            user.setRoleIds(arrRoles);
            return user;
        }
        return user;
    }
    @Override
    public UserDO getByWorkerid(String workerid) {
        return userMapper.getByWorkerid(workerid);
    }

    @Override
    public UserDO getByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public List<UserDO> list(Map<String, Object> map) {
        return userMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userMapper.count(map);
    }

    @Transactional
    @Override
    public int  save(UserDO user) {
        int count = userMapper.save(user);
        String userId = user.getId();
        List<String> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<UserRoleDO> list = new ArrayList<>();
        for (String roleId : roles) {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserid(userId);
            ur.setRoleid(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    public int update(UserDO user) {
        int r = userMapper.update(user);
        String userId = user.getId();
        if (!user.getRoleIds().isEmpty()) {
            List<String> roles = user.getRoleIds();
            userRoleMapper.removeByUserId(userId);
            List<UserRoleDO> list = new ArrayList<>();
            for (String roleId : roles) {
                UserRoleDO ur = new UserRoleDO();
                ur.setUserid(userId);
                ur.setRoleid(roleId);
                if (!roleId.equals("")){
                    list.add(ur);
                }

            }
            if (list.size() > 0) {
                userRoleMapper.batchSave(list);
            }
        }
        return r;
    }

    @Override
    public int remove(String userId) {
        userRoleMapper.removeByUserId(userId);
        return userMapper.remove(userId);
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userMapper.list(params).size() > 0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> depts = deptMapper.list(new HashMap<String, Object>(16));
        Long[] pDepts = deptMapper.listParentDept();
        Long[] uDepts = userMapper.listAllDept();
        Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
        for (DeptDO dept : depts) {
            if (!ArrayUtils.contains(allDepts, dept.getId())) {
                continue;
            }
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getParentid().toString());
            tree.setText(dept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        List<UserDO> users = userMapper.list(new HashMap<String, Object>(16));
        for (UserDO user : users) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(user.getId().toString());
            //tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<UserDO> listRegion(Query query) {
        return userMapper.listRegion(query);
    }

    @Override
    public List<UserDO> listSchool(Query query) {
        return userMapper.listSchool(query);
    }

    @Override
    public List<UserDO> listCollege(Query query) {
        return userMapper.listCollege(query);
    }

    @Override
    public int countRegion(Query query) {
        return userMapper.countRegion(query);
    }

    @Override
    public int countSchool(Query query) {
        return userMapper.countSchool(query);
    }

    @Override
    public int countCollege(Query query) {
        return userMapper.countCollege(query);
    }
}
