package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseTempDao;
import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseTempService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StudentCourseTempServiceImpl implements StudentCourseTempService {
	@Autowired
	private StudentCourseTempDao studentCourseTempDao;

	DbfReadUtil dbfReadUtil = new DbfReadUtil();

	@Override
	public StudentCourseTempDO get(Long id){
		return studentCourseTempDao.get(id);
	}
	
	@Override
	public List<StudentCourseTempDO> list(Map<String, Object> map){
		return studentCourseTempDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseTempDao.count(map);
	}
	
	@Override
	public int save(StudentCourseTempDO studentCourseTemp){
		return studentCourseTempDao.save(studentCourseTemp);
	}
	
	@Override
	public int update(StudentCourseTempDO studentCourseTemp){
		return studentCourseTempDao.update(studentCourseTemp);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseTempDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseTempDao.batchRemove(ids);
	}

	//读取dbf文件
	@Override
	public String batchImport(String fileName, MultipartFile file) throws IOException {

		List<Map<String,String>> list = dbfReadUtil.readDbf(file);
		int num = dbfReadUtil.readDbf1(file);
			return readExcelValue(list,num);

	}

	/**
	 * 解析Excel里面的数据
	 * @return
	 */
	private String readExcelValue(List<Map<String,String>> list, int num) {


		StudentCourseTempDO tempUserKB;
		String br = "<br/>";
		String errorMsg = "";
		List listSet = null;
		Set keys = null;
		List<StudentCourseTempDO> userKnowledgeBaseList=new ArrayList<>();

		for (int i =0; i<list.size();i++){
			String sx_dm  ="";
			String ks_zkz ="";
			String ks_xm  ="";
			String zydm   ="";
			String zymc   ="";
			String kcdm   ="";
			String kcmc   ="";
			String kssj   = "";
			String kssjxh ="";
			tempUserKB = new StudentCourseTempDO();

			keys = list.get(i).keySet();
			listSet = new ArrayList();
			String rowMessage = "";
			listSet.addAll(keys);
			System.out.println(listSet);

			for (int j =0; j<num;j++){

				if(list.get(i)!= null){
					if(listSet.get(j) == "sx_dm" || "sx_dm".equals(listSet.get(j))){
						sx_dm = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(sx_dm)) {
							rowMessage += "市县代码不能为空；";
						}else if (sx_dm.length() > 4){
							rowMessage += "市县代码的长度不能超过4";
						}
						tempUserKB.setSxDm(sx_dm);
					}
					if(listSet.get(j) == "ks_zkz" || "ks_zkz".equals(listSet.get(j))){
						ks_zkz = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(ks_zkz)) {
							rowMessage += "准考证号不能为空；";
						}else if (ks_zkz.length() > 12){
							rowMessage += "准考证号的长度不能超过12";
						}
						tempUserKB.setKsZkz(ks_zkz);
					}
					if(listSet.get(j) == "ks_xm" || "ks_xm".equals(listSet.get(j))){
						ks_xm = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(ks_xm)) {
							rowMessage += "姓名不能为空；";
						}
						tempUserKB.setKsXm(ks_xm);
					}
					if(listSet.get(j) == "zydm" || "zydm".equals(listSet.get(j))){
						zydm = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(zydm)) {
							rowMessage += "专业代码不能为空；";
						}
						tempUserKB.setZydm(zydm);

					}
					if(listSet.get(j) == "zymc" || "zymc".equals(listSet.get(j))){
						zymc = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(zymc)) {
							rowMessage += "专业名称不能为空；";
						}
						tempUserKB.setZymc(zymc);
					}
					if(listSet.get(j) == "kcdm" || "kcdm".equals(listSet.get(j))){
						kcdm = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(kcdm)) {
							rowMessage += "课程代码不能为空；";
						}
						tempUserKB.setKcdm(kcdm);
					}
					if(listSet.get(j) == "kcmc" || "kcmc".equals(listSet.get(j))){
						kcmc = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(kcmc)) {
							rowMessage += "课程名称不能为空；";
						}
						tempUserKB.setKcmc(kcmc);
					}
					if(listSet.get(j) == "kssj" || "kssj".equals(listSet.get(j))){
						kssj = list.get(i).get(listSet.get(j));
						if (StringUtils.isEmpty(kssj)) {
							rowMessage += "考试时间不能为空；";
						}
						tempUserKB.setKssj(kssj);
					}
					if(listSet.get(j) == "kssjxh" || "kssjxh".equals(listSet.get(j))){
						kssjxh = list.get(i).get(listSet.get(j));
						//kssjxh = FieldDictUtil.get(Constant.APPID, "exa_exam_time", "segment", kssjxh);
						if (StringUtils.isEmpty(kssjxh)) {
							rowMessage += "考试时间序号不能为空；";
						}
						tempUserKB.setKssjxh(kssjxh);
					}
				}else{
					rowMessage += "第" + (j + 1) + "列数据有问题，请仔细检查；";
				}

			}
			if (!StringUtils.isEmpty(rowMessage)) {
				errorMsg += br + "第" + (i + 1) + "行，" + rowMessage;
			} else {
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());
				tempUserKB.setEnabledFlag(1);
				userKnowledgeBaseList.add(tempUserKB);
			}


		}
		//验证上传数据在数据库是否已存在
		if(userKnowledgeBaseList.size() == 0){
			errorMsg += "没有数据，导入失败";
			return errorMsg;
		}
		List<StudentCourseTempDO> listk=studentCourseTempDao.listsc(userKnowledgeBaseList);
		if(listk.size()>0){
			errorMsg ="导入失败，姓名、准考证和考试时间序号分别为";
			for(StudentCourseTempDO spe : listk){
				errorMsg += spe.getKsXm()+"、"+spe.getKsZkz()+"、"+spe.getKssjxh();
			}
			errorMsg +="的信息数据库已存在,不能重复添加";
		}
		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(StudentCourseTempDO courseClassify : userKnowledgeBaseList){
				this.save(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

}
