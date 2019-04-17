package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.center.region.dao.RegionDao;
import com.hxy.nzxy.stexam.center.region.service.RegionService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.ExcelImportUtils;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private FieldDictService fieldDictService;
	@Override
	public RegionDO get(Long id){
		return regionDao.get(id);
	}
	
	@Override
	public List<RegionDO> list(Map<String, Object> map){
		return regionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return regionDao.count(map);
	}
	
	@Override
	public int save(RegionDO region){
		return regionDao.save(region);
	}
	
	@Override
	public int update(RegionDO region){
		return regionDao.update(region);
	}
	
	@Override
	public int remove(Long id){
		return regionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return regionDao.batchRemove(ids);
	}

	@Override
	public int updateParentName(RegionDO region) {
		return regionDao.updateParentName(region);
	}

	@Override
	public String KbatchImport(String fileName, MultipartFile file){

		File uploadDir = new  File("E:\\fileupload");
		//创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!uploadDir.exists()) uploadDir.mkdirs();
		//新建一个文件
		File tempFile = new File("E:\\fileupload\\" + new Date().getTime() + ".xlsx");
		//初始化输入流
		InputStream is = null;
		try{
			//将上传的文件写入新建的文件中
			file.transferTo(tempFile);

			//根据新建的文件实例化输入流
			is = new FileInputStream(tempFile);
			//根据版本选择创建Workbook的方式
			Workbook wb = null;
			//根据文件名判断文件是2003版本还是2007版本
			if(ExcelImportUtils.isExcel2007(fileName)){
				wb = new XSSFWorkbook(is);
			}else{
				wb = new HSSFWorkbook(is);
			}
			//根据excel里面的内容读取知识库信息
			return readExcelValue(wb,tempFile);
		}catch(Exception e){
			e.printStackTrace();
		} finally{
			if(is !=null)
			{
				try{
					is.close();
				}catch(IOException e){
					is = null;
					e.printStackTrace();
				}
			}
		}
		return "导入出错！请检查数据格式！";



	}

