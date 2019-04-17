package com.hxy.nzxy.stexam.center.plan.controller;

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

import com.hxy.nzxy.stexam.domain.CourseFreeCopyDO;
import com.hxy.nzxy.stexam.center.plan.service.CourseFreeCopyCenterService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 课程免考规则copy
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
 
@Controller
@RequestMapping("/plan/courseFreeCopyCenter")
public class CourseFreeCopyCenterController extends SystemBaseController{
	@Autowired
	private CourseFreeCopyCenterService courseFreeCopyCenterService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseFreeCopyCenter:courseFreeCopyCenter")
	String CourseFreeCopyCenter(){
	    return "center/plan/courseFreeCopyCenter/courseFreeCopyCenter";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseFreeCopyCenter:courseFreeCopyCenter")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseFreeCopyDO> courseFreeCopyCenterList = courseFreeCopyCenterService.list(query);
        for (CourseFreeCopyDO item : courseFreeCopyCenterList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseFreeCopyCenterService.count(query);
		PageUtils pageUtils = new PageUtils(courseFreeCopyCenterList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseFreeCopyCenter:add")
	String add(Model model){
	    return "center/plan/courseFreeCopyCenter/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseFreeCopyCenter:edit")
	String edit(@PathVariable("id") String id,Model model){
		CourseFreeCopyDO courseFreeCopyCenter = courseFreeCopyCenterService.get(id);
		model.addAttribute("courseFreeCopyCenter", courseFreeCopyCenter);
	    return "center/plan/courseFreeCopyCenter/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseFreeCopyCenter:add")
	public R save( CourseFreeCopyDO courseFreeCopyCenter){
        courseFreeCopyCenter.setOperator(ShiroUtils.getUserId().toString());
		if(courseFreeCopyCenterService.save(courseFreeCopyCenter)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseFreeCopyCenter:edit")
	public R update( CourseFreeCopyDO courseFreeCopyCenter){
	    courseFreeCopyCenter.setOperator(ShiroUtils.getUserId().toString());
		courseFreeCopyCenterService.update(courseFreeCopyCenter);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseFreeCopyCenter:remove")
	public R remove( String id){
		if(courseFreeCopyCenterService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseFreeCopyCenter:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		courseFreeCopyCenterService.batchRemove(ids);
		return R.ok();
	}
	
}
