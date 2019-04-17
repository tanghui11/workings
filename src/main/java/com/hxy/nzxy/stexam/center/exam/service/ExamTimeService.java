package com.hxy.nzxy.stexam.center.exam.service;

import com.hxy.nzxy.stexam.domain.ExamTimeDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考试时间
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
public interface ExamTimeService {
	
	ExamTimeDO get(Long id);
	
	List<ExamTimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamTimeDO examTime);
	
	int update(ExamTimeDO examTime);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<ExamTimeDO> seachExamTimeAllList(Map<String,Object> params);

    String batchImport(String fileName, MultipartFile file ,String examTaskid);

    void saveBatch(List<ExamTimeDO> userKnowledgeBaseList);
}
