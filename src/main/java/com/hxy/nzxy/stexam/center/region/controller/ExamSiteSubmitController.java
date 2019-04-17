package com.hxy.nzxy.stexam.center.region.controller;

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

import com.hxy.nzxy.stexam.domain.ExamSiteSubmitDO;
import com.hxy.nzxy.stexam.center.region.service.ExamSiteSubmitService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考点上报
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/examSiteSubmit")
public class ExamSiteSubmitController extends SystemBaseController{
	@Autowired
	private ExamSiteSubmitService examSiteSubmitService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:examSiteSubmit:examSiteSubmit")
	String ExamSiteSubmit(){
	    return "center/region/examSiteSubmit/examSiteSubmit";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examSiteSubmit:examSiteSubmit")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamSiteSubmitDO> examSiteSubmitList = examSiteSubmitService.list(query);
        for (ExamSiteSubmitDO item : examSiteSubmitList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examSiteSubmitService.count(query);
		PageUtils pageUtils = new PageUtils(examSiteSubmitList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:examSiteSubmit:add")
	String add(Model model){
	    return "center/region/examSiteSubmit/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examSiteSubmit:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamSiteSubmitDO examSiteSubmit = examSiteSubmitService.get(id);
		model.addAttribute("examSiteSubmit", examSiteSubmit);
	    return "center/region/examSiteSubmit/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examSiteSubmit:add")
	public R save( ExamSiteSubmitDO examSiteSubmit){
        examSiteSubmit.setOperator(ShiroUtils.getUserId().toString());
		if(examSiteSubmitService.save(examSiteSubmit)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examSiteSubmit:edit")
	public R update( ExamSiteSubmitDO examSiteSubmit){
	    examSiteSubmit.setOperator(ShiroUtils.getUserId().toString());
		examSiteSubmitService.update(examSiteSubmit);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examSiteSubmit:remove")
	public R remove( Long id){
		if(examSiteSubmitService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:examSiteSubmit:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examSiteSubmitService.batchRemove(ids);
		return R.ok();
	}
	
}
