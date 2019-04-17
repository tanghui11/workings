package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.domain.WorkerDO;
import org.apache.ibatis.annotations.Mapper;

/**com.hxy.nzxy.stexam.system.dao.UserDao
 * com.hxy.nzxy.stexam.common.dao.CommonDao
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

    UserDO get(String userId);

    UserDO getUser(String userId);

    UserDO getByWorkerid(String workerid);

    UserDO getByName(String userName);

    List<UserDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(String userId);

    int batchRemove(String[] userIds);

    Long[] listAllDept();

    List<UserDO> listRegion(Query query);

    List<UserDO> listSchool(Query query);

    List<UserDO> listCollege(Query query);

    int countRegion(Query query);

    int countSchool(Query query);

    int countCollege(Query query);

    UserDO getUserType(String username);

    UserDO getRegionByName(String username);

    UserDO getSchoolByName(String username);

    UserDO getCollegeByName(String username);

    UserDO getRegion(String id);

    UserDO getSchool(String id);

    UserDO getCollege(String id);
}
