package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentDO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 考生基本信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentService {
	
	StudentDO get(String id);
	
	List<StudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentDO student);
	
	int update(StudentDO student);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    int batchUpdateAudit(Long[] ids, String auditStatus);

	int updateAudit(Map<String,Object> params);

	int  batchUpdateTx(Long[] ids, String status);

    String batchImport(String fileName, MultipartFile file,String flag);

	String importInfoBl(String fileName, MultipartFile file, String type, MultipartHttpServletRequest multipartRequest, MultipartFile multipartFiles, boolean havePhoto) throws IOException, ParseException;
	List readExcelValueBLList(String fileName, MultipartFile file, String type, MultipartHttpServletRequest multipartRequest, MultipartFile multipartFiles, boolean havePhoto) throws IOException, ParseException;

	void saveBatch(List<StudentDO> userKnowledgeBaseList);

}
