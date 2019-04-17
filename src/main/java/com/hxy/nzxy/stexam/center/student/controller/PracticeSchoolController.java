package com.hxy.nzxy.stexam.center.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;
import com.hxy.nzxy.stexam.center.student.service.PracticeSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 实践成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/practiceSchool")
public class PracticeSchoolController extends SystemBaseController{
	@Autowired
	private PracticeSchoolService practiceSchoolService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:practiceSchool:practiceSchool")
	String PracticeSchool(Model model){
		model.addAttribute("stu_practice_school_status", commonService.listFieldDict(getAppName(), "stu_practice_school", "status"));
	    return "center/student/practiceSchool/practiceSchool";
	}

	@GetMapping("/practiceCheck")
	@RequiresPermissions("student:practiceSchool:practiceSchool")
	String practiceCheck(Model model){
		model.addAttribute("stu_practice_school_status", commonService.listFieldDict(getAppName(), "stu_practice_school", "status"));
	    return "center/student/practiceSchool/practiceCheck";
	}

	@GetMapping("importData")
	@RequiresPermissions("student:practiceSchool:practiceSchool")
	String importData(){
		return "center/student/practiceSchool/importData";
	}

	@GetMapping("importDataSH")
	@RequiresPermissions("student:practiceSchool:societySchool")
	String importDataSH(){
		return "center/student/societySchool/importData";
	}

