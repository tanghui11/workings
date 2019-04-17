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

import com.hxy.nzxy.stexam.domain.ScoreTempDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreTempService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 登分表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/scoreTemp")
public class ScoreTempController extends SystemBaseController{
	@Autowired
	private ScoreTempService scoreTempService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreTemp:scoreTemp")
	String ScoreTemp(){
	    return "center/student/scoreTemp/scoreTemp";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreTemp:scoreTemp")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreTempDO> scoreTempList = scoreTempService.list(query);
        for (ScoreTempDO item : scoreTempList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreTempService.count(query);
		PageUtils pageUtils = new PageUtils(scoreTempList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreTemp:add")
	String add(Model model){
	    return "center/student/scoreTemp/add";
	}

	@GetMapping("/edit/{seatArrangeid}")
	@RequiresPermissions("student:scoreTemp:edit")
	String edit(@PathVariable("seatArrangeid") Long seatArrangeid,Model model){
		ScoreTempDO scoreTemp = scoreTempService.get(seatArrangeid);
		model.addAttribute("scoreTemp", scoreTemp);
	    return "center/student/scoreTemp/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreTemp:add")
	public R save( ScoreTempDO scoreTemp){
        scoreTemp.setOperator(ShiroUtils.getUserId().toString());
		if(scoreTempService.save(scoreTemp)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreTemp:edit")
	public R update( ScoreTempDO scoreTemp){
	    scoreTemp.setOperator(ShiroUtils.getUserId().toString());
		scoreTempService.update(scoreTemp);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreTemp:remove")
	public R remove( Long seatArrangeid){
		if(scoreTempService.remove(seatArrangeid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreTemp:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] seatArrangeids){
		scoreTempService.batchRemove(seatArrangeids);
		return R.ok();
	}
	
}
