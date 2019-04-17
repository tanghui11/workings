package com.hxy.nzxy.stexam.region.exam.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.service.TaskService;
import com.hxy.nzxy.stexam.domain.TaskDO;
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

import com.hxy.nzxy.stexam.domain.SecretOfficeDO;
import com.hxy.nzxy.stexam.region.exam.service.SecretOfficeRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 保密室维护
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
 
@Controller
@RequestMapping("/exam/secretOfficeRegion")
public class SecretOfficeRegionController extends SystemBaseController{
	@Autowired
	private SecretOfficeRegionService secretOfficeRegionService;
    @Autowired
    private CommonService commonService;
    @Autowired
	private TaskService taskService;
	@GetMapping()
	@RequiresPermissions("exam:secretOfficeRegion:secretOfficeRegion")
	String SecretOfficeRegion(){
	    return "region/exam/secretOfficeRegion/secretOfficeRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:secretOfficeRegion:secretOfficeRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//获取当前用户id
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		//查询列表数据
        Query query = new Query(params);
		List<SecretOfficeDO> secretOfficeRegionList = secretOfficeRegionService.list(query);
        for (SecretOfficeDO item : secretOfficeRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "exa_secret_office", "type", item.getType()));
			item.setHasForce(FieldDictUtil.get(getAppName(), "exa_secret_office", "has_force", item.getHasForce()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "exa_secret_office", "audit_status", item.getAuditStatus()));
		}
		int total = secretOfficeRegionService.count(query);
		PageUtils pageUtils = new PageUtils(secretOfficeRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:secretOfficeRegion:add")
	String add(Model model){
		model.addAttribute("exa_secret_office_type", commonService.listFieldDict(getAppName(), "exa_secret_office", "type"));
		model.addAttribute("exa_secret_office_has_force", commonService.listFieldDict(getAppName(), "exa_secret_office", "has_force"));
		model.addAttribute("exa_secret_office_audit_status", commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status"));

	    return "region/exam/secretOfficeRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:secretOfficeRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SecretOfficeDO secretOfficeRegion = secretOfficeRegionService.get(id);
		/*TaskDO taskDO = taskService.get(secretOfficeRegion.getExamTaskid());
		secretOfficeRegion.setExamTaskName();*/
		model.addAttribute("secretOfficeRegion", secretOfficeRegion);
		model.addAttribute("exa_secret_office_type", commonService.listFieldDict(getAppName(), "exa_secret_office", "type"));
		model.addAttribute("exa_secret_office_has_force", commonService.listFieldDict(getAppName(), "exa_secret_office", "has_force"));
		model.addAttribute("exa_secret_office_audit_status", commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status"));

		return "region/exam/secretOfficeRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:secretOfficeRegion:add")
	public R save( SecretOfficeDO secretOfficeRegion){
		secretOfficeRegion.setRegionid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
		secretOfficeRegion.setAuditStatus("a");
        secretOfficeRegion.setOperator(ShiroUtils.getUserId().toString());
		if(secretOfficeRegionService.save(secretOfficeRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:secretOfficeRegion:edit")
	public R update( SecretOfficeDO secretOfficeRegion){
	    secretOfficeRegion.setOperator(ShiroUtils.getUserId().toString());
		secretOfficeRegionService.update(secretOfficeRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:secretOfficeRegion:remove")
	public R remove( Long id){
		if(secretOfficeRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:secretOfficeRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		secretOfficeRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
