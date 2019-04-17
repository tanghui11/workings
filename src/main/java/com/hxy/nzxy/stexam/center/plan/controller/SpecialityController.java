package com.hxy.nzxy.stexam.center.plan.controller;

import com.alibaba.fastjson.JSON;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 专业管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-07-30 10:05:51
 */
 
@Controller
@RequestMapping("/plan/speciality")
public class SpecialityController extends SystemBaseController {
	@Autowired
	private SpecialityService specialityService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("plan:speciality:speciality")
	String Speciality(Model model){

		model.addAttribute("pla_speciality_classify", commonService.listFieldDict(getAppName(), "pla_speciality", "classify"));

		return "center/plan/speciality/speciality";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("plan:speciality:speciality")
	public PageUtils list (@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SpecialityDO> specialityList = specialityService.list(query);
		for (SpecialityDO item : specialityList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_speciality", "classify", item.getClassify()));
			item.setFlag(FieldDictUtil.get(getAppName(), "pla_speciality", "flag", item.getFlag()));
			item.setGrantType(FieldDictUtil.get(getAppName(), "pla_speciality", "grant_type", item.getGrantType()));
            item.setOperator(UserUtil.getName(item.getOperator()));
			if(item.getZkSpecialityid()!=null&& !"".equals(item.getZkSpecialityid())) {
				item.setZkSpecialityid(item.getZkSpecialityid() + "  " + FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", String.valueOf(item.getZkSpecialityid())));
			}
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		int total = specialityService.count(query);
		PageUtils pageUtils = new PageUtils(specialityList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("plan:speciality:add")
	String add( Model model){

		model.addAttribute("pla_speciality_type", commonService.listFieldDict(getAppName(), "pla_speciality", "type"));
		model.addAttribute("pla_speciality_classify", commonService.listFieldDict(getAppName(), "pla_speciality", "classify"));
		model.addAttribute("pla_speciality_flag", commonService.listFieldDict(getAppName(), "pla_speciality", "flag"));
		model.addAttribute("pla_speciality_grant_type", commonService.listFieldDict(getAppName(), "pla_speciality", "grant_type"));

		return "center/plan/speciality/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:speciality:edit")
	String edit(@PathVariable("id") String id,Model model){
		SpecialityDO speciality = specialityService.get(id);



		model.addAttribute("pla_speciality_type", commonService.listFieldDict(getAppName(), "pla_speciality", "type"));
		model.addAttribute("pla_speciality_classify", commonService.listFieldDict(getAppName(), "pla_speciality", "classify"));
		model.addAttribute("pla_speciality_flag", commonService.listFieldDict(getAppName(), "pla_speciality", "flag"));
		model.addAttribute("pla_speciality_grant_type", commonService.listFieldDict(getAppName(), "pla_speciality", "grant_type"));
		if(speciality.getZkSpecialityid()!=null&& !"".equals(speciality.getZkSpecialityid())) {
			speciality.setZkSpecialityid(speciality.getZkSpecialityid() + "  " + FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", String.valueOf(speciality.getZkSpecialityid())));
		}
		model.addAttribute("speciality", speciality);
	    return "center/plan/speciality/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:speciality:add")
	public R save( SpecialityDO speciality){
		speciality.setOperator(ShiroUtils.getUserId().toString());
		if(specialityService.save(speciality)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_speciality_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(speciality.getId());
			fieldDict.setFieldMean(speciality.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.saveCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("添加数据成功，添加缓存失败！");
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:speciality:edit")
	public R update( SpecialityDO speciality){
		speciality.setOperator(ShiroUtils.getUserId().toString());
		if(specialityService.update(speciality)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_speciality_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(speciality.getId());
			fieldDict.setFieldMean(speciality.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.updateCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("修改数据成功，修改缓存失败！");
			}
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:speciality:remove")
	public R remove( String id){
		if(specialityService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_speciality_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id);
			if(fieldDictService.removeCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:speciality:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		if(specialityService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("pla_speciality_nzxy");
			fieldDict.setFieldName("id");
			if(fieldDictService.batchremoveCache(fieldDict ,ids)>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.ok();
	}


	/**
	 * 显示弹出页面
	 * @param
	 * @return
	 */
	@GetMapping("/showSubject")
	String showSubject( ){
		return "center/plan/speciality/showSubject";
	}

	/**
	 * 显示弹出页面转出
	 * @param
	 * @return
	 */
	@GetMapping("/showSubjectZc")
	String showSubjectZc( ){
		return "center/plan/speciality/showSubjectZc";
	}
	//专业导入页面
	@GetMapping("/importData")
	@RequiresPermissions("plan:speciality:savBathData")
	String importData( ){

		return "center/plan/speciality/importData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("plan:speciality:savBathData")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
						 HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException{
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/speciality/importData";
		}

		//获取文件名
		String fileName=file.getOriginalFilename();

		//验证文件名是否合格
	/*	if(!ExcelImportUtils.validateExcel(fileName)){
			session.setAttribute("msg","文件必须是excel格式！");
			return "redirect:toUserKnowledgeImport";
		}*/

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size=file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/speciality/importData";
		}

		//批量导入
		String message = specialityService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/plan/speciality/importData";
	}

	@RequestMapping("/searchOutExcel")
	@RequiresPermissions("plan:speciality:searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){

		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");

		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "Speciality/";
		String strZipPath=configsrc+"SpecialityZip/";
		File file = new File(Filepath);
		if (!file.exists()) {
			file.mkdirs();
		}else {
			String[] tempList = file.list();
			File tempFile = null;
			for (int i = 0; i < tempList.length; i++) {
				if (Filepath.endsWith("/")) {
					tempFile = new File(Filepath+tempList[i]);
				}else {
					tempFile=new File(Filepath+"/"+tempList[i]);
				}
				if (tempFile.isFile()) {
					tempFile.delete();
				}
			}
		}
		File fileZip = new File(strZipPath);
		if (!fileZip.exists()) {
			fileZip.mkdirs();
		}else {
			String[] tempList = fileZip.list();
			File tempFile = null;
			for (int i = 0; i < tempList.length; i++) {
				if (Filepath.endsWith("/")) {
					tempFile = new File(Filepath+tempList[i]);
				}else {
					tempFile=new File(Filepath+"/"+tempList[i]);
				}
				if (tempFile.isFile()) {
					tempFile.delete();
				}
			}
		}
		ZipOutputStream out=null;
		List<SpecialityDO> specialityList = specialityService.list(params);
		request.getSession().setAttribute("totalCount", specialityList.size());
		for (SpecialityDO item : specialityList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_speciality", "classify", item.getClassify()));
			item.setFlag(FieldDictUtil.get(getAppName(), "pla_speciality", "flag", item.getFlag()));
			item.setGrantType(FieldDictUtil.get(getAppName(), "pla_speciality", "grant_type", item.getGrantType()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			if(item.getZkSpecialityid()!=null&& !"".equals(item.getZkSpecialityid())) {
				item.setZkSpecialityid(item.getZkSpecialityid() + "  " + FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", String.valueOf(item.getZkSpecialityid())));
			}
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		if (specialityList != null && specialityList.size() > 0) {
			String[][] result = new String[specialityList.size() + 1][12];

			result[0] = new String[] { "序号", "专业代码", "专业名称", "拼音", "类别", "专业层次", "层次类型", "委托类型","学分要求","专科名称","操作员","操作日期"};
			if (specialityList != null && specialityList.size() > 0) {
				for (int i = 0; i < specialityList.size(); i++) {
					SpecialityDO speciality = specialityList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(speciality.getId());
					result[i + 1][2] = String.valueOf(speciality.getName());
					result[i + 1][3] = String.valueOf(speciality.getPinyin());
					result[i + 1][4] = String.valueOf(speciality.getType());
					result[i + 1][5] = String.valueOf(speciality.getClassify());
					result[i + 1][6] = String.valueOf(speciality.getFlag());
					result[i + 1][7] = String.valueOf(speciality.getGrantType());
					result[i + 1][8] = String.valueOf(speciality.getScore());
					result[i + 1][9] = String.valueOf(speciality.getZkSpecialityid());
					result[i + 1][10] = String.valueOf(speciality.getOperator());
					result[i + 1][11] = String.valueOf(DateUtils.format(speciality.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/specialityList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="专业信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="专业信息" +date+".zip";
			ZipUtils.createZip(Filepath,strZipPath+zipName);
			try {
				File fs = new File(strZipPath+zipName);
				//检查该文件是否存在
				if(!fs.exists()){
					return null;
				}
				buffer1 = new BufferedInputStream(new FileInputStream(fs));
				byte[] buf = new byte[1024];
				int len = 0;
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-disposition", "attachment;filename="+new String(fs.getName().getBytes("gbk"), "iso8859-1"));
				out1 = response.getOutputStream();
				while((len = buffer1.read(buf)) >0){
					out1.write(buf,0,len);
				}
			}catch(Throwable e)
			{
			}finally
			{
				try
				{
					buffer1.close();
					out1.close();
				}catch(Throwable e)
				{

					e.printStackTrace();
				}
			}
		}
		else
		{
			return "center/plan/speciality/speciality";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", specialityList.size());

		return null;
	}



}
