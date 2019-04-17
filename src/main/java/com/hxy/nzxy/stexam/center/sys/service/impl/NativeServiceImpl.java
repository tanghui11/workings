package com.hxy.nzxy.stexam.center.sys.service.impl;

import com.hxy.nzxy.stexam.center.sys.dao.NativeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.NativeDO;
import com.hxy.nzxy.stexam.center.sys.service.NativeService;



@Service
public class NativeServiceImpl implements NativeService {
	@Autowired
	private NativeDao nativeDao;
	
	@Override
	public NativeDO get(String id){
		return nativeDao.get(id);
	}
	
	@Override
	public List<NativeDO> list(Map<String, Object> map){
		return nativeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return nativeDao.count(map);
	}
	
	@Override
	public int save(NativeDO nativeDo){
		return nativeDao.save(nativeDo);
	}
	
	@Override
	public int update(NativeDO nativeDo){
		return nativeDao.update(nativeDo);
	}
	
	@Override
	public int remove(String id){
		return nativeDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return nativeDao.batchRemove(ids);
	}
	
}
