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

import com.hxy.nzxy.stexam.domain.CourseFreeSpecialityDO;
import com.hxy.nzxy.stexam.center.plan.service.CourseFreeSpecialityCenterService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 课程免考规则专业
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
 
@Controller
@RequestMapping("/plan/courseFreeSpecialityCenter")
public class CourseFreeSpecialityCenterController extends SystemBaseController{
	@Autowired
	private CourseFreeSpecialityCenterService courseFreeSpecialityCenterService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseFreeSpecialityCenter:courseFreeSpecialityCenter")
	String CourseFreeSpecialityCenter(){
	    return "center/plan/courseFreeSpecialityCenter/courseFreeSpecialityCenter";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseFreeSpecialityCenter:courseFreeSpecialityCenter")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseFreeSpecialityDO> courseFreeSpecialityCenterList = courseFreeSpecialityCenterService.list(query);
        for (CourseFreeSpecialityDO item : courseFreeSpecialityCenterList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseFreeSpecialityCenterService.count(query);
		PageUtils pageUtils = new PageUtils(courseFreeSpecialityCenterList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseFreeSpecialityCenter:add")
	String add(Model model){
	    return "center/plan/courseFreeSpecialityCenter/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseFreeSpecialityCenter:edit")
	String edit(@PathVariable("id") String id,Model model){
		CourseFreeSpecialityDO courseFreeSpecialityCenter = courseFreeSpecialityCenterService.get(id);
		model.addAttribute("courseFreeSpecialityCenter", courseFreeSpecialityCenter);
	    return "center/plan/courseFreeSpecialityCenter/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseFreeSpecialityCenter:add")
	public R save( CourseFreeSpecialityDO courseFreeSpecialityCenter){
        courseFreeSpecialityCenter.setOperator(ShiroUtils.getUserId().toString());
		if(courseFreeSpecialityCenterService.save(courseFreeSpecialityCenter)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseFreeSpecialityCenter:edit")
	public R update( CourseFreeSpecialityDO courseFreeSpecialityCenter){
	    courseFreeSpecialityCenter.setOperator(ShiroUtils.getUserId().toString());
		courseFreeSpecialityCenterService.update(courseFreeSpecialityCenter);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseFreeSpecialityCenter:remove")
	public R remove( String id){
		if(courseFreeSpecialityCenterService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseFreeSpecialityCenter:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		courseFreeSpecialityCenterService.batchRemove(ids);
		return R.ok();
	}
	
}
