package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.service.ScoreService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.DbfReadUtil;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.StudentTempDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hxy.nzxy.stexam.center.student.dao.ScoreImportDao;
import com.hxy.nzxy.stexam.domain.ScoreImportDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreImportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ScoreImportServiceImpl implements ScoreImportService {
	@Autowired
	private ScoreImportDao scoreImportDao;
	@Autowired
	private ScoreService scoreService;

	@Override
	public ScoreImportDO get(String kemuid){
		return scoreImportDao.get(kemuid);
	}

	@Override
	public List<ScoreImportDO> list(Map<String, Object> map){
		return scoreImportDao.list(map);
	}

    @Override
    public List<ScoreImportDO> listAll(){
        return scoreImportDao.listAll();
    }

//	@Override
//	public ScoreImportDO selectStudentid(String kemuid,String code){
//		return scoreImportDao.selectStudentid(kemuid, code);
//	}

	@Override
	public int ifPutIn(String code, String examTaskid, String kemuid){
		return scoreImportDao.ifPutIn(code,examTaskid,kemuid);
	}

	@Override
	public int setEnabled(String kemuid,String code){
		return scoreImportDao.setEnabled(kemuid, code);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreImportDao.count(map);
	}

	@Override
	public int delete(){
		return scoreImportDao.delete();
	}
	
	@Override
	public int save(ScoreImportDO scoreImport){
		return scoreImportDao.save(scoreImport);
	}

	@Override
	public int putInFormation(ScoreImportDO scoreImportDO){
		return scoreImportDao.putInFormation(scoreImportDO);
	}
	
	@Override
	public int update(ScoreImportDO scoreImport){
		return scoreImportDao.update(scoreImport);
	}
	
	@Override
	public int remove(String kemuid){
		return scoreImportDao.remove(kemuid);
	}
	
	@Override
	public int batchRemove(String[] kemuids){
		return scoreImportDao.batchRemove(kemuids);
	}

	DbfReadUtil dbfReadUtil = new DbfReadUtil();
	/**
	 * 批量导入考生基本信息到临时表
	 * @param fileName
	 * @param file
	 * @return
	 */
	@Override
	public String batchImport(String fileName, MultipartFile file) throws IOException, ParseException {
		List<Map<String,String>> list = dbfReadUtil.readDbf(file);
		int num = dbfReadUtil.readDbf1(file);
		return readExcelValue(list,num);
	}


	/**
	 * 解析Excel里面的数据
	 * @return
	 */
	private String readExcelValue(List<Map<String,String>> list, int num) throws ParseException {


		List<ScoreImportDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		ScoreImportDO tempUserKB;
		String br = "<br/>";
		List listSet = null;
		Set keys = null;
		String errorMsg = "";

		//循环Excel行数,从第二行开始。标题不入库
		for (int i =0; i<list.size();i++){

			tempUserKB = new ScoreImportDO();
			String	kemuid    ="";
			String	code      ="";
			String	name      ="";
			String	totalscore="";
			String	zgscore   ="";
			String	omrscore  ="";
			String	qk        ="";
			String	wj        ="";

			keys = list.get(i).keySet();
			listSet = new ArrayList();
			String rowMessage = "";
			listSet.addAll(keys);
			for (int j =0; j<num;j++){

				if (list.get(i)!= null) {
					if (listSet.get(j) == "kemuid" || "kemuid".equals(listSet.get(j))) {
						kemuid = list.get(i).get(listSet.get(j));
						tempUserKB.setKemuid(kemuid);

					}else if (listSet.get(j) == "code" || "code".equals(listSet.get(j))) {
						code = list.get(i).get(listSet.get(j));
						//code = FieldDictUtil.get(Constant.APPID, "stu_student", "gender", code);
						tempUserKB.setCode(code);

					}else if (listSet.get(j) == "name" || "name".equals(listSet.get(j))) {
						name = list.get(i).get(listSet.get(j));
						tempUserKB.setName(name);

					}else if (listSet.get(j) == "totalscore" || "totalscore".equals(listSet.get(j))) {
						totalscore = list.get(i).get(listSet.get(j));
						tempUserKB.setTotalscore(Float.parseFloat(totalscore));

					}else if (listSet.get(j) == "zgscore" || "zgscore".equals(listSet.get(j))) {
						zgscore = list.get(i).get(listSet.get(j));
						tempUserKB.setZgscore(Float.parseFloat(zgscore));

					}else if (listSet.get(j) == "omrscore" || "omrscore".equals(listSet.get(j))) {
						omrscore = list.get(i).get(listSet.get(j));
						tempUserKB.setOmrscore(Float.parseFloat(omrscore));

					}else if (listSet.get(j) == "qk" || "qk".equals(listSet.get(j))) {
						qk = list.get(i).get(listSet.get(j));
						qk = FieldDictUtil.get(Constant.APPID, "stu_score_import", "qk", qk);
						tempUserKB.setQk(qk);

					}else if (listSet.get(j) == "wj" || "wj".equals(listSet.get(j))) {
						wj = list.get(i).get(listSet.get(j));
						wj = FieldDictUtil.get(Constant.APPID, "stu_score_import", "wj", wj);
						tempUserKB.setWj(wj);

					}
				} else {
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
//		//删除上传的临时文件
//		if(tempFile.exists()){
//			tempFile.delete();
//		}

        if(userKnowledgeBaseList.size() == 0){
            errorMsg += "没有找到导入数据";
            return errorMsg;
        }
//        String scoreid = "";
//		String studentid = "";
//		Map<String,Object> map = new HashMap<String, Object>();

		//验证上传数据在数据库是否已存在
		List<ScoreImportDO> listw=scoreImportDao.listW(userKnowledgeBaseList);
		if(listw.size()>0){
			errorMsg ="导入失败，准考证和姓名分别为";
			for(ScoreImportDO spe : listw){
				errorMsg += spe.getCode()+"、"+spe.getName();
//				map.put("scoreid",spe.getKemuid());
////				map.put("studentid",spe.getCode());
			}
			errorMsg +="的信息数据库已存在,不能重复添加";
		}

		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(ScoreImportDO courseClassify : userKnowledgeBaseList){
				this.save(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

}
