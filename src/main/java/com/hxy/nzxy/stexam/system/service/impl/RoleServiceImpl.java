package com.hxy.nzxy.stexam.system.service.impl;

import java.util.*;

import com.hxy.nzxy.stexam.system.dao.RoleDao;
import com.hxy.nzxy.stexam.system.dao.RoleMenuDao;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.dao.UserRoleDao;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.domain.RoleMenuDO;
import com.hxy.nzxy.stexam.system.service.RoleService;
import com.hxy.nzxy.stexam.system.dao.RoleDao;
import com.hxy.nzxy.stexam.system.dao.RoleMenuDao;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.dao.UserRoleDao;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.domain.RoleMenuDO;
import com.hxy.nzxy.stexam.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxy.nzxy.stexam.system.dao.RoleDao;
import com.hxy.nzxy.stexam.system.dao.RoleMenuDao;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.dao.UserRoleDao;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.domain.RoleMenuDO;
import com.hxy.nzxy.stexam.system.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleDao roleMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;

    @Override
    public List<RoleDO> list(Map<String, Object> map) {
        List<RoleDO> roles = roleMapper.list(map);
        return roles;
    }

    @Override
    public List<RoleDO> list(String operatorId, String userId) {
        List<RoleDO> listOperatorRoles;
        if (operatorId.equals("1")) {
            listOperatorRoles = roleMapper.list(null);
        } else {
            listOperatorRoles = userRoleMapper.listRoleId(operatorId);
        }
        List<RoleDO> listUserRoles = userRoleMapper.listRoleId(userId);
        for (RoleDO roleDO : listOperatorRoles) {
            roleDO.setRoleSign("false");
            for (RoleDO roleId : listUserRoles) {
                if (roleDO.getId().equals(roleId.getId())) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return listOperatorRoles;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public int save(RoleDO role) {
        int count = roleMapper.save(role);
        List<String> menuIds = role.getMenuIds();
        String roleId = role.getId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (String menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleid(roleId);
            rmDo.setMenuid(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public int remove(String id) {
        int count = roleMapper.remove(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public RoleDO get(String id) {
        RoleDO roleDO = roleMapper.get(id);
        return roleDO;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Override
    public int update(RoleDO role) {
        int r = roleMapper.update(role);
        List<String> menuIds = role.getMenuIds();
        String roleId = role.getId();
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<>();
        for (String menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleid(roleId);
            rmDo.setMenuid(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return r;
    }
}