	@GetMapping("societySchool")
	@RequiresPermissions("student:practiceSchool:societySchool")
	String societySchool(Model model){
		model.addAttribute("stu_practice_school_status", commonService.listFieldDict(getAppName(), "stu_practice_school", "status"));
		return "center/student/societySchool/societySchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:practiceSchool:practiceSchool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PracticeSchoolDO> practiceSchoolList = practiceSchoolService.list(query);
        for (PracticeSchoolDO item : practiceSchoolList) {
			item.setConfirmStatus(FieldDictUtil.get(getAppName(), "stu_practice_school", "confirm_status", item.getConfirmStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_practice_school", "audit_status", item.getAuditStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = practiceSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(practiceSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:practiceSchool:add")
	String add(Model model){
	    return "center/student/practiceSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:practiceSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PracticeSchoolDO practiceSchool = practiceSchoolService.get(id);
		practiceSchool.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_practice_school", "audit_status", practiceSchool.getAuditStatus()));
		practiceSchool.setStatus(FieldDictUtil.get(getAppName(), "stu_practice_school", "status", practiceSchool.getStatus()));
		practiceSchool.setConfirmStatus(FieldDictUtil.get(getAppName(), "stu_practice_school", "confirm_status", practiceSchool.getConfirmStatus()));
		model.addAttribute("practiceSchool", practiceSchool);
	    return "center/student/societySchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:practiceSchool:add")
	public R save( PracticeSchoolDO practiceSchool){
        practiceSchool.setOperator(ShiroUtils.getUserId().toString());
		if(practiceSchoolService.save(practiceSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:practiceSchool:edit")
	public R update( PracticeSchoolDO practiceSchool){
	    practiceSchool.setOperator(ShiroUtils.getUserId().toString());
		practiceSchoolService.update(practiceSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchool:remove")
	public R remove( Long id){
		if(practiceSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		practiceSchoolService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 批量导入实践成绩临时表
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("student:practiceSchool:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/student/practiceSchool/PSimportData";
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
			return "center/student/practiceSchool/PSimportData";
		}

		//批量导入
		String message = practiceSchoolService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/student/practiceSchool/importData";
	}



	/**
	 * 实践成绩入库  社会型
	 */
	@ResponseBody
	@PostMapping("/SHScoreInDB")
	@RequiresPermissions("student:practiceSchool:add")
	public R SHScoreInDB(@RequestParam Map<String, Object> params){
		int k =0;
		String msg = "";
		int mm = 0;
		String operator = ShiroUtils.getUserId().toString();
		List<PracticeSchoolDO> practiceSchoolList = practiceSchoolService.list(params);
		if("a".equals(params.get("yes"))){
			for(int i =0; i<practiceSchoolList.size(); i++){
				k = practiceSchoolService.isBK(practiceSchoolList.get(i).getStudentid(),practiceSchoolList.get(i).getCourseid());
				if(k!=0){
					//if(practiceSchoolService.whetherinfo(practiceSchoolList.get(i).getStudentid()) == 1){
					if(practiceSchoolList.get(i).getStudentid() != ""){
						msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生入库失败或已存在；";
					}else if(!"b".equals(practiceSchoolList.get(i).getAuditStatus())){
						msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的暂未经过审核；";
					}else{
						mm = practiceSchoolService.RK(practiceSchoolList.get(i));
						if(mm != 1){
							msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生入库失败；";
						}else{
							practiceSchoolList.get(i).setOperator(operator);
							practiceSchoolService.enabled(practiceSchoolList.get(i));
						}
					}

					continue;
				}else{
					msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生未报名；";
				}
			}

		}
		if("b".equals(params.get("yes"))){
			for(int i =0; i<practiceSchoolList.size(); i++){
				//if(practiceSchoolService.whetherinfo(practiceSchoolList.get(i).getStudentid()) == 1){
				if(practiceSchoolList.get(i).getStudentid() != ""){
					msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生入库失败或已存在；";
				}else if(!"b".equals(practiceSchoolList.get(i).getAuditStatus())){
					msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的暂未经过审核；";
				}else{
					mm = practiceSchoolService.RK(practiceSchoolList.get(i));
					if(mm != 1){
						msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生入库失败；";
					}else{
						practiceSchoolList.get(i).setOperator(operator);
						practiceSchoolService.enabled(practiceSchoolList.get(i));
					}
					if ("".equals(msg)){
						msg += "入库成功";
					}
				}

			}
		}

		return R.ok(msg);

	}

	/**
	 * 实践成绩入库  应用型
	 */
	@ResponseBody
	@PostMapping("/YYScoreInDB")
	@RequiresPermissions("student:practiceSchool:add")
	public R YYScoreInDB(@RequestParam Map<String, Object> params){
		int k =0;
		String msg = "";
		int mm = 0;
		String operator = ShiroUtils.getUserId().toString();
		List<PracticeSchoolDO> practiceSchoolList = practiceSchoolService.list(params);
		if("a".equals(params.get("yes"))){
			for(int i =0; i<practiceSchoolList.size(); i++){
						k = practiceSchoolService.isBK(practiceSchoolList.get(i).getStudentid(),practiceSchoolList.get(i).getCourseid());
						if(k!=0){
							//if(practiceSchoolService.whetherinfo(practiceSchoolList.get(i).getStudentid()) == 1){
							if(practiceSchoolList.get(i).getStudentid() != ""){
								msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生入库失败或已存在；";
							}else if(!"b".equals(practiceSchoolList.get(i).getAuditStatus())){
								msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的暂未经过审核；";
							}else{
								mm = practiceSchoolService.RK(practiceSchoolList.get(i));

								if(mm != 1){
									msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生入库失败；";
								}else{
									practiceSchoolList.get(i).setOperator(operator);
									practiceSchoolService.enabled(practiceSchoolList.get(i));
								}
							}

					continue;
				}else{
					msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生未报名；";
				}
			}

		}
		if("b".equals(params.get("yes"))){
			for(int i =0; i<practiceSchoolList.size(); i++){
				//if(practiceSchoolService.whetherinfo(practiceSchoolList.get(i).getStudentid()) == 1){
				if(practiceSchoolList.get(i).getStudentid() != ""){
					msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生入库失败或已存在；";
				}else{
					mm = practiceSchoolService.RK(practiceSchoolList.get(i));
					if(mm != 1){
						msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的考生入库失败；";
					}else if(!"b".equals(practiceSchoolList.get(i).getAuditStatus())){
						msg += "准考证号为"+practiceSchoolList.get(i).getStudentid()+"的暂未经过审核；";
					}else{
						practiceSchoolList.get(i).setOperator(operator);
						practiceSchoolService.enabled(practiceSchoolList.get(i));
					}
					if ("".equals(msg)){
						msg += "入库成功";
					}
				}

			}
		}

		return R.ok(msg);

	}

	/**
	 * 实践成绩入库  取消入库
	 */
	@ResponseBody
	@Transactional
	@PostMapping("/QXScoreInDB")
	@RequiresPermissions("student:practiceSchool:add")
	public R QXScoreInDB(@RequestParam Map<String, Object> params){
		int k =0;
		String msg = "";
		int mm = 0;
		List<PracticeSchoolDO> practiceSchoolList = practiceSchoolService.list(params);
		int i = practiceSchoolService.qxrkZS(practiceSchoolList);
		k = practiceSchoolService.qxrk(practiceSchoolList);
		if(i>0 && k >0){
			msg += "取消成功！";
		}else{
			msg += "取消失败";
		}
		return R.ok(msg);
	}

	@Transactional
	@PostMapping("/hangworkPerformance")
	@ResponseBody
	@RequiresPermissions("student:practiceSchool:add")
	public R hangworkPerformance(@RequestParam Map<String, Object> params){
		String studentid = params.get("studentid").toString();
 		String type = params.get("type").toString();
		float grade = Float.parseFloat(params.get("grade").toString());
		int gradeInDB = 0 ;
		PracticeSchoolDO information = practiceSchoolService.cannotRK(studentid, type);
		String msg = "";
		if (information != null){
			if("b".equals(information.getStatus()) && "b".equals(information.getAuditStatus())){
				gradeInDB = practiceSchoolService.readyToRK(studentid, type, grade);
				if (gradeInDB == 1){
					msg = "修改已入库成绩成功";
				}else{
					msg = "入库失败";
					return R.error(0,msg);
				}
			}else{
				msg = "该报考信息还未审核，不能录入";
				return R.error(0,msg);
			}
		}else{
			msg = "未找到该考生的考试信息 ";
			return R.error(0,msg);
		}
//		request.setAttribute("msg",msg);
		return R.ok(msg);
	}

	/**
	 * 删除成绩
	 */
	@Transactional
	@PostMapping("/deleteGrade")
	@ResponseBody
	@RequiresPermissions("student:practiceSchool:add")
	public R deleteGrade(@RequestParam Map<String, Object> params){
		String studentid = params.get("studentid").toString();
		int gradeInDB = 0 ;
		String msg = "";
		gradeInDB = practiceSchoolService.deleteGrade(studentid);
		if (gradeInDB == 1){
			msg = "删除成绩成功";
		}else{
			msg = "删除失败或未找到该考生的考试信息";
			return R.error(0,msg);
		}
		return R.ok(msg);
	}
}
