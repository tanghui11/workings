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

import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCardPoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 准考证打印池
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCardPool")
public class StudentCardPoolController extends SystemBaseController{
	@Autowired
	private StudentCardPoolService studentCardPoolService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCardPool:studentCardPool")
	String StudentCardPool(){
	    return "center/student/studentCardPool/studentCardPool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCardPool:studentCardPool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCardPoolDO> studentCardPoolList = studentCardPoolService.list(query);
        for (StudentCardPoolDO item : studentCardPoolList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCardPoolService.count(query);
		PageUtils pageUtils = new PageUtils(studentCardPoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCardPool:add")
	String add(Model model){
	    return "center/student/studentCardPool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCardPool:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentCardPoolDO studentCardPool = studentCardPoolService.get(id);
		model.addAttribute("studentCardPool", studentCardPool);
	    return "center/student/studentCardPool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCardPool:add")
	public R save( StudentCardPoolDO studentCardPool){
        studentCardPool.setOperator(ShiroUtils.getUserId().toString());
		if(studentCardPoolService.save(studentCardPool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCardPool:edit")
	public R update( StudentCardPoolDO studentCardPool){
	    studentCardPool.setOperator(ShiroUtils.getUserId().toString());
		studentCardPoolService.update(studentCardPool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCardPool:remove")
	public R remove( String id){
		if(studentCardPoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCardPool:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentCardPoolService.batchRemove(ids);
		return R.ok();
	}
	
}
