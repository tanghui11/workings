package com.hxy.nzxy.stexam.center.exam.controller;

import com.hxy.nzxy.stexam.center.exam.service.TaskService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.TaskDO;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考试任务
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 16:59:07
 */
 
@Controller
@RequestMapping("/exam/task")
public class TaskController extends SystemBaseController{
	@Autowired
	private TaskService taskService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:task:task")
	String Task(){
	    return "center/exam/task/task";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:task:task")
	public PageUtils list(@RequestParam Map<String, Object> params){

		//查询列表数据
        Query query = new Query(params);
		List<TaskDO> taskList = taskService.list(query);
        for (TaskDO item : taskList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setExamMonth( FieldDictUtil.get(getAppName(), "exam_task", "exam_month",item.getExamMonth()));
			item.setConfirmStatus(FieldDictUtil.get(getAppName(), "exam_task", "confirm_status",item.getConfirmStatus()));
			item.setStatus(FieldDictUtil.get(getAppName(), "exam_task", "status",item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "exam_task", "audit_status",item.getAuditStatus()));
			item.setType(FieldDictUtil.get(getAppName(), "exam_task", "type",item.getType()));
        }
		int total = taskService.count(query);
		PageUtils pageUtils = new PageUtils(taskList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:task:add")
	String add(Model model)
	{
		model.addAttribute("exam_task_exam_month", commonService.listFieldDict(getAppName(), "exam_task", "exam_month"));
		model.addAttribute("exam_task_type", commonService.listFieldDict(getAppName(), "exam_task", "type"));

	    return "center/exam/task/add";
	}

	/**
	 * 考务设置报考时间页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:task:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TaskDO task = taskService.get(id);
		model.addAttribute("task", task);
		model.addAttribute("exam_task_type", commonService.listFieldDict(getAppName(), "exam_task", "type"));
		model.addAttribute("exam_task_exam_month", commonService.listFieldDict(getAppName(), "exam_task", "exam_month"));
	    return "center/exam/task/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:task:add")
	public R save( TaskDO task){
        task.setOperator(ShiroUtils.getUserId().toString());

		//学制开关
		Configuration config = getConfig("config.properties");
		String auditStatus =config.getString("auditStatus");
        task.setAuditStatus(auditStatus);
        task.setStatus("a");
        task.setConfirmStatus("a");
		if(taskService.save(task)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 设置报考时间
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:task:edit")
	public R update( TaskDO task){
		taskService.update(task);
		return R.ok("设置报考时间成功");
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:task:remove")
	public R remove( Long id){
	Long[]ids =new Long[1];
	ids[0]=id;
		//验证是否存在 报考记录
		int total = taskService.bkcount(ids);
		if (total>0){
			return R.ok("该条考试任务已存在报考记录不可删除！");
		}
		if(taskService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:task:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		//验证是否存在 报考记录
		int total = taskService.bkcount(ids);
		if (total>0){
			return R.ok("选中的考试任务已存在报考记录不可删除！");
		}
		taskService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 查询所有考试任务
	 * @return
	 */
	@ResponseBody
	@GetMapping("/serchTaskAll")
	public List  serchTaskAll(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<TaskDO> taskList = taskService.serchTaskAll(params);
		for (TaskDO item : taskList) {
			item.setExamMonth( FieldDictUtil.get(getAppName(), "exam_task", "exam_month",item.getExamMonth()));
			item.setConfirmStatus(FieldDictUtil.get(getAppName(), "exam_task", "confirm_status",item.getConfirmStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "exam_task", "audit_status",item.getAuditStatus()));

		}
		return taskList;
	}

	/**
	 *考务设置报考时间列表页面
	 * @param model
	 * @return
	 */
	@GetMapping("/showBkTimeSetList")
	@RequiresPermissions("exam:task:showBkTimeSetList")
	String showTaskList( Model model){
		model.addAttribute("exam_task_exam_month", commonService.listFieldDict(getAppName(), "exam_task", "exam_month"));
		return "center/exam/task/showBkTimeSetList";
	}

	/**
	 * 查询要设置报考时间的考试任务
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/seachBkTimeSetlist")
	@RequiresPermissions("exam:task:showBkTimeSetList")
	public PageUtils seachBkTimeSetlist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<TaskDO> taskList = taskService.seachBkTimeSetlist(query);
		for (TaskDO item : taskList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setExamMonth( FieldDictUtil.get(getAppName(), "exam_task", "exam_month",item.getExamMonth()));
			item.setConfirmStatus(FieldDictUtil.get(getAppName(), "exam_task", "confirm_status",item.getConfirmStatus()));
			item.setStatus(FieldDictUtil.get(getAppName(), "exam_task", "status",item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "exam_task", "audit_status",item.getAuditStatus()));
		}
		int total = taskService.count(query);
		PageUtils pageUtils = new PageUtils(taskList, total);
		return pageUtils;
	}

	/**
	 * 确认状态：status :b已确认，a取消确认
	 */
	@PostMapping( "/confirmStatus")
	@ResponseBody
	@RequiresPermissions("exam:task:batchRemove")
	public R confirmStatus(@RequestParam("examTaskid") String examTaskid,@RequestParam("confirmStatus") String confirmStatus){
		if(examTaskid!=null && !"".equals(examTaskid))
		{
			TaskDO task = taskService.get(Long.valueOf(examTaskid));
			task.setConfirmStatus(confirmStatus);
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("tableName","exa_exam_item");
			map.put("filedName1","exam_taskid");
			map.put("filedName1",examTaskid);
			Map<String,Object> map2 =new HashMap<String,Object>();
			map2.put("tableName","exa_exam_time");
			map2.put("filedName1","exam_taskid");
			map2.put("filedName1",examTaskid);
			Map<String,Object> map3 =new HashMap<String,Object>();
			map3.put("tableName","exa_exam_course");
			map3.put("filedName1","exam_taskid");
			map3.put("filedName1",examTaskid);
			int itemNum=commonService.searchIfExist(map);
			int timeNum=commonService.searchIfExist(map2);
			int courseNum=commonService.searchIfExist(map3);
			if(itemNum==0)
		{
			return R.ok("对不起，您还没有设置考试项目！");
		}
			if(timeNum==0)
			{
				return R.ok("对不起，您还没有设置考试时间！");
			}
			if(courseNum==0)
			{
				return R.ok("对不起，您还没有设置开考课程！");
			}
			if(taskService.updateStatus(task)>0)
			{
				return R.ok();
			}
			else
			{
				return R.error();
			}
		}

		return R.error();
	}


	/**
	 * 转结状态：status :b已转结，a取消转结
	 */
	@PostMapping( "/status")
	@ResponseBody
	@RequiresPermissions("exam:task:batchRemove")
	public R status(@RequestParam("examTaskid") String examTaskid,@RequestParam("status") String status){
		if(examTaskid!=null && !"".equals(examTaskid))
		{
			TaskDO task = taskService.get(Long.valueOf(examTaskid));
			task.setStatus(status);
			if(taskService.updateStatus(task)>0)
			{
				return R.ok();
			}
			else
			{
				return R.error();
			}
		}

		return R.error();
	}

	//导入页面
	@GetMapping("/TimportData")

	String TimportData( ){

		return "center/exam/task/TimportData";
	}


	//任务管理导入页面
	@GetMapping("/importData")

	String importData( ){

		return "center/exam/task/importData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("exam:task:add")
	public String TsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/exam/task/importData";
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
			return "center/exam/task/importData";
		}

		//批量导入
		String message = taskService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/exam/task/importData";
	}


	/**
	 * 考试任务管理导出
	 * @param params
	 * @param response
	 * 严鹏鹏用
	 * @return
	 */
	@RequestMapping("/searchOutExcel")
	public String searchOutTExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "certificateReplace/";
		String strZipPath=configsrc+"certificateReplaceZip/";
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
		List<TaskDO> taskList = taskService.list(params);
		request.getSession().setAttribute("totalCount", taskList.size());
		for (TaskDO item : taskList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setExamMonth( FieldDictUtil.get(getAppName(), "exam_task", "exam_month",item.getExamMonth()));
			item.setConfirmStatus(FieldDictUtil.get(getAppName(), "exam_task", "confirm_status",item.getConfirmStatus()));
			item.setStatus(FieldDictUtil.get(getAppName(), "exam_task", "status",item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "exam_task", "audit_status",item.getAuditStatus()));
		}
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(sf2.format(date));

		if (taskList != null && taskList.size() > 0) {
			String[][] result = new String[taskList.size() + 1][8];
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result[0] = new String[] { "年份","月份","备注","状态","确认状态","操作员","操作日期"};
			if (taskList != null && taskList.size() > 0) {
				for (int i = 0; i < taskList.size(); i++) {
					TaskDO courseFree = taskList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getExamYear());
					result[i + 1][1] = String.valueOf(courseFree.getExamMonth());

					result[i + 1][2] = String.valueOf(courseFree.getRemark());
					result[i + 1][3] = String.valueOf(courseFree.getStatus());
					result[i + 1][4] = String.valueOf(courseFree.getConfirmStatus());
					result[i + 1][5] = String.valueOf(courseFree.getOperator());
					result[i + 1][6] =  String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/taskList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="考试任务管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="考试任务管理导出信息" +date+".zip";
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
			return "center/exam/task/searchOutExcel";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", taskList.size());
		return null;
	}


	/**
	 * 报考时间设置导出
	 * @param params
	 * @param response
	 * @return
	 */
	@RequestMapping("/BKSJsearchOutTExcel")
	public String BKSJsearchOutTExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "certificateReplace/";
		String strZipPath=configsrc+"certificateReplaceZip/";
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
		List<TaskDO> taskList = taskService.seachBkTimeSetlist(params);
		request.getSession().setAttribute("totalCount", taskList.size());
		for (TaskDO item : taskList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setExamMonth( FieldDictUtil.get(getAppName(), "exam_task", "exam_month",item.getExamMonth()));
			item.setConfirmStatus(FieldDictUtil.get(getAppName(), "exam_task", "confirm_status",item.getConfirmStatus()));
			item.setStatus(FieldDictUtil.get(getAppName(), "exam_task", "status",item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "exam_task", "audit_status",item.getAuditStatus()));
		}
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(sf2.format(date));

		if (taskList != null && taskList.size() > 0) {
			String[][] result = new String[taskList.size() + 1][13];
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result[0] = new String[] { "年份","月份","助学生报考开始日期","助学生报考结束日期","助学生补报开始日期","助学生补报结束日期","社会生报考开始日期","社会生报考结束日期","社会生补报开始日期","社会生补报结束日期","状态","操作员","操作日期"};
			if (taskList != null && taskList.size() > 0) {
				for (int i = 0; i < taskList.size(); i++) {
					TaskDO courseFree = taskList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getExamYear());
					result[i + 1][1] = String.valueOf(courseFree.getExamMonth());
					result[i + 1][2] = String.valueOf(sf2.format(courseFree.getBeginDate()));
					result[i + 1][3] = String.valueOf(sf2.format(courseFree.getEndDate()));
					result[i + 1][4] = String.valueOf(sf2.format(courseFree.getBeginDateAppend()));
					result[i + 1][5] = String.valueOf(sf2.format(courseFree.getEndDateAppend()));
					result[i + 1][6] = String.valueOf(sf2.format(courseFree.getBeginDate1()));
					result[i + 1][7] = String.valueOf(sf2.format(courseFree.getEndDate1()));
					result[i + 1][8] = String.valueOf(sf2.format(courseFree.getBeginDateAppend1()));
					result[i + 1][9] = String.valueOf(sf2.format(courseFree.getEndDateAppend1()));
					result[i + 1][10] = String.valueOf(courseFree.getStatus());
					result[i + 1][11] = String.valueOf(courseFree.getOperator());
					result[i + 1][12] =  String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/taskList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="报考日期设置导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="报考日期设置导出信息" +date+".zip";
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
			return "center/exam/task/showBkTimeSetList";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", taskList.size());
		return null;
	}
}
