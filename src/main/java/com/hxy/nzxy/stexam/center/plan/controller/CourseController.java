package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseDO;
import com.hxy.nzxy.stexam.domain.CourseDOTest;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import java.util.*;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-07-30 10:05:51
 */
 
@Controller
@RequestMapping("/plan/course")
public class CourseController extends SystemBaseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("plan:course:course")
	String Course(Model model){
		model.addAttribute("pla_course_type", commonService.listFieldDict(getAppName(), "pla_course", "type"));

		return "center/plan/course/course";
	}
	@GetMapping("/clist")
	String Courses(Model model,@RequestParam Map<String, Object> params){
		String attribute=(String)params.get("attribute");
		model.addAttribute("attribute",attribute);
		return "center/plan/course/courses";
	}

	@GetMapping("/clistXJ")
	String Coursesxj(Model model,@RequestParam Map<String, Object> params){
		String attribute=(String)params.get("attribute");
		model.addAttribute("attribute",attribute);
		return "center/plan/course/coursesxj";
	}

	@GetMapping("/clistSJ")
	String Coursessj(Model model,@RequestParam Map<String, Object> params){
		String attribute=(String)params.get("attribute");
		model.addAttribute("attribute",attribute);
		return "center/plan/course/coursessj";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:course:course")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseDO> courseList = courseService.list(query);
		for (CourseDO item : courseList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_course", "classify", item.getClassify()));
			item.setAttribute(FieldDictUtil.get(getAppName(), "pla_course", "attribute", item.getAttribute()));
			item.setPracticeCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getPracticeCourseid())+" "+item.getPracticeCourseid());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = courseService.count(query);
		PageUtils pageUtils = new PageUtils(courseList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/listxj")
	@RequiresPermissions("plan:course:course")
	public PageUtils listxj(@RequestParam Map<String, Object> params){
		//查询列表数据
		String schoolid = ShiroUtils.getUser().getWorkerid();
		params.put("schoolid",schoolid);
		Query query = new Query(params);
		List<CourseDO> courseList = courseService.listxj(query);
		for (CourseDO item : courseList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_course", "classify", item.getClassify()));
			item.setAttribute(FieldDictUtil.get(getAppName(), "pla_course", "attribute", item.getAttribute()));
			item.setPracticeCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getPracticeCourseid())+" "+item.getPracticeCourseid());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = courseService.countxj(query);
		PageUtils pageUtils = new PageUtils(courseList, total);
		return pageUtils;
	}

	
	@GetMapping("/add")
	@RequiresPermissions("plan:course:add")
	String add( Model model){

		model.addAttribute("pla_course_type", commonService.listFieldDict(getAppName(), "pla_course", "type"));
		model.addAttribute("pla_course_classify", commonService.listFieldDict(getAppName(), "pla_course", "classify"));
		model.addAttribute("pla_course_attribute", commonService.listFieldDict(getAppName(), "pla_course", "attribute"));
	    return "center/plan/course/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:course:edit")
	String edit(@PathVariable("id") String id,Model model){
		CourseDO course = courseService.get(id);
		model.addAttribute("pla_course_type", commonService.listFieldDict(getAppName(), "pla_course", "type"));
		model.addAttribute("pla_course_classify", commonService.listFieldDict(getAppName(), "pla_course", "classify"));
		model.addAttribute("pla_course_attribute", commonService.listFieldDict(getAppName(), "pla_course", "attribute"));

		model.addAttribute("course", course);
	    return "center/plan/course/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:course:add")
	public R save( CourseDO course){
		course.setOperator(ShiroUtils.getUserId().toString());
		if(courseService.save(course)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_course_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(course.getId());
			fieldDict.setFieldMean(course.getName());
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
	@RequiresPermissions("plan:course:edit")
	public R update( CourseDO course){
		course.setOperator(ShiroUtils.getUserId().toString());
		if(courseService.update(course)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_course_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(course.getId());
			fieldDict.setFieldMean(course.getName());
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
	@RequiresPermissions("plan:course:remove")
	public R remove( String id){
		if(courseService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_course_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id);
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
	@RequiresPermissions("plan:course:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		if(courseService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("pla_course_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setSeq(0);
			if(fieldDictService.batchremoveCache(fieldDict,ids)>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}

	@GetMapping("/test")
	void test(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        //执行结束
        System.out.println("Export success!!");
		//此处使用的pojo类必须和模板文件中的类一致,否则无法正确生成.
        List<CourseDOTest> userList = Arrays.asList(new CourseDOTest("aaa", "111",4+""), new CourseDOTest("bbb", "222",4+""), new CourseDOTest("bbb", "222",4+""), new CourseDOTest("bbb", "222",4+""), new CourseDOTest("bbb", "222",4+""), new CourseDOTest("bbb", "222",4+""), new CourseDOTest("bbb", "222",4+""), new CourseDOTest("bbb", "222",4+""), new CourseDOTest("bbb", "222",4+""), new CourseDOTest("bbb", "222",4+""));
        //模拟数据源
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(userList);
        HashMap<String, Object> objectHashMap = new HashMap<>();
        //此处demo直接使用的磁盘绝对路径了,实际生产老实取文件路径
        File reporeFile = new File("F:/report3.jasper");
        JasperHelper.showPdf("导出测试", reporeFile.getPath(), request, response, objectHashMap, jrDataSource);
	}

    //弹出导入页面
    @GetMapping("/importData")
    String importData( ){
        return "center/plan/course/importData";
    }

    /**
     * 批量导入
     */
    @PostMapping("/savBathData")
    @RequiresPermissions("plan:course:add")
    public String savBathData(@RequestParam(value="filename") MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session
    ) throws IOException{
        //判断文件是否为空
        if(file==null){
            request.setAttribute("msg","文件不能为空！");
            return "center/plan/course/importData";
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
            return "center/plan/course/importData";
        }

        //批量导入
        String message = courseService.batchImport(fileName,file);
        request.setAttribute("msg",message);
        return "center/plan/course/importData";
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
		List<CourseDO> courseList = courseService.list(params);
		request.getSession().setAttribute("totalCount", courseList.size());
		for (CourseDO item : courseList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_course", "classify", item.getClassify()));
			item.setAttribute(FieldDictUtil.get(getAppName(), "pla_course", "attribute", item.getAttribute()));
			item.setPracticeCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getPracticeCourseid())+" "+item.getPracticeCourseid());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (courseList != null && courseList.size() > 0) {
			String[][] result = new String[courseList.size() + 1][11];
			result[0] = new String[] { "序号","课程编码", "课程名称", "拼音", "学分", "类别", "分类", "属性","操作员","操作日期"};
			if (courseList != null && courseList.size() > 0) {
				for (int i = 0; i < courseList.size(); i++) {
					CourseDO courseDO = courseList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(courseDO.getId());
					result[i + 1][2] = String.valueOf(courseDO.getName());
					result[i + 1][3] = String.valueOf(courseDO.getPinyin());
					result[i + 1][4] = String.valueOf(courseDO.getScore());
					result[i + 1][5] = String.valueOf(courseDO.getType());
					result[i + 1][6] = String.valueOf(courseDO.getClassify());
					result[i + 1][7] = String.valueOf(courseDO.getAttribute());
					result[i + 1][8] = String.valueOf(courseDO.getOperator());
					result[i + 1][9] = String.valueOf(courseDO.getUpdateDate());

					//进度条写入进度
					double dPercent=(double)(i)/courseList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="课程管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="课程管理导出信息" +date+".zip";
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
			return "center/plan/course/course";
		}

		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", courseList.size());
		return null;
	}

	@GetMapping("/listCourse")
	String listCouse(Model model,@RequestParam Map<String, Object> params){
		String specialityRecordid=(String)params.get("specialityRecordid");
		model.addAttribute("specialityRecordid",specialityRecordid);
		return "center/plan/course/listCourse";
	}

	//课程复选课程列表
	@ResponseBody
	@GetMapping("/listCourses")
	@RequiresPermissions("plan:course:course")
	public PageUtils listCourses(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CourseDO> courseList = courseService.listCourses(query);
		for (CourseDO item : courseList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_course", "classify", item.getClassify()));
			item.setAttribute(FieldDictUtil.get(getAppName(), "pla_course", "attribute", item.getAttribute()));
			item.setPracticeCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getPracticeCourseid())+" "+item.getPracticeCourseid());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = courseService.countCourses(query);
		PageUtils pageUtils = new PageUtils(courseList, total);
		return pageUtils;
	}
}