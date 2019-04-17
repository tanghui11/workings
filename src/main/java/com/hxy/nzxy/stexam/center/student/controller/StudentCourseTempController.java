package com.hxy.nzxy.stexam.center.student.controller;

import java.io.*;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseDao;
import com.hxy.nzxy.stexam.center.student.dao.StudentCourseTempDao;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseTempService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 考生报考临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCourseTemp")
public class StudentCourseTempController extends SystemBaseController{
	@Autowired
	private StudentCourseTempService studentCourseTempService;
    @Autowired
    private CommonService commonService;
    @Autowired
	private StudentCourseTempDao studentCourseTempDao;
    @Autowired
	private StudentCourseDao studentCourseDao;
	@GetMapping()
	@RequiresPermissions("student:studentCourseTemp:studentCourseTemp")
	String StudentCourseTemp(){
	    return "center/student/studentCourseTemp/studentCourseTemp";
	}

	@GetMapping("/leadInPage")
	@RequiresPermissions("student:studentCourseTemp:studentCourseTemp")
	String leadInPage(){
		return "center/student/studentCourseTemp/SCImportData";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseTemp:studentCourseTemp")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseTempDO> studentCourseTempList = studentCourseTempService.list(query);
        for (StudentCourseTempDO item : studentCourseTempList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseTempService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseTempList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseTemp:add")
	String add(Model model){
	    return "center/student/studentCourseTemp/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseTemp:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseTempDO studentCourseTemp = studentCourseTempService.get(id);
		model.addAttribute("studentCourseTemp", studentCourseTemp);
	    return "center/student/studentCourseTemp/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseTemp:add")
	public R save( StudentCourseTempDO studentCourseTemp){
        studentCourseTemp.setOperator(ShiroUtils.getUserId().toString());
		if(studentCourseTempService.save(studentCourseTemp)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseTemp:edit")
	public R update( StudentCourseTempDO studentCourseTemp){
	    studentCourseTemp.setOperator(ShiroUtils.getUserId().toString());
		studentCourseTempService.update(studentCourseTemp);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseTemp:remove")
	public R remove( Long id){
		if(studentCourseTempService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseTemp:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseTempService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/SCsavBathData")
	@RequiresPermissions("student:studentCourseTemp:add")
	public String ZSsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/student/studentCourseTemp/SCImportData";
		}

		//获取文件名
		String fileName=file.getOriginalFilename();

//		//验证文件名是否合格
//		if(!ExcelImportUtils.validatedbf(fileName)){
//			session.setAttribute("msg","文件必须是dbf格式！");
//			return "redirect:toUserKnowledgeImport";
//		}

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size=file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			request.setAttribute("msg","文件不能为空！");
			return "center/student/studentCourseTemp/SCImportData";
		}

		//批量导入
		String message = studentCourseTempService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/student/studentCourseTemp/SCImportData";
	}

	/**
	 * 批量入库
	 */
	@Transactional
	@ResponseBody
	@PostMapping("/saveCourse")
	@RequiresPermissions("student:studentCourseTemp:add")
	public  R saveCourse(@RequestParam Map<String, Object> params){
		List<StudentCourseTempDO> list=studentCourseTempDao.list(params);
		Long examCourseid =null;
		for (StudentCourseTempDO item :list){
			examCourseid = studentCourseTempDao.getExamCourseid(item.getKssj(),item.getKcdm());
			if(examCourseid==null){
				return R.error("未找到对应的开考课程！！");
			}
			item.setExamCourseid(examCourseid);
		}
		for (int i =0; i<list.size();i++) {

			studentCourseDao.tempSave(list.get(i));
		}

		return R.ok();
		//return "center/student/studentCourseTemp/studentCourseTemp";

	}
}
