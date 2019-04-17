package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RoomArrangeKeepSecretDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.RoomArrangeDao;
import com.hxy.nzxy.stexam.domain.RoomArrangeDO;
import com.hxy.nzxy.stexam.center.region.service.RoomArrangeService;



@Service
public class RoomArrangeServiceImpl implements RoomArrangeService {
	@Autowired
	private RoomArrangeDao roomArrangeDao;
	
	@Override
	public RoomArrangeDO get(Long id){
		return roomArrangeDao.get(id);
	}
	
	@Override
	public List<RoomArrangeDO> list(Map<String, Object> map){
		return roomArrangeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roomArrangeDao.count(map);
	}
	
	@Override
	public int save(RoomArrangeDO roomArrange){
		return roomArrangeDao.save(roomArrange);
	}
	
	@Override
	public int update(RoomArrangeDO roomArrange){
		return roomArrangeDao.update(roomArrange);
	}
	
	@Override
	public int remove(Long id){
		return roomArrangeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return roomArrangeDao.batchRemove(ids);
	}

	@Override
	public List<RoomArrangeKeepSecretDO> listKeepSecret(Query query) {
		return roomArrangeDao.listKeepSecret(query);
	}

	@Override
	public int countKeepSecret(Query query) {
		return roomArrangeDao.countKeepSecret(query);
	}

	@Override
	public void batchUpdate(List<RoomArrangeDO> list) {
		roomArrangeDao.batchUpdate(list);
	}

	@Override
	public void batchUpdateQx(List<RoomArrangeDO> ids) {
		roomArrangeDao.batchUpdateQx(ids);
	}

	@Override
	public List<Map<String, Object>> listCheck(Map<String, Object> map) {
		return roomArrangeDao.listCheck(map);
	}

	@Override
	public List<RoomArrangeDO> getByTimeidRoomId(RoomArrangeDO room_arrangeid) {
		return roomArrangeDao.getByTimeidRoomId(room_arrangeid);
	}

	@Override
	public RoomArrangeDO getFirstStudentid(RoomArrangeDO roomArrangeDO1) {
		return roomArrangeDao.getFirstStudentid(roomArrangeDO1);
	}

	@Override
	public int  updateRoomArrangeConfirm(Map<String, Object> params) {
		return roomArrangeDao.updateRoomArrangeConfirm(params);
	}

}
