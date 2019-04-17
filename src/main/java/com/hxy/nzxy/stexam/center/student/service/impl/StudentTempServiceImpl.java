package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CertificateReplaceDO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hxy.nzxy.stexam.center.student.dao.StudentTempDao;
import com.hxy.nzxy.stexam.domain.StudentTempDO;
import com.hxy.nzxy.stexam.center.student.service.StudentTempService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StudentTempServiceImpl implements StudentTempService {
	@Autowired
	private StudentTempDao studentTempDao;
	
	@Override
	public StudentTempDO get(String ksZkz){
		return studentTempDao.get(ksZkz);
	}
	
	@Override
	public List<StudentTempDO> list(Map<String, Object> map){
		return studentTempDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentTempDao.count(map);
	}

	@Override
	public int isNotExist(String studentid){
		return studentTempDao.isNotExist(studentid);
	}
	
	@Override
	public int save(StudentTempDO studentTemp){
		return studentTempDao.save(studentTemp);
	}

	@Override
	public int ZSsave(StudentTempDO studentTemp){
		return studentTempDao.ZSsave(studentTemp);
	}
	
	@Override
	public int update(StudentTempDO studentTemp){
		return studentTempDao.update(studentTemp);
	}
	
	@Override
	public int remove(String type){
		return studentTempDao.remove(type);
	}

	@Override
	public int moveOut(String studentid){
		return studentTempDao.moveOut(studentid);
	}
	
	@Override
	public int batchRemove(String[] types){
		return studentTempDao.batchRemove(types);
	}

	@Override
	public Integer delete(String studentid){
		return studentTempDao.delete(studentid);
	}

	DbfReadUtil dbfReadUtil = new DbfReadUtil();
	/**
	 * 批量导入考生基本信息到临时表
	 * @param fileName
	 * @param file
	 * @return
	 */
	@Override
	public String batchImport(String fileName, MultipartFile file, String type) throws IOException, ParseException {
		List<Map<String,String>> list = dbfReadUtil.readDbf(file);
		int num = dbfReadUtil.readDbf1(file);
		return readExcelValue(list,num, type);
	}


	/**
	 * 解析Excel里面的数据
	 * @return
	 */
	private String readExcelValue(List<Map<String,String>> list, int num, String type) throws ParseException {


		List<StudentTempDO> userKnowledgeBaseList=new ArrayList<>();//创建了一个courseFreeCenterDO类型的list
		StudentTempDO tempUserKB;
		String br = "<br/>";
        List listSet = null;
        Set keys = null;
        String errorMsg = "";

		//循环Excel行数,从第二行开始。标题不入库
        for (int i =0; i<list.size();i++){

			tempUserKB = new StudentTempDO();
			String ks_zkz    ="";
			String ks_xm     ="";
			String ks_exm    ="";
			String ks_xb     ="";
			String hj_dm     ="";
			String mm_dm     ="";
			String mz_dm     ="";
			String xl_dm     ="";
			String zhiy_dm   ="";
			String zy_dm     ="";
			String first_zy  ="";
			String jtdm_dm   ="";
			String ks_sfz    ="";
			String ks_birthda="";
			String ks_phone  ="";
			String ks_zip    ="";
			String ks_address="";
			String ks_bmxs   ="";
			String ks_bmsj   ="";
			String ks_zdyxx  ="";
			String ks_tksj   ="";
			String ks_yqby   ="";
			String ks_qx     ="";
			String sx_dm     ="";
			String modified  ="";
			String ks_oldzkz ="";
			String zy_mc     ="";
			String mm	     ="";
			String sj1       ="";
			String sj2       ="";
			String zzdh      ="";
			String ybmh      ="";
			String bmddm     ="";
			String gzdw      ="";
			String email     ="";
			String msdm      ="";
			String hkszd     ="";
			String gzdwszd   ="";
			String flag      ="";
			//循环Excel的列
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            keys = list.get(i).keySet();
            listSet = new ArrayList();
            String rowMessage = "";
            listSet.addAll(keys);
            for (int j =0; j<num;j++){

                if (list.get(i)!= null) {
                    if (listSet.get(j) == "ks_zkz" || "ks_zkz".equals(listSet.get(j))) {
                        ks_zkz = list.get(i).get(listSet.get(j));
                        if (StringUtils.isEmpty(ks_zkz)) {
                            rowMessage += "准考证号不能为空；";
                        }else if (ks_zkz.length() > 12){
                            rowMessage += "准考证号的长度不能超过12";
                        }
                        tempUserKB.setKsZkz(ks_zkz);

                    }else if (listSet.get(j) == "ks_xm" || "ks_xm".equals(listSet.get(j))) {
                        ks_xm = list.get(i).get(listSet.get(j));
                        if (StringUtils.isEmpty(ks_xm)) {
                            rowMessage += "姓名不能为空；";
                        }else if (ks_xm.length() > 64){
                            rowMessage += "姓名的长度不能超过64";
                        }
                        tempUserKB.setKsXm(ks_xm);

                    }else if (listSet.get(j) == "ks_exm" || "ks_exm".equals(listSet.get(j))) {
                        ks_exm = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsExm(ks_exm);

                    }else if (listSet.get(j) == "ks_xb" || "ks_xb".equals(listSet.get(j))) {
                        ks_xb = list.get(i).get(listSet.get(j));
                        //ks_xb = FieldDictUtil.get(Constant.APPID, "stu_student", "gender", ks_xb);
                        tempUserKB.setKsXb(ks_xb);

                    }else if (listSet.get(j) == "hj_dm" || "hj_dm".equals(listSet.get(j))) {
                        hj_dm = list.get(i).get(listSet.get(j));
                        tempUserKB.setHjDm(hj_dm);

                    }else if (listSet.get(j) == "mm_dm" || "mm_dm".equals(listSet.get(j))) {
                        mm_dm = list.get(i).get(listSet.get(j));
                        tempUserKB.setMmDm(mm_dm);

                    }else if (listSet.get(j) == "mz_dm" || "mz_dm".equals(listSet.get(j))) {
                        mz_dm = list.get(i).get(listSet.get(j));
                        tempUserKB.setMzDm(mz_dm);

                    }else if (listSet.get(j) == "xl_dm" || "xl_dm".equals(listSet.get(j))) {
                        xl_dm = list.get(i).get(listSet.get(j));
                        tempUserKB.setXlDm(xl_dm);

                    }else if (listSet.get(j) == "zhiy_dm" || "zhiy_dm".equals(listSet.get(j))) {
                        zhiy_dm = list.get(i).get(listSet.get(j));
                        tempUserKB.setZhiyDm(zhiy_dm);

                    }else if (listSet.get(j) == "zy_dm" || "zy_dm".equals(listSet.get(j))) {
                        zy_dm = list.get(i).get(listSet.get(j));
                        tempUserKB.setZyDm(zy_dm);

                    }else if (listSet.get(j) == "first_zy" || "first_zy".equals(listSet.get(j))) {
                        first_zy = list.get(i).get(listSet.get(j));
                        tempUserKB.setFirstZy(first_zy);

                    }else if (listSet.get(j) == "jtdm_dm" || "jtdm_dm".equals(listSet.get(j))) {
                        jtdm_dm = list.get(i).get(listSet.get(j));
                        tempUserKB.setJtdmDm(jtdm_dm);

                    }else if (listSet.get(j) == "ks_sfz" || "ks_sfz".equals(listSet.get(j))) {
                        ks_sfz = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsSfz(ks_sfz);

                    }else if (listSet.get(j) == "ks_birthda" || "ks_birthda".equals(listSet.get(j))) {
                        ks_birthda = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsBirthda(new Date(ks_birthda));

                    }else if (listSet.get(j) == "ks_phone" || "ks_phone".equals(listSet.get(j))) {
                        ks_phone = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsPhone(ks_phone);

                    }else if (listSet.get(j) == "ks_zip" || "ks_zip".equals(listSet.get(j))) {
                        ks_zip = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsZip(ks_zip);

                    }else if (listSet.get(j) == "ks_address" || "ks_address".equals(listSet.get(j))) {
                        ks_address = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsAddress(ks_address);

                    }else if (listSet.get(j) == "ks_bmxs" || "ks_bmxs".equals(listSet.get(j))) {
                        ks_bmxs = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsBmxs(ks_bmxs);

                    }else if (listSet.get(j) == "ks_bmsj" || "ks_bmsj".equals(listSet.get(j))) {
                        ks_bmsj = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsBmsj(new Date(ks_bmsj));

                    }else if (listSet.get(j) == "ks_zdyxx" || "ks_zdyxx".equals(listSet.get(j))) {
                        ks_zdyxx = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsZdyxx(ks_zdyxx);

                    }else if (listSet.get(j) == "ks_tksj" || "ks_tksj".equals(listSet.get(j))) {
                        ks_tksj = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsTksj(new Date(ks_tksj));

                    }else if (listSet.get(j) == "ks_yqby" || "ks_yqby".equals(listSet.get(j))) {
                        ks_yqby = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsYqby(ks_yqby);

                    }else if (listSet.get(j) == "ks_qx" || "ks_qx".equals(listSet.get(j))) {
                        ks_qx = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsQx(ks_qx);

                    }else if (listSet.get(j) == "sx_dm" || "sx_dm".equals(listSet.get(j))) {
                        sx_dm = list.get(i).get(listSet.get(j));
                        tempUserKB.setSxDm(sx_dm);

                    }else if (listSet.get(j) == "modified" || "modified".equals(listSet.get(j))) {
                        modified = list.get(i).get(listSet.get(j));
                        tempUserKB.setModified(modified);

                    }else if (listSet.get(j) == "ks_oldzkz" || "ks_oldzkz".equals(listSet.get(j))) {
                        ks_oldzkz = list.get(i).get(listSet.get(j));
                        tempUserKB.setKsOldzkz(ks_oldzkz);

                    }else if (listSet.get(j) == "zy_mc" || "zy_mc".equals(listSet.get(j))) {
                        zy_mc = list.get(i).get(listSet.get(j));
                        tempUserKB.setZyMc(zy_mc);

                    }else if (listSet.get(j) == "mm" || "mm".equals(listSet.get(j))) {
                        mm = list.get(i).get(listSet.get(j));
                        tempUserKB.setMm(mm);

                    }else if (listSet.get(j) == "sj1" || "sj1".equals(listSet.get(j))) {
                        sj1 = list.get(i).get(listSet.get(j));
                        tempUserKB.setSj1(sj1);

                    }else if (listSet.get(j) == "sj2" || "sj2".equals(listSet.get(j))) {
                        sj2 = list.get(i).get(listSet.get(j));
                        tempUserKB.setSj2(sj2);

                    }else if (listSet.get(j) == "zzdh" || "zzdh".equals(listSet.get(j))) {
                        zzdh = list.get(i).get(listSet.get(j));
                        tempUserKB.setZzdh(zzdh);

                    }else if (listSet.get(j) == "ybmh" || "ybmh".equals(listSet.get(j))) {
                        ybmh = list.get(i).get(listSet.get(j));
                        tempUserKB.setYbmh(ybmh);

                    }else if (listSet.get(j) == "bmddm" || "bmddm".equals(listSet.get(j))) {
                        bmddm = list.get(i).get(listSet.get(j));
                        tempUserKB.setBmddm(bmddm);

                    }else if (listSet.get(j) == "gzdw" || "gzdw".equals(listSet.get(j))) {
                        gzdw = list.get(i).get(listSet.get(j));
                        tempUserKB.setGzdw(gzdw);

                    }else if (listSet.get(j) == "email" || "email".equals(listSet.get(j))) {
                        email = list.get(i).get(listSet.get(j));
                        tempUserKB.setEmail(email);

                    }else if (listSet.get(j) == "msdm" || "msdm".equals(listSet.get(j))) {
                        msdm = list.get(i).get(listSet.get(j));
                        tempUserKB.setMsdm(msdm);

                    }else if (listSet.get(j) == "hkszd" || "hkszd".equals(listSet.get(j))) {
                        hkszd = list.get(i).get(listSet.get(j));
                        tempUserKB.setHkszd(hkszd);

                    }else if (listSet.get(j) == "gzdwszd" || "gzdwszd".equals(listSet.get(j))) {
                        gzdwszd = list.get(i).get(listSet.get(j));
                        tempUserKB.setGzdwszd(gzdwszd);

                    }else if (listSet.get(j) == "flag" || "flag".equals(listSet.get(j))) {
                        flag = list.get(i).get(listSet.get(j));
                        tempUserKB.setFlag(flag);

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
				tempUserKB.setType(type);
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
		//验证上传数据在数据库是否已存在
		List<StudentTempDO> listw=studentTempDao.listW(userKnowledgeBaseList);
		if(listw.size()>0){
			errorMsg ="导入失败，准考证分别为";
			for(StudentTempDO spe : listw){
				errorMsg += spe.getKsZkz();
			}
			errorMsg +="的信息数据库已存在,不能重复添加";
		}
		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(StudentTempDO courseClassify : userKnowledgeBaseList){
				this.save(courseClassify);
			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}
}
