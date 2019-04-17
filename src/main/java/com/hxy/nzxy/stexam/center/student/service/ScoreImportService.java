package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.ScoreImportDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 成绩导入临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
public interface ScoreImportService {
	
	ScoreImportDO get(String kemuid);

//	ScoreImportDO selectStudentid(String kemuid,String code);

	int ifPutIn(String code, String examTaskid, String kemuid);

	int putInFormation(ScoreImportDO scoreImportDO);

	int setEnabled(String kemuid,String code);

	int delete();
	
	List<ScoreImportDO> list(Map<String, Object> map);

	List<ScoreImportDO> listAll();
	
	int count(Map<String, Object> map);
	
	int save(ScoreImportDO scoreImport);
	
	int update(ScoreImportDO scoreImport);
	
	int remove(String kemuid);
	
	int batchRemove(String[] kemuids);

	String batchImport(String fileName, MultipartFile file) throws IOException, ParseException;
}
