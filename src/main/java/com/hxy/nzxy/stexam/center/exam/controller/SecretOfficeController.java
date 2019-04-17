package com.hxy.nzxy.stexam.center.exam.controller;

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

import com.hxy.nzxy.stexam.domain.SecretOfficeDO;
import com.hxy.nzxy.stexam.center.exam.service.SecretOfficeService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 保密室维护
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
 
@Controller
@RequestMapping("/exam/secretOffice")
public class SecretOfficeController extends SystemBaseController{
	@Autowired
	private SecretOfficeService secretOfficeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:secretOffice:secretOffice")
	String SecretOffice(Model model){
		model.addAttribute("exa_secret_office_audit_status", commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status"));

		return "center/exam/secretOffice/secretOffice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:secretOffice:secretOffice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SecretOfficeDO> secretOfficeList = secretOfficeService.list(query);
        for (SecretOfficeDO item : secretOfficeList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "exa_secret_office", "type", item.getType()));
			item.setHasForce(FieldDictUtil.get(getAppName(), "exa_secret_office", "has_force", item.getHasForce()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "exa_secret_office", "audit_status", item.getAuditStatus()));
        }
		int total = secretOfficeService.count(query);
		PageUtils pageUtils = new PageUtils(secretOfficeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:secretOffice:add")
	String add(Model model){
	    return "center/exam/secretOffice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:secretOffice:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SecretOfficeDO secretOffice = secretOfficeService.get(id);
		model.addAttribute("secretOffice", secretOffice);
		model.addAttribute("exa_secret_office_type", commonService.listFieldDict(getAppName(), "exa_secret_office", "type"));
		model.addAttribute("exa_secret_office_has_force", commonService.listFieldDict(getAppName(), "exa_secret_office", "has_force"));
		model.addAttribute("exa_secret_office_audit_status", commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status"));

		return "center/exam/secretOffice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:secretOffice:add")
	public R save( SecretOfficeDO secretOffice){
        secretOffice.setOperator(ShiroUtils.getUserId().toString());
		if(secretOfficeService.save(secretOffice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:secretOffice:edit")
	public R update( SecretOfficeDO secretOffice){
	    secretOffice.setOperator(ShiroUtils.getUserId().toString());
		secretOfficeService.update(secretOffice);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:secretOffice:remove")
	public R remove( Long id){
		if(secretOfficeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量审核通过
	 */
	@PostMapping( "/batchAudit")
	@ResponseBody
	@RequiresPermissions("exam:secretOffice:batchAudit")
	public R batchAudit(@RequestParam("ids[]") Long[] ids){
		secretOfficeService.batchAudit(ids);
		return R.ok();
	}
	/**
	 * 批量审核不通过
	 */
	@PostMapping( "/batchAuditNo")
	@ResponseBody
	@RequiresPermissions("exam:secretOffice:batchAudit")
	public R batchAuditNo(@RequestParam("ids[]") Long[] ids){
		secretOfficeService.batchAuditNo(ids);
		return R.ok();
	}
}
