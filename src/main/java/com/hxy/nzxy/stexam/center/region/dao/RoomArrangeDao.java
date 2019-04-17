package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RoomArrangeDO;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.RoomArrangeKeepSecretDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考场编排
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
@Mapper
public interface RoomArrangeDao {

	RoomArrangeDO get(Long id);
	
	List<RoomArrangeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomArrangeDO roomArrange);
	
	int update(RoomArrangeDO roomArrange);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<RoomArrangeKeepSecretDO> listKeepSecret(Query query);

	int countKeepSecret(Query query);

    void batchUpdate(List<RoomArrangeDO> list);

	void batchUpdateQx(List<RoomArrangeDO> list);

    List<Map<String,Object>> listCheck(Map<String,Object> map);

    List<RoomArrangeDO> getByTimeidRoomId(RoomArrangeDO room_arrangeid);

    RoomArrangeDO getFirstStudentid(RoomArrangeDO roomArrangeDO1);

    int updateRoomArrangeConfirm(Map<String,Object> params);
}