//	public static String getPingYin(String src) {
//		char[] t1 = null;
//		t1 = src.toCharArray();
//		String[] t2 = new String[t1.length];
//		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
//		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
//		String t4 = "";
//		int t0 = t1.length;
//		try {
//			for (int i = 0; i < t0; i++) {
//				// 判断是否为汉字字符
//				if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
//					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
//					t4 += t2[0];
//				} else {
//					t4 += java.lang.Character.toString(t1[i]);
//				}
//			}
//			return t4;
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		return t4;
//	}
//	public static String getPinYinHeadChar(String str) throws BadHanyuPinyinOutputFormatCombination {
//
//		String convert = "";
//		for (int j = 0; j < str.length(); j++) {
//			char word = str.charAt(j);
//			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word,new HanyuPinyinOutputFormat());
//			if (pinyinArray != null) {
//				convert += pinyinArray[0].charAt(0);
//			} else {
//				convert += word;
//			}
//		}
//		return convert;
//	}
//	public static String getCnASCII(String cnStr) {
//		StringBuffer strBuf = new StringBuffer();
//		byte[] bGBK = cnStr.getBytes();
//		for (int i = 0; i < bGBK.length; i++) {
//			// System.out.println(Integer.toHexString(bGBK[i]&0xff));
//			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
//		}
//		return strBuf.toString();
//	}
// 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
private static int BEGIN = 45217;
	private static int END = 63486;

	// 按照声 母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
	// i, u, v都不做声母, 自定规则跟随前面的字母
	private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', };

	// 二十六个字母区间对应二十七个端点
	// GB2312码汉字区间十进制表示
	private static int[] table = new int[27];

	// 对应首字母区间表
	private static char[] initialtable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', };

	// 初始化
	static {
		for (int i = 0; i < 26; i++) {
			table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
		}
		table[26] = END;// 区间表结尾
	}

	// ------------------------public方法区------------------------
	// 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出

	public static String getPingYin(String SourceStr) {
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				Result += Char2Initial(SourceStr.charAt(i));
			}
		} catch (Exception e) {
			Result = "";
			e.printStackTrace();
		}
		return Result;
	}


	private static char Char2Initial(char ch) {
		if (ch >= 'a' && ch <= 'z') {
			return (char) (ch - 'a' + 'A');
		}
		if (ch >= 'A' && ch <= 'Z') {
			return ch;
		}
		int gb = gbValue(ch);
		if ((gb < BEGIN) || (gb > END))
		{
			return ch;
		}
		int i;
		for (i = 0; i < 26; i++) {
			if ((gb >= table[i]) && (gb < table[i + 1])) {
				break;
			}
		}
		if (gb == END) {
			i = 25;
		}
		return initialtable[i];
	}

	private static int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2) {
				return 0;
			}
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 解析Excel里面的数据
	 * @param wb Workbook的方式
	 * @param tempFile 临时文件
	 * @return
	 */
	private String readExcelValue(Workbook wb,File tempFile) {

		//错误信息接收器
		String errorMsg = "";
		//得到第一个shell,一般是字段的名称
		Sheet sheet=wb.getSheetAt(0);
		//得到Excel的行数
		int totalRows=sheet.getPhysicalNumberOfRows();
		//总列数
		int totalCells = 0;
		//得到Excel的列数(前提是有行数)，从第二行算起
		if(totalRows>=2 && sheet.getRow(1) != null){
			totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
		}
		List<RegionDO> userKnowledgeBaseList=new ArrayList<RegionDO>();//创建了一个courseFreeCenterDO类型的list
		RegionDO tempUserKB ;
		String br = "<br/>";

		//循环Excel行数,从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			String rowMessage = "";
			Row row = sheet.getRow(r);
			if (row == null) {
				errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
				continue;
			}
			tempUserKB = new RegionDO();//定义了一个空的courseFreeCenterDO

			String parentid = "";
			String code ="";
			String type ="";
			String name ="";
			String parent_name ="";
			String pinyin ="";
			String linkman ="";
			String address ="";
			String post_code ="";
			String phone ="";
			String mphone ="";
			String fax ="";
			String email ="";
			String photo_flag ="";
			String exam_transfer ="";
			int room_size = 0;
			String exam_msg ="";
			String seq ="00";

			//循环Excel的列
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						parentid = cell.getStringCellValue();
						if (parentid == null || "".equals(parentid) ) {
							rowMessage += "父考区不能为空；";
						}
						tempUserKB.setParentid(Long.parseLong(parentid));
					} else if (c == 1) {
						code = cell.getStringCellValue();
						if (StringUtils.isEmpty(code)) {
							rowMessage += "考区代码不能为空；";
						} else if (code.length() > 20) {
							rowMessage += "考区代码的长度不能超过20；";
						}
						tempUserKB.setCode(code);
					} else if (c == 2) {
						type = cell.getStringCellValue();
						type = FieldDictUtil.get(Constant.APPID, "reg_region", "type", type);
						if (StringUtils.isEmpty(type)) {
							rowMessage += "类别不能为空；";
						}
						tempUserKB.setType(type);
					} else if (c == 3) {
						name = cell.getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							rowMessage += "名称不能为空";
						}else if(name.length() > 100){
							rowMessage += "名称不可以超过50个字";
						}
						tempUserKB.setName(name);
					}else if (c == 4) {
						parent_name = cell.getStringCellValue();

						tempUserKB.setParentName(parent_name);
					}else if (c == 5) {
						pinyin = cell.getStringCellValue();

						if (StringUtils.isEmpty(pinyin)) {
							tempUserKB.setPinyin(getPingYin(name));
						}else{
							tempUserKB.setPinyin(pinyin);
						}
					}else if (c == 6) {
						linkman = cell.getStringCellValue();
						tempUserKB.setLinkman(linkman);
					}else if (c == 7) {
						address = cell.getStringCellValue();

						tempUserKB.setAddress(address);
					}else if (c == 8) {
						post_code = cell.getStringCellValue();

						tempUserKB.setPostCode(post_code);
					}else if (c == 9) {
						phone = cell.getStringCellValue();

						tempUserKB.setPhone(phone);
					}else if (c == 10) {
						mphone = cell.getStringCellValue();

						tempUserKB.setMphone(mphone);
					}else if (c == 11) {
						fax = cell.getStringCellValue();

						tempUserKB.setFax(fax);
					}else if (c == 12) {
						email = cell.getStringCellValue();

						tempUserKB.setEmail(email);
					}else if(c == 13){
						photo_flag = cell.getStringCellValue();
						photo_flag = FieldDictUtil.get(Constant.APPID, "reg_region", "photo_flag", photo_flag);
						if (StringUtils.isEmpty(photo_flag)) {
							rowMessage += "是否有摄像头不能为空";
						}else if(photo_flag.length() > 1){
							rowMessage += "是否有摄像头的长度不能超过1";
						}
						tempUserKB.setPhotoFlag(photo_flag);
				} else if (c == 14) {
						exam_transfer = cell.getStringCellValue();

						tempUserKB.setExamTransfer(exam_transfer);
					}else if (c == 15) {
						room_size = Integer.parseInt(cell.getStringCellValue());

						tempUserKB.setName(String.valueOf(room_size));
					}else if (c == 16) {
						exam_msg = cell.getStringCellValue();

						tempUserKB.setExamMsg(exam_msg);
					}else if (c == 17) {
					seq = cell.getStringCellValue();

					tempUserKB.setSeq(seq);
				}
				}else {
					rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
				}

			}
			//拼接每行的错误提示
			if (!StringUtils.isEmpty(rowMessage)) {
				errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
			} else {
				tempUserKB.setOperator(ShiroUtils.getUserId().toString());//添加操作人
				tempUserKB.setEnabledFlag(1);
				userKnowledgeBaseList.add(tempUserKB);//添加信息到userKnowledgeBaseList
			}
		}
		//删除上传的临时文件
		if(tempFile.exists()){
			tempFile.delete();
		}
		//验证上传数据在数据库是否已存在
		List<RegionDO> list=regionDao.listK(userKnowledgeBaseList);
		if(list.size()>0){
			errorMsg ="导入失败，代码为";
			for(RegionDO cfc : list){
				errorMsg += cfc.getCode()+",";
			}
			errorMsg +="已存在,不能重复添加";
		}



		//全部验证通过才导入到数据库
		if(StringUtils.isEmpty(errorMsg)){
			for(RegionDO reg : userKnowledgeBaseList){
				this.save(reg);
				FieldDictDO fieldDict = new FieldDictDO();
				fieldDict.setAppid(Constant.APPID);
				fieldDict.setTableName("reg_region_nzxy");
				fieldDict.setFieldName("id");
				fieldDict.setFieldValue(reg.getId()+"");
				fieldDict.setFieldMean(reg.getName());
				fieldDictService.saveCache(fieldDict);
				fieldDict.setTableName("reg_region_nzxy_b");
				fieldDictService.saveCache(fieldDict);
				fieldDict.setTableName("reg_region_nzxy");
				fieldDict.setFieldName("code");
				fieldDict.setFieldValue(reg.getCode()+"");
				fieldDictService.saveCache(fieldDict);
				if(reg.getType().equals("b")){
					FieldDictDO fieldDict1 = new FieldDictDO();
					fieldDict1.setAppid(Constant.APPID);
					fieldDict1.setTableName("reg_region_nzxy_b");
					fieldDict1.setFieldName("id");
					fieldDict1.setFieldValue(reg.getId()+"");
					fieldDict1.setFieldMean(reg.getName());
					fieldDictService.saveCache(fieldDict1);
				}

			}
			errorMsg = "导入成功，共"+userKnowledgeBaseList.size()+"条数据！";
		}
		return errorMsg;
	}

}
