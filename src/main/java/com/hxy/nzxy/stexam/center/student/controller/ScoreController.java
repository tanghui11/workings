package com.hxy.nzxy.stexam.center.student.controller;

import java.lang.invoke.MethodType;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseDao;
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

import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生成绩表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/score")
public class ScoreController extends SystemBaseController{
	@Autowired
	private ScoreService scoreService;
    @Autowired
    private CommonService commonService;

	@Autowired
	private StudentCourseDao studentCourseDao;
	@GetMapping()
	@RequiresPermissions("student:score:score")
	String Score(){
	    return "center/student/score/score";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:score:score")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreDO> scoreList = scoreService.list(query);//里边加 专业名称联查
        for (ScoreDO item : scoreList) {
            item.setType(FieldDictUtil.get(getAppName(), "stu_score", "type", item.getType()));
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
            item.setUseStatus(FieldDictUtil.get(getAppName(), "stu_score", "use_status", item.getUseStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreService.count(query);
		PageUtils pageUtils = new PageUtils(scoreList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/listTwo")
	@RequiresPermissions("student:score:score")
	public PageUtils listTwo(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ScoreDO> scoreList = scoreService.listTwo(query);//里边加 专业名称联查
		for (ScoreDO item : scoreList) {
			item.setType(FieldDictUtil.get(getAppName(), "stu_score", "type", item.getType()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setUseStatus(FieldDictUtil.get(getAppName(), "stu_score", "use_status", item.getUseStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = scoreService.count(query);
		PageUtils pageUtils = new PageUtils(scoreList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:score:add")
	String add(Model model){
	    return "center/student/score/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:score:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreDO score = scoreService.get(id);
		model.addAttribute("score", score);
	    return "center/student/score/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:score:add")
	public R save( ScoreDO score){
        score.setOperator(ShiroUtils.getUserId().toString());
		if(scoreService.save(score)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:score:edit")
	public R update( ScoreDO score){
	    score.setOperator(ShiroUtils.getUserId().toString());
		scoreService.update(score);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:score:remove")
	public R remove( Long id){
		if(scoreService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:score:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreService.batchRemove(ids);
		return R.ok();
	}


	@RequestMapping("merge")//合并成绩
	@RequiresPermissions("student:studentCourse:add")
	public String merge(String oldStudentid, String newStudentid, String courseid){
		studentCourseDao.meger(oldStudentid, newStudentid, courseid);
		return "center/student/courseMeger/courseMeger";
	}
	@RequestMapping("reMeger")//取消合并成绩
	@RequiresPermissions("student:studentCourse:add")
	public String reMeger(String oldStudentid, String newStudentid, String courseid){
		studentCourseDao.reMeger(oldStudentid, newStudentid, courseid);
		return "center/student/courseMeger/courseMeger";
	}

	@GetMapping("courseMegerPage")
	@RequiresPermissions("student:score:score")
	public  String courseMegerPage(){
		return "center/student/courseMeger/courseMeger";
	}


	/**
	 * 平时成绩合并------考生信息查询
	 */
	@ResponseBody
	@GetMapping("/listMerger")
	@RequiresPermissions("student:score:score")
	public PageUtils listMerger(@RequestParam Map<String, Object> params){//params中需包含 考试任务id  助学组织id
		//查询列表数据
		Query query = new Query(params);
		List<ScoreDO> scoreList = scoreService.listMerger(query);
		for (ScoreDO item : scoreList) {
			item.setType(FieldDictUtil.get(getAppName(), "stu_score", "type", item.getType()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setUseStatus(FieldDictUtil.get(getAppName(), "stu_score", "use_status", item.getUseStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = scoreService.countMerger(query);
		PageUtils pageUtils = new PageUtils(scoreList, total);
		return pageUtils;
	}

	/**
	 * 平时成绩合并
	 */

	@ResponseBody
	@PostMapping(value = "/mergerResults")
	@RequiresPermissions("student:score:score")
	public R mergerResults(@RequestParam Map<String, Object> params){
		params.put("limit",20);
		params.put("offset",0);
		Query query = new Query(params);
		String schoolid = params.get("schoolid").toString();
		ScoreDO retio = scoreService.getRetio(schoolid);//查寻 合并比例
		String msg = "";
		float num = 0;
		float num2 = 0;
		int ii =0;
		float psScore = 0;
		List<ScoreDO> scoreList = scoreService.listMerger(query);
		if (scoreList.size() != 0){
			for (int i = 0; i< scoreList.size(); i++){
				psScore = scoreService.maxGrade(scoreList.get(i).getStudentid(), scoreList.get(i).getCourseid(), scoreList.get(i).getSpecialityRecordid().toString());
				if(retio != null  &&scoreList.get(i).getSchoolGrade() !=null && !"".equals(scoreList.get(i).getSchoolGrade()) && scoreList.get(i).getExamGrade() !=null && !"".equals(scoreList.get(i).getExamGrade()) && psScore!= 0){
					num = Math.round(scoreList.get(i).getExamGrade()*(retio.getRatio()) + psScore);
					num2 = scoreList.get(i).getExamGrade();
					if (num <= num2){
						ii = scoreService.setMerger(scoreList.get(i).getStudentid(), scoreList.get(i).getCourseid(), psScore, num2);
						if(ii != 1){
							msg += "准考证号："+scoreList.get(i).getStudentid()+"合并成绩失败；";
						}else{
							scoreService.updateSchoolScore(retio.getRatio(),scoreList.get(i).getId(),scoreList.get(i).getCourseid());
						}
					}else{
						ii = scoreService.setMerger(scoreList.get(i).getStudentid(), scoreList.get(i).getCourseid(), psScore, num2);
						if(ii != 1){
							msg += "准考证号："+scoreList.get(i).getStudentid()+"合并成绩失败；";
						}else{
							scoreService.updateSchoolScore(retio.getRatio(),scoreList.get(i).getId(),scoreList.get(i).getCourseid());
						}
					}
				}else{
					msg += "准考证号："+scoreList.get(i).getStudentid()+"的统考成绩或平时成绩不存在；";
				}
			}

		}else{
			msg = "没有找到合并信息。";
			return R.error(0,msg);
		}
		return R.ok(msg);
	}

	/**
	 * 平时成绩取消合并
	 */

	@ResponseBody
	@PostMapping(value = "/mergerResultsQX")
	@RequiresPermissions("student:score:score")
	public R mergerResultsQX(@RequestParam Map<String, Object> params){
		params.put("limit",20);
		params.put("offset",0);
		String schoolid = params.get("schoolid").toString();
		String msg = "";
		float num = 0;
		float num2 = 0;
		int ii =0;
		Query query = new Query(params);
		List<ScoreDO> scoreList = scoreService.listMerger(query);
		if (scoreList.size() != 0){
			for (int i = 0; i< scoreList.size(); i++){
				scoreService.qxMerger(scoreList.get(i).getStudentid(), scoreList.get(i).getCourseid());
				scoreService.noUpdateSchoolScore(scoreList.get(i).getId(),scoreList.get(i).getCourseid());
			}

		}else{
			msg = "没有找到需要取消合并的信息。";
				return R.error(0,msg);
		}
		return R.ok();
	}

}
