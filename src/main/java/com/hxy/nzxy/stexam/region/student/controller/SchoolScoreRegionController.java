package com.hxy.nzxy.stexam.region.student.controller;

import java.text.ParseException;
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

import com.hxy.nzxy.stexam.domain.SchoolScoreDO;
import com.hxy.nzxy.stexam.region.student.service.SchoolScoreRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 平时成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/schoolScoreRegion")
public class SchoolScoreRegionController extends SystemBaseController{
	@Autowired
	private SchoolScoreRegionService schoolScoreRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:schoolScoreRegion:schoolScoreRegion")
	String SchoolScoreRegion(Model model){
		model.addAttribute("stu_school_score_audit_status", commonService.listFieldDict(getAppName(), "stu_school_score", "audit_status"));
	    return "region/student/schoolScoreRegion/schoolScoreRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:schoolScoreRegion:schoolScoreRegion")
	public PageUtils list(@RequestParam Map<String, Object> params) throws ParseException {
		//查询列表数据
        Query query = new Query(params);
			List<SchoolScoreDO> schoolScoreRegionList = schoolScoreRegionService.list(query);
        for (SchoolScoreDO item : schoolScoreRegionList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_school_score", "status", item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_school_score", "audit_status", item.getAuditStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolScoreRegionService.count(query);
		PageUtils pageUtils = new PageUtils(schoolScoreRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:schoolScoreRegion:add")
	String add(Model model){
	    return "region/student/schoolScoreRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:schoolScoreRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolScoreDO schoolScoreRegion = schoolScoreRegionService.get(id);
		model.addAttribute("schoolScoreRegion", schoolScoreRegion);
	    return "region/student/schoolScoreRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:schoolScoreRegion:add")
	public R save( SchoolScoreDO schoolScoreRegion){
        schoolScoreRegion.setOperator(ShiroUtils.getUserId().toString());
		if(schoolScoreRegionService.save(schoolScoreRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:schoolScoreRegion:edit")
	public R update( SchoolScoreDO schoolScoreRegion){
	    schoolScoreRegion.setOperator(ShiroUtils.getUserId().toString());
		schoolScoreRegionService.update(schoolScoreRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:schoolScoreRegion:remove")
	public R remove( Long id){
		if(schoolScoreRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:schoolScoreRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolScoreRegionService.batchRemove(ids);
		return R.ok();
	}


	//平时成绩审核
	@PostMapping("updateStatus")
	@ResponseBody
	@RequiresPermissions("student:schoolScoreRegion:edit")
	public R updateStatus( @RequestParam Map<String, Object> params) throws ParseException {
		String msg = "";
		int k = 0;
		int l = 0;
		List<SchoolScoreDO> schoolScoreRegionList = schoolScoreRegionService.listT(params);
		String operator = ShiroUtils.getUserId().toString();
		if ("1".equals(params.get("sh"))) {
			for (int i = 0; i < schoolScoreRegionList.size(); i++) {
				schoolScoreRegionList.get(i).setAuditStatus("b");
				schoolScoreRegionList.get(i).setOperator(operator);
				k = schoolScoreRegionService.updateStatus(schoolScoreRegionList.get(i));
				if (k == 0) {
					msg = "审核失败";
					return R.error(0, msg);
				}
			}

		}

		if ("2".equals(params.get("sh"))) {
			for (int i = 0; i < schoolScoreRegionList.size(); i++) {
				schoolScoreRegionList.get(i).setAuditStatus("a");
				schoolScoreRegionList.get(i).setOperator(operator);
				l = schoolScoreRegionService.updateStatus(schoolScoreRegionList.get(i));
				if (l == 0) {
					msg = "取消审核失败";
					return R.error(0, msg);
				}
			}
		}
		if (k > 0 && l == 0) {
			msg = "审核成功";
		}
		if (k == 0 && l > 0) {
			msg = "审核取消审核成功";
		}

		return R.ok(msg);
	}
}
