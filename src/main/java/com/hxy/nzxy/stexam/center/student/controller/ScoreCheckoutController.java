package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.domain.FirstScoreCheckoutDO;
import com.hxy.nzxy.stexam.center.student.domain.ThirdScoreCheckoutDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreCheckoutService;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *功能描述 成绩数据校验
 * @author ypp
 * @date 2018/11/27
 * @param
 * @return
 */
 
@Controller
@RequestMapping("/student/scoreCheckout")
public class ScoreCheckoutController extends SystemBaseController{
	@Autowired
	private ScoreCheckoutService scoreCheckoutService;
	@GetMapping()
	@RequiresPermissions("student:scoreCheckout:scoreCheckout")
	String PracticeSchool(){
	    return "center/student/scoreCheckout/scoreCheckout";
	}
	
	@ResponseBody
	@GetMapping("/firstScorelist")
	@RequiresPermissions("student:ScoreCheckout:ScoreCheckout")
	public PageUtils firstScorelist(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FirstScoreCheckoutDO> scoreCheckoutList = scoreCheckoutService.firstScoreList(query);
        for (FirstScoreCheckoutDO item : scoreCheckoutList) {

        }
		int total = scoreCheckoutService.firstCount(query);
		PageUtils pageUtils = new PageUtils(scoreCheckoutList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/secondScorelist")
	@RequiresPermissions("student:ScoreCheckout:ScoreCheckout")
	public PageUtils secondScorelist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<FirstScoreCheckoutDO> scoreCheckoutList = scoreCheckoutService.secondScorelist(query);
		for (FirstScoreCheckoutDO item : scoreCheckoutList) {

		}
		int total = scoreCheckoutService.secondCount(query);
		PageUtils pageUtils = new PageUtils(scoreCheckoutList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/thirdScorelist")
	@RequiresPermissions("student:ScoreCheckout:ScoreCheckout")
	public PageUtils thirdScorelist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ThirdScoreCheckoutDO> scoreCheckoutList = scoreCheckoutService.thirdScorelist(query);
		for (ThirdScoreCheckoutDO item : scoreCheckoutList) {

		}
		int total = scoreCheckoutService.thirdCount(query);
		PageUtils pageUtils = new PageUtils(scoreCheckoutList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/fourthScorelist")
	@RequiresPermissions("student:ScoreCheckout:ScoreCheckout")
	public PageUtils fourthScorelist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ThirdScoreCheckoutDO> scoreCheckoutList = scoreCheckoutService.fourthScorelist(query);
		for (ThirdScoreCheckoutDO item : scoreCheckoutList) {

		}
		int total = scoreCheckoutService.fourthCount(query);
		PageUtils pageUtils = new PageUtils(scoreCheckoutList, total);
		return pageUtils;
	}

}