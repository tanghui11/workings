package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentTempDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 考生基本信息临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:26
 */
public interface StudentTempService {
	
	StudentTempDO get(String ksZkz);
	
	List<StudentTempDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentTempDO studentTemp);

	int ZSsave(StudentTempDO studentTemp);

	int isNotExist(String studentid);
	
	int update(StudentTempDO studentTemp);
	
	int remove(String type);

	int moveOut(String studentid);

	int batchRemove(String[] types);

	String batchImport(String fileName,  MultipartFile file, String type) throws IOException, ParseException;

	Integer delete (String studentid);
}
