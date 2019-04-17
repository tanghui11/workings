package com.hxy.nzxy.stexam.region.region.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.domain.ExamSiteDO;
import com.hxy.nzxy.stexam.region.region.service.ExamSiteRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考点维护
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:33
 */
 
@Controller
@RequestMapping("/region/examSiteRegion")
public class ExamSiteRegionController extends SystemBaseController{
	@Autowired
	private ExamSiteRegionService examSiteRegionService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("region:examSiteRegion:examSiteRegion")
	String ExamSiteRegion(@RequestParam Map<String, String> params ,Model model){
		String regType = ShiroUtils.getUser().getRegType();
		model.addAttribute("examSite",ShiroUtils.getUser().getWorkerid());

		model.addAttribute("reg_exam_site_status", commonService.listFieldDict(getAppName(), "reg_exam_site", "status"));
		if (regType.equals("a")){
			return "region/region/examSiteRegion/examSiteRegion";
		}else{
			/*ExamSiteDO examSiteRegion = examSiteRegionService.get(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
			model.addAttribute("examSiteRegion", examSiteRegion);*/
			return "region/region/examSiteRegion/examSiteRegionXian";
		}

	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examSiteRegion:examSiteRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		String regionid= String.valueOf(params.get("regionid"));

		if(regionid!=null&&!"".equals(regionid)) {
			List<ExamSiteDO> examSiteRegionList = examSiteRegionService.list(query);
			for (ExamSiteDO item : examSiteRegionList) {
				item.setStatus(FieldDictUtil.get(getAppName(), "reg_exam_site", "status", item.getStatus()));
				//item.setPhotoFlag(FieldDictUtil.get(getAppName(), "reg_region", "photo_flag", item.getPhotoFlag()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = examSiteRegionService.count(query);
			PageUtils pageUtils = new PageUtils(examSiteRegionList, total);
			return pageUtils;
		}else {
			return new PageUtils(new ArrayList<>(), 0);
		}
	}

	@GetMapping("/add")
	@RequiresPermissions("region:examSiteRegion:add")
	String add(@RequestParam Map<String, String> params,Model model){
		String regionid =params.get("regionid");
		model.addAttribute("regionid", regionid);
		model.addAttribute("reg_exam_site_school", commonService.listFieldDict(getAppName(), "reg_exam_site", "school"));
		model.addAttribute("reg_exam_site_standard", commonService.listFieldDict(getAppName(), "reg_exam_site", "standard"));
		model.addAttribute("reg_exam_site_status", commonService.listFieldDict(getAppName(), "reg_exam_site", "status"));
	    return "region/region/examSiteRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examSiteRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamSiteDO examSiteRegion = examSiteRegionService.get(id);
		model.addAttribute("reg_exam_site_school", commonService.listFieldDict(getAppName(), "reg_exam_site", "school"));
		model.addAttribute("reg_exam_site_standard", commonService.listFieldDict(getAppName(), "reg_exam_site", "standard"));
		model.addAttribute("reg_exam_site_status", commonService.listFieldDict(getAppName(), "reg_exam_site", "status"));
		model.addAttribute("examSiteRegion", examSiteRegion);
	    return "region/region/examSiteRegion/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examSiteRegion:add")
	public R save( ExamSiteDO examSiteRegion){
        examSiteRegion.setOperator(ShiroUtils.getUserId().toString());
        //examSiteRegion.setRegionid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
		if(examSiteRegionService.save(examSiteRegion)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(examSiteRegion.getId()+"");
			fieldDict.setFieldMean(examSiteRegion.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.saveCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("保存数据成功，修改缓存失败！");
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examSiteRegion:edit")
	public R update( ExamSiteDO examSiteRegion){
	    examSiteRegion.setOperator(ShiroUtils.getUserId().toString());
		if(examSiteRegionService.update(examSiteRegion)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(examSiteRegion.getId()+"");
			fieldDict.setFieldMean(examSiteRegion.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.updateCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("修改数据成功，修改缓存失败！");
			}
		}
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examSiteRegion:remove")
	public R remove( Long id){
		if(examSiteRegionService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id+"");
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
	@RequiresPermissions("region:examSiteRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		if(examSiteRegionService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			if(fieldDictService.batchremoveCache(fieldDict,CommonUtil.longToString(ids) )>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.ok();
	}



	/**
	 *考点管理导出
	 * @param params
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchOutEXExcel")
	public String searchOutEXExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request,Model model){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "examSiteRegion/";
		String strZipPath=configsrc+"examSiteRegionZip/";
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
		List<ExamSiteDO> examSiteList = examSiteRegionService.list(params);
		if(examSiteList.size() == 0){
			String regType = ShiroUtils.getUser().getRegType();
			model.addAttribute("examSite",ShiroUtils.getUser().getWorkerid());

			model.addAttribute("reg_exam_site_status", commonService.listFieldDict(getAppName(), "reg_exam_site", "status"));
			if (regType.equals("a")){
				return "region/region/examSiteRegion/examSiteRegion";
			}else{
			/*ExamSiteDO examSiteRegion = examSiteRegionService.get(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
			model.addAttribute("examSiteRegion", examSiteRegion);*/
				return "region/region/examSiteRegion/examSiteRegionXian";
			}
		}
		request.getSession().setAttribute("totalCount", examSiteList.size());
		for (ExamSiteDO item : examSiteList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "reg_exam_site", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (examSiteList != null && examSiteList.size() > 0) {
			String[][] result = new String[examSiteList.size() + 1][9];
			result[0] = new String[] {"序号","考点代码","名称","考场数量","联系人","联系电话","状态","操作员","操作日期"};
			if (examSiteList != null && examSiteList.size() > 0) {
				for (int i = 0; i < examSiteList.size(); i++) {
					ExamSiteDO courseFree = examSiteList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getId());
					result[i + 1][1] = String.valueOf(courseFree.getRegionid());
					result[i + 1][2] = String.valueOf(courseFree.getName());
					result[i + 1][3] = String.valueOf(courseFree.getNum());
					result[i + 1][4] = String.valueOf(courseFree.getLinkman());
					result[i + 1][5] = String.valueOf(courseFree.getPhone());
					result[i + 1][6] = String.valueOf(courseFree.getStatus());
					result[i + 1][7] = String.valueOf(courseFree.getOperator());
					result[i + 1][8] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/examSiteList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="考点管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="考点管理导出信息" +date+".zip";
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
			return "region/region/examSiteRegion/examSite";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", examSiteList.size());
		return null;
	}


	/*
	tanchupage
	 */
	@GetMapping("EXimportData")
	String EXimportDate(){
		return "region/region/examSiteRegion/EXimportData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/EXsavBathData")
	@RequiresPermissions("region:examSiteRegion:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "region/region/examSiteRegion/EXimportData";
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
			return "region/region/examSiteRegion/EXimportData";
		}

		//批量导入
		String message = examSiteRegionService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "region/region/examSiteRegion/EXimportData";
	}

}
