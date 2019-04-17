package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.BookCopyDao;
import com.hxy.nzxy.stexam.center.plan.service.BookCopyService;
import com.hxy.nzxy.stexam.domain.BookCopyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BookCopyServiceImpl implements BookCopyService {
	@Autowired
	private BookCopyDao bookCopyDao;
	
	@Override
	public BookCopyDO get(Long id){
		return bookCopyDao.get(id);
	}
	
	@Override
	public List<BookCopyDO> list(Map<String, Object> map){
		return bookCopyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bookCopyDao.count(map);
	}
	
	@Override
	public int save(BookCopyDO bookCopy){
		return bookCopyDao.save(bookCopy);
	}
	
	@Override
	public int update(BookCopyDO bookCopy){
		return bookCopyDao.update(bookCopy);
	}
	
	@Override
	public int remove(Long id){
		return bookCopyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return bookCopyDao.batchRemove(ids);
	}
	
}
