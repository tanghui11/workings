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

import com.hxy.nzxy.stexam.domain.ExamSiteSubmitHisDO;
import com.hxy.nzxy.stexam.center.region.service.ExamSiteSubmitHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考点上报_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/examSiteSubmitHis")
public class ExamSiteSubmitHisController extends SystemBaseController{
	@Autowired
	private ExamSiteSubmitHisService examSiteSubmitHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:examSiteSubmitHis:examSiteSubmitHis")
	String ExamSiteSubmitHis(){
	    return "center/region/examSiteSubmitHis/examSiteSubmitHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examSiteSubmitHis:examSiteSubmitHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamSiteSubmitHisDO> examSiteSubmitHisList = examSiteSubmitHisService.list(query);
        for (ExamSiteSubmitHisDO item : examSiteSubmitHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examSiteSubmitHisService.count(query);
		PageUtils pageUtils = new PageUtils(examSiteSubmitHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:examSiteSubmitHis:add")
	String add(Model model){
	    return "center/region/examSiteSubmitHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examSiteSubmitHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamSiteSubmitHisDO examSiteSubmitHis = examSiteSubmitHisService.get(id);
		model.addAttribute("examSiteSubmitHis", examSiteSubmitHis);
	    return "center/region/examSiteSubmitHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examSiteSubmitHis:add")
	public R save( ExamSiteSubmitHisDO examSiteSubmitHis){
        examSiteSubmitHis.setOperator(ShiroUtils.getUserId().toString());
		if(examSiteSubmitHisService.save(examSiteSubmitHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examSiteSubmitHis:edit")
	public R update( ExamSiteSubmitHisDO examSiteSubmitHis){
	    examSiteSubmitHis.setOperator(ShiroUtils.getUserId().toString());
		examSiteSubmitHisService.update(examSiteSubmitHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examSiteSubmitHis:remove")
	public R remove( Long id){
		if(examSiteSubmitHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:examSiteSubmitHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examSiteSubmitHisService.batchRemove(ids);
		return R.ok();
	}
	
}
