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

import com.hxy.nzxy.stexam.domain.FoulPunishDO;
import com.hxy.nzxy.stexam.center.exam.service.FoulPunishService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 犯规及处罚设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
 
@Controller
@RequestMapping("/exam/foulPunish")
public class FoulPunishController extends SystemBaseController{
	@Autowired
	private FoulPunishService foulPunishService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:foulPunish:foulPunish")
	String FoulPunish(Model model){
		model.addAttribute("exa_foul_punish_type", commonService.listFieldDict(getAppName(), "exa_foul_punish", "type"));
		model.addAttribute("exa_foul_punish_flag", commonService.listFieldDict(getAppName(), "exa_foul_punish", "flag"));

		return "center/exam/foulPunish/foulPunish";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:foulPunish:foulPunish")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FoulPunishDO> foulPunishList = foulPunishService.list(query);
        for (FoulPunishDO item : foulPunishList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "exa_foul_punish", "type", item.getType()));
			item.setFlag(FieldDictUtil.get(getAppName(), "exa_foul_punish", "flag", item.getFlag()));
		}
		int total = foulPunishService.count(query);
		PageUtils pageUtils = new PageUtils(foulPunishList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:foulPunish:add")
	String add(Model model){

		model.addAttribute("exa_foul_punish_type", commonService.listFieldDict(getAppName(), "exa_foul_punish", "type"));
		model.addAttribute("exa_foul_punish_flag", commonService.listFieldDict(getAppName(), "exa_foul_punish", "flag"));

		return "center/exam/foulPunish/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:foulPunish:edit")
	String edit(@PathVariable("id") Long id,Model model){
		FoulPunishDO foulPunish = foulPunishService.get(id);
		model.addAttribute("foulPunish", foulPunish);
		model.addAttribute("exa_foul_punish_type", commonService.listFieldDict(getAppName(), "exa_foul_punish", "type"));
		model.addAttribute("exa_foul_punish_flag", commonService.listFieldDict(getAppName(), "exa_foul_punish", "flag"));
		return "center/exam/foulPunish/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:foulPunish:add")
	public R save( FoulPunishDO foulPunish){
        foulPunish.setOperator(ShiroUtils.getUserId().toString());
		if(foulPunishService.save(foulPunish)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:foulPunish:edit")
	public R update( FoulPunishDO foulPunish){
	    foulPunish.setOperator(ShiroUtils.getUserId().toString());
		foulPunishService.update(foulPunish);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:foulPunish:remove")
	public R remove( Long id){
		if(foulPunishService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:foulPunish:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		foulPunishService.batchRemove(ids);
		return R.ok();
	}
	
}
