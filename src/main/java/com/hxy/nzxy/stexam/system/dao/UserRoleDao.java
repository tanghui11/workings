package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.domain.UserRoleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface UserRoleDao {

    UserRoleDO get(Long id);

    List<UserRoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserRoleDO userRole);

    int update(UserRoleDO userRole);

    int remove(Long id);

    int batchRemove(String[] ids);

    List<RoleDO> listRoleId(String userId);

    int removeByUserId(String userId);

    int batchSave(List<UserRoleDO> list);

    int batchRemoveByUserId(String[] ids);
}
