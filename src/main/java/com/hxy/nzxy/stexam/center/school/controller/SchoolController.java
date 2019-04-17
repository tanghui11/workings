package com.hxy.nzxy.stexam.center.school.controller;

import com.hxy.nzxy.stexam.center.region.service.RegionService;
import com.hxy.nzxy.stexam.center.school.service.SchoolService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.SchoolDO;
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
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 助学组织
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/school")
public class SchoolController extends SystemBaseController {
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("school:school:school")
	String School(Model model){

		model.addAttribute("sch_school_type", commonService.listFieldDict(getAppName(), "sch_school", "type"));
		model.addAttribute("sch_school_status", commonService.listFieldDict(getAppName(), "sch_school", "status"));
		return "center/school/school/school";
	}
	@GetMapping("/schoolOpen")
	String schoolOpen(Model model){

		model.addAttribute("sch_school_type", commonService.listFieldDict(getAppName(), "sch_school", "type"));
		model.addAttribute("sch_school_status", commonService.listFieldDict(getAppName(), "sch_school", "status"));
		return "center/school/school/schoolOpen";
	}
	@ResponseBody
	@GetMapping("/list/{regionid}")
	@RequiresPermissions("school:school:school")
	public PageUtils list(@RequestParam Map<String, Object> params,@PathVariable("regionid") String regionid, HttpServletRequest request){
		//查询列表数据

		request.getSession().setAttribute("regionid",regionid);
		params.put("regionid", regionid);
		Query query = new Query(params);
		List<SchoolDO> schoolList = schoolService.list(query);
		for (SchoolDO item : schoolList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = schoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:school:school")
	public PageUtils list(@RequestParam Map<String, Object> params, HttpServletRequest request){
		//查询列表数据
		Query query = new Query(params);
		List<SchoolDO> schoolList = schoolService.list(query);
		for (SchoolDO item : schoolList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = schoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("school:school:add")
	String add(Model model,HttpServletRequest request){
		/*String  regionid= (String) request.getSession().getAttribute("regionid");
		RegionDO region = regionService.get(Long.valueOf(regionid));
		RegionDO region1=regionService.get(region.getParentid());
		if(region1!=null){
			model.addAttribute("regionSheng",region1.getName());
		}
		model.addAttribute("regionXian",region.getName());*/
		model.addAttribute("sch_school_type", commonService.listFieldDict(getAppName(), "sch_school", "type"));
		model.addAttribute("sch_school_status", commonService.listFieldDict(getAppName(), "sch_school", "status"));

		return "center/school/school/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:school:edit")
	String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request){
		String  regionid= (String) request.getSession().getAttribute("regionid");
		SchoolDO school = schoolService.get(id);

		RegionDO region = regionService.get(Long.valueOf(school.getRegionid()));
	/*	RegionDO region1=regionService.get(region.getParentid());
		if(region1!=null){
			model.addAttribute("regionSheng",region1.getName());

		}*/
		model.addAttribute("school",school);
		model.addAttribute("regionXian",region.getName());
		model.addAttribute("sch_school_type", commonService.listFieldDict(getAppName(), "sch_school", "type"));
		model.addAttribute("sch_school_status", commonService.listFieldDict(getAppName(), "sch_school", "status"));

		return "center/school/school/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:school:add")
	public R save( SchoolDO school,HttpServletRequest request){
		/*String  regionid= (String) request.getSession().getAttribute("regionid");
		school.setRegionid(Long.valueOf(regionid));*/
		school.setOperator(ShiroUtils.getUserId().toString());
		if(schoolService.save(school)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("sch_school_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(school.getId()+"");
			fieldDict.setFieldMean(school.getName());
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
	@RequiresPermissions("school:school:edit")
	public R update( SchoolDO school){
		school.setOperator(ShiroUtils.getUserId().toString());
		if(schoolService.update(school)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("sch_school_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(school.getId()+"");
			fieldDict.setFieldMean(school.getName());
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
	@RequiresPermissions("school:school:remove")
	public R remove( Long id){
		if(schoolService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("sch_school_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id+"");
			fieldDict.setSeq(0);
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
	@RequiresPermissions("school:school:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		if(schoolService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("sch_school_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setSeq(0);
			if(fieldDictService.batchremoveCache(fieldDict,CommonUtil.longToString(ids))>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}

	/**
	 * 查询所有组织机构
	 * @return
	 */
	@ResponseBody
	@GetMapping("/serchSchoolAll")
	public List  serchSchoolAll(){
		//查询列表数据

		List<SchoolDO> schoolList = schoolService.serchSchoolAll();
		return schoolList;
	}


	//导出
	@RequestMapping("/searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params,HttpServletRequest request, HttpServletResponse response){
		//进度条清session
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "Student/";
		String strZipPath=configsrc+"StudentZip/";
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
		ZipOutputStream out = null;
		params.put("regionid", params.get("orgid"));
		List<SchoolDO> schoolList = schoolService.list(params);
		request.getSession().setAttribute("totalCount", schoolList.size());
		for (SchoolDO item : schoolList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));

		}

		if (schoolList != null && schoolList.size() > 0) {
			String[][] result = new String[schoolList.size() + 1][8];
			result[0] = new String[] { "序号","代码", "助学组织名称","助学主体类型", "法人名称", "移动电话","状态"};
			if (schoolList != null && schoolList.size() > 0) {
				for (int i = 0; i < schoolList.size(); i++) {
					SchoolDO schoolDO = schoolList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(schoolDO.getCode());
					result[i + 1][2] = String.valueOf(schoolDO.getName());
					result[i + 1][3] = String.valueOf(schoolDO.getType());
					result[i + 1][4] = String.valueOf(schoolDO.getLegalPerson());
					result[i + 1][5] = String.valueOf(schoolDO.getMphone());
					result[i + 1][6] = String.valueOf(schoolDO.getStatus());
					//进度条写入进度
					double dPercent=(double)(i)/schoolList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="教学组织管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="教学组织管理导出信息" +date+".zip";
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
			return "center/school/school/school";
		}
		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", schoolList.size());
		return null;
	}


	//弹出导入页面
	@GetMapping("/importData")
	String importData(String regionid,Model model){
		model.addAttribute("regionid",regionid);
		return "center/school/school/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("school:school:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session,String regionid
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/school/school/importData";
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
			return "center/school/school/importData";
		}

		//返回错误信息
		String message = schoolService.batchImport(fileName,file,regionid);
		request.setAttribute("msg",message);
		return "center/school/school/importData";
	}


}
