package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityCourseService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityRecordService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
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
 * 专业开设管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:50
 */
 
@Controller
@RequestMapping("/plan/specialityRecord")
public class SpecialityRecordController extends SystemBaseController{
	@Autowired
	private SpecialityRecordService specialityRecordService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SpecialityService specialityService;
	@Autowired
	private SpecialityCourseService specialityCourseService;
	@GetMapping()
	@RequiresPermissions("plan:specialityRecord:specialityRecord")
	String SpecialityRecord(){
	    return "center/plan/specialityRecord/specialityRecord";
	}
    @GetMapping("copy/{id}")
    @RequiresPermissions("plan:specialityRecord:specialityRecord")
    String SpecialityRecordCopy(@PathVariable("id") Long id,Model model){
	    model.addAttribute("id",id);
        return "center/plan/specialityRecord/specialityRecordCopy";
    }
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("plan:specialityRecord:specialityRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        //Object id = params.get("id");
        Query query = new Query(params);
		List<SpecialityRecordDO> specialityRecordList = specialityRecordService.list(query);


        for (SpecialityRecordDO item : specialityRecordList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_speciality_record", "status", item.getStatus()));
			item.setGradStatus(FieldDictUtil.get(getAppName(), "pla_speciality_record", "grad_status", item.getGradStatus()));
			item.setSubjectName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setNewSpecialityName(item.getNewSpecialityid()+"("+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getNewSpecialityid())+")("+item.getDirection()+")");
			item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", String.valueOf(item.getCollegeid())));
            item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", String.valueOf(item.getSchoolid())));
			item.setGradCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getGradCourseid())+" "+item.getGradCourseid());
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = specialityRecordService.count(query);
		PageUtils pageUtils = new PageUtils(specialityRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:specialityRecord:add")
	String add(Model model){
		model.addAttribute("pla_speciality_record_grad_status", commonService.listFieldDict(getAppName(), "pla_speciality_record", "grad_status"));
		model.addAttribute("pla_speciality_record_status", commonService.listFieldDict(getAppName(), "pla_speciality_record", "status"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));

	    return "center/plan/specialityRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:specialityRecord:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SpecialityRecordDO specialityRecord = specialityRecordService.get(id);
		/*SpecialityDO speciality = specialityService.get(specialityRecord.getSpecialityid());
		if(speciality!=null){
			specialityRecord.setSubjectName(speciality.getName());
		}
		SpecialityDO speciality1 = specialityService.get(specialityRecord.getNewSpecialityid());
		if(speciality1!=null){
			specialityRecord.setNewSpecialityName(speciality1.getName());
		}
		specialityRecord.setSubjectName(speciality.getName());*/
		if(specialityRecord.getNewSpecialityid()!=null){
            specialityRecord.setNewSpecialityName(specialityRecord.getNewSpecialityid()+"("+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", specialityRecord.getNewSpecialityid())+")("+specialityRecord.getNewDirection()+")");

        }
		specialityRecord.setSubjectName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", specialityRecord.getNewSpecialityid()));
		model.addAttribute("specialityRecord", specialityRecord);
		model.addAttribute("pla_speciality_record_grad_status", commonService.listFieldDict(getAppName(), "pla_speciality_record", "grad_status"));
		model.addAttribute("pla_speciality_record_status", commonService.listFieldDict(getAppName(), "pla_speciality_record", "status"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
	    return "center/plan/specialityRecord/edit";
	}

	/**
	 * 专业课程复制页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/showCopyCourse/{id}")
	@RequiresPermissions("plan:specialityRecord:showCopyCourse")
	String showCopyCourse(@PathVariable("id") Long id,Model model){
		SpecialityRecordDO specialityRecord = specialityRecordService.get(id);
		SpecialityDO speciality = specialityService.get(specialityRecord.getSpecialityid());
		specialityRecord.setSubjectName(speciality.getName());
		model.addAttribute("specialityRecord", specialityRecord);

		return "center/plan/specialityRecord/showCopyCourse";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:specialityRecord:add")
	public R save( SpecialityRecordDO specialityRecord){
        specialityRecord.setOperator(ShiroUtils.getUserId().toString());
		if(specialityRecordService.save(specialityRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 专业课程复制
	 */
	@ResponseBody
	@PostMapping("/saveCopy")
	@RequiresPermissions("plan:specialityRecord:showCopyCourse")
	public R saveCopy( @RequestParam Map<String, Object> map){
		String specialityRecordid=(String)map.get("specialityRecordid");

		List<SpecialityCourseDO>  list = specialityCourseService.serachSpecialityIdList(map);
		if(list!=null &&list.size()>0) {
			list.forEach(item -> {
				item.setOperator(ShiroUtils.getUserId().toString());
				item.setSpecialityRecordid(Long.valueOf(specialityRecordid));
			});
			if (specialityCourseService.batchSave(list) > 0) {
				return R.ok("复制专业课程成功！");
			}else
			{
				return R.error();
			}

		}else
		{
			return R.ok("没有专业课程复制！");
		}
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:specialityRecord:edit")
	public R update( SpecialityRecordDO specialityRecord){
	    specialityRecord.setOperator(ShiroUtils.getUserId().toString());
		specialityRecordService.update(specialityRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:specialityRecord:remove")
	public R remove( Long id){
		if(specialityRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:specialityRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		specialityRecordService.batchRemove(ids);
		return R.ok();
	}

	//弹出导入页面
	@GetMapping("/importData")
	String importData( ){
		return "center/plan/specialityRecord/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("plan:specialityRecord:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/specialityRecord/importData";
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
			return "center/plan/specialityRecord/importData";
		}

		//批量导入
		String message = specialityRecordService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/plan/specialityRecord/importData";
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
		List<SpecialityRecordDO> specialityRecordList = specialityRecordService.list(params);
		request.getSession().setAttribute("totalCount", specialityRecordList.size());
		for (SpecialityRecordDO item : specialityRecordList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_speciality_record", "status", item.getStatus()));
			item.setGradStatus(FieldDictUtil.get(getAppName(), "pla_speciality_record", "grad_status", item.getGradStatus()));
			item.setSubjectName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", String.valueOf(item.getCollegeid())));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", String.valueOf(item.getSchoolid())));
			item.setGradCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getGradCourseid())+" "+item.getGradCourseid());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (specialityRecordList != null && specialityRecordList.size() > 0) {
			String[][] result = new String[specialityRecordList.size() + 1][10];
			result[0] = new String[] { "序号","专业代码", "专业名称","毕业论文", "主考院校","类别","专业方向","状态","办证状态"};
			if (specialityRecordList != null && specialityRecordList.size() > 0) {
				for (int i = 0; i < specialityRecordList.size(); i++) {
					SpecialityRecordDO specialityRecordDO = specialityRecordList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(specialityRecordDO.getSpecialityid());
					result[i + 1][2] = String.valueOf(specialityRecordDO.getSubjectName());
					result[i + 1][3] = String.valueOf(specialityRecordDO.getGradCourseid());
					result[i + 1][4] = String.valueOf(specialityRecordDO.getSchoolName());
//					result[i + 1][5] = String.valueOf(specialityRecordDO.getCollegeName());
					result[i + 1][5] = String.valueOf(specialityRecordDO.getType());
					result[i + 1][6] = String.valueOf(specialityRecordDO.getDirection());
					result[i + 1][7] = String.valueOf(specialityRecordDO.getStatus());
					result[i + 1][8] = String.valueOf(specialityRecordDO.getGradStatus());

					//进度条写入进度
					double dPercent=(double)(i)/specialityRecordList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%

				}
			}
			String tempFileName="开考专业设置信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="开考专业设置信息" +date+".zip";
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
			return "center/plan/specialityRecord/specialityRecord";
		}
		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", specialityRecordList.size());

		return null;
	}
	/**
	 * 显示弹出页面 专业开设编号
	 * @param
	 * @return
	 */
	@GetMapping("/showSubject")

	String showSubject( ){

		return "center/plan/specialityRecord/showSubject";
	}
	/**
	 * 显示弹出页面 专业开设编号
	 * @param
	 * @return
	 */
	@GetMapping("/showSubject1")

	String showSubject1( ){

		return "center/plan/specialityRecord/showSubject1";
	}

	/**
	 * 显示弹出页面 新专业开设编号
	 * @param
	 * @return
	 */
	@GetMapping("/showSubject2")

	String showSubject2( ){

		return "center/plan/specialityRecord/showSubject2";
	}


}
