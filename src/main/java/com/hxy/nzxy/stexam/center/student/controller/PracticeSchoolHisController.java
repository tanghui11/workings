package com.hxy.nzxy.stexam.center.student.controller;

import java.util.List;
import java.util.Map;

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

import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;
import com.hxy.nzxy.stexam.center.student.service.PracticeSchoolHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 实践成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/practiceSchoolHis")
public class PracticeSchoolHisController extends SystemBaseController{
	@Autowired
	private PracticeSchoolHisService practiceSchoolHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:practiceSchoolHis:practiceSchoolHis")
	String PracticeSchoolHis(){
	    return "center/student/practiceSchoolHis/practiceSchoolHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:practiceSchoolHis:practiceSchoolHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PracticeSchoolHisDO> practiceSchoolHisList = practiceSchoolHisService.list(query);
        for (PracticeSchoolHisDO item : practiceSchoolHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = practiceSchoolHisService.count(query);
		PageUtils pageUtils = new PageUtils(practiceSchoolHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:practiceSchoolHis:add")
	String add(Model model){
	    return "center/student/practiceSchoolHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:practiceSchoolHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PracticeSchoolHisDO practiceSchoolHis = practiceSchoolHisService.get(id);
		model.addAttribute("practiceSchoolHis", practiceSchoolHis);
	    return "center/student/practiceSchoolHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:practiceSchoolHis:add")
	public R save( PracticeSchoolHisDO practiceSchoolHis){
        practiceSchoolHis.setOperator(ShiroUtils.getUserId().toString());
		if(practiceSchoolHisService.save(practiceSchoolHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:practiceSchoolHis:edit")
	public R update( PracticeSchoolHisDO practiceSchoolHis){
	    practiceSchoolHis.setOperator(ShiroUtils.getUserId().toString());
		practiceSchoolHisService.update(practiceSchoolHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolHis:remove")
	public R remove( Long id){
		if(practiceSchoolHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		practiceSchoolHisService.batchRemove(ids);
		return R.ok();
	}
	
}
