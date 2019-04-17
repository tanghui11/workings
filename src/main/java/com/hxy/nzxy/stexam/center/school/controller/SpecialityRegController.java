package com.hxy.nzxy.stexam.center.school.controller;

import java.io.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.domain.RegionDO;
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

import com.hxy.nzxy.stexam.domain.SpecialityRegDO;
import com.hxy.nzxy.stexam.center.school.service.SpecialityRegService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 中心端专业开设备案审核
 *
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/specialityReg")
public class SpecialityRegController extends SystemBaseController{
	@Autowired
	private SpecialityRegService specialityRegService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:specialityReg:specialityReg")
	String SpecialityReg(Model model){
		model.addAttribute("sch_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "audit_status"));
		model.addAttribute("sch_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "status"));
		return "center/school/specialityReg/specialityReg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:specialityReg:specialityReg")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SpecialityRegDO> specialityRegList = specialityRegService.list(query);
        for (SpecialityRegDO item : specialityRegList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "status", item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "audit_status", item.getAuditStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", String.valueOf(item.getSchoolid())));
			item.setSubjectCode(String.valueOf(item.getSubjectCode()));
			item.setSubjectName(String.valueOf(item.getSubjectName()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = specialityRegService.count(query);
		PageUtils pageUtils = new PageUtils(specialityRegList, total);
		return pageUtils;
	}

	
	/**
	 * 单个审核/取消审核
	 */
	@PostMapping( "/updateAudit")
	@ResponseBody
	@RequiresPermissions("school:specialityReg:audit")
	public R updateAudit( @RequestParam Map<String, Object> params){
		String auditStatus=(String)params.get("auditStatus");
		String messages="审核成功！";
		if("a".equals(auditStatus))
		{
			messages="取消审核成功！";
		}
		if(specialityRegService.updateAudit(params)>0){
		return R.ok(messages);
		}
		return R.error();
	}
	
	/**
	 * 批量审核/取消审核
	 */
	@PostMapping( "/batchUpdateAudit")
	@ResponseBody
	@RequiresPermissions("school:specialityReg:batchAudit")
	public R batchUpdateAudit(@RequestParam("ids[]") Long[] ids,@RequestParam("auditStatus") String auditStatus){

		String messages="批量审核成功！";
		if("a".equals(auditStatus))
		{
			messages="批量取消审核成功！";
		}
		specialityRegService.batchUpdateAudit(ids,auditStatus);
		return R.ok();
	}

	//导入页面
	@GetMapping("/ZYimportData")

	String ZYimportData( ){
		return "center/school/specialityReg/ZYimportData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/ZYsavBathData")
	@RequiresPermissions("school:specialityReg:add")
	public String ZYsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/school/specialityReg/ZYimportData";
		}

		//获取文件名
		String fileName=file.getOriginalFilename();

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size=file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			request.setAttribute("msg","文件不能为空！");
			return "center/school/specialityReg/ZYimportData";
		}

		//批量导入
		String message = specialityRegService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/school/specialityReg/ZYimportData";
	}

	//批量导出
	@RequestMapping("/searchOutExcelZY")
	public String searchOutKEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request, Model model){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "specialityReg/";
		String strZipPath=configsrc+"specialityRegZip/";
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
		List<SpecialityRegDO> courseFreeList = specialityRegService.list(params);
		request.getSession().setAttribute("totalCount", courseFreeList.size());
		for (SpecialityRegDO item : courseFreeList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "status", item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "audit_status", item.getAuditStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", String.valueOf(item.getSchoolid())));
			item.setSubjectCode(String.valueOf(item.getSubjectCode()));
			item.setSubjectName(String.valueOf(item.getSubjectName()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		if (courseFreeList != null && courseFreeList.size() > 0) {
			String[][] result = new String[courseFreeList.size() + 1][8];


			result[0] = new String[] { "序号","专业代码","专业名称","助学组织","审核状态"	,"使用状态","操作员","操作日期"};
			if (courseFreeList != null && courseFreeList.size() > 0) {
				for (int i = 0; i < courseFreeList.size(); i++) {
					SpecialityRegDO courseFree = courseFreeList.get(i);
					result[i + 1][0] = String.valueOf( courseFree.getId());
					result[i + 1][1] = String.valueOf(courseFree.getSubjectCode());
					result[i + 1][2] = String.valueOf(courseFree.getSubjectName());
					result[i + 1][3] = String.valueOf(courseFree.getSchoolid()+courseFree.getSchoolName());
					result[i + 1][4] = String.valueOf(courseFree.getAuditStatus());
					result[i + 1][5] = String.valueOf(courseFree.getStatus());
					result[i + 1][6] = String.valueOf(courseFree.getOperator());
					result[i + 1][7] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/courseFreeList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="专业开设申请导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="专业开设申请导出信息" +date+".zip";
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
			model.addAttribute("sch_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "audit_status"));
			model.addAttribute("sch_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "status"));
			return "center/school/specialityReg/specialityReg";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", courseFreeList.size());
		return null;
	}





}
