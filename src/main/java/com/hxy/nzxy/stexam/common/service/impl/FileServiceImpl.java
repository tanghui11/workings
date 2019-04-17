package com.hxy.nzxy.stexam.common.service.impl;

import com.hxy.nzxy.stexam.common.domain.FileDO;
import com.hxy.nzxy.stexam.common.service.FileService;
import com.hxy.nzxy.stexam.common.dao.FileDao;
import com.hxy.nzxy.stexam.common.domain.FileDO;
import com.hxy.nzxy.stexam.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.dao.FileDao;
import com.hxy.nzxy.stexam.common.domain.FileDO;
import com.hxy.nzxy.stexam.common.service.FileService;



@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao sysFileMapper;
	
	@Override
	public FileDO get(Long id){
		return sysFileMapper.get(id);
	}
	
	@Override
	public List<FileDO> list(Map<String, Object> map){
		return sysFileMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysFileMapper.count(map);
	}
	
	@Override
	public int save(FileDO sysFile){
		return sysFileMapper.save(sysFile);
	}
	
	@Override
	public int update(FileDO sysFile){
		return sysFileMapper.update(sysFile);
	}
	
	@Override
	public int remove(Long id){
		return sysFileMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysFileMapper.batchRemove(ids);
	}
	
}
