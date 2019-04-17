package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.service.SeatArrangeScoreService;
import com.hxy.nzxy.stexam.center.region.service.SeatArrangeService;
import com.hxy.nzxy.stexam.center.student.service.ScoreService;
import com.hxy.nzxy.stexam.center.student.service.ScoreTempService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 座次安排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
 
@Controller
@RequestMapping("/region/seatArrangeScore")
public class SeatArrangeScoreController extends SystemBaseController {
	@Autowired
	private SeatArrangeScoreService seatArrangeScoreService;
	@Autowired
	private ScoreTempService scoreTempService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private CommonService commonService;

	private String message;

	@GetMapping()
	@RequiresPermissions("region:seatArrangeScore:seatArrangeScore")
	String SeatArrange() {
		return "center/region/seatArrangeScore/seatArrangeScore";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:seatArrangeScore:seatArrangeScore")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<SeatArrangeDO> seatArrangeList = seatArrangeScoreService.list(query);
		message = "1";
		for (SeatArrangeDO item : seatArrangeList) {

			if (ShiroUtils.getUserId().equals(item.getOperator())) {
				item.setGrade(item.getGrade2());
				item.setExamFlag(item.getExamFlag2());
				message = "2";
			} else if (ShiroUtils.getUserId().equals(item.getOperator1())) {
				item.setGrade(item.getGrade1());
				item.setExamFlag(item.getExamFlag1());

			} else if (item.getExamFlag1() != null) {
				message = "2";
			}
			item.setMessage(message);
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = seatArrangeScoreService.count(query);
		PageUtils pageUtils = new PageUtils(seatArrangeList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("region:seatArrangeScore:add")
	String add(Model model) {
		return "center/region/seatArrangeScore/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:seatArrangeScore:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		SeatArrangeDO seatArrange = seatArrangeScoreService.get(id);
		model.addAttribute("seatArrange", seatArrange);
		return "center/region/seatArrangeScore/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:seatArrangeScore:add")
	public R save(SeatArrangeDO seatArrange) {
		seatArrange.setOperator(ShiroUtils.getUserId().toString());
		if (seatArrangeScoreService.save(seatArrange) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:seatArrangeScore:edit")
	public R update(SeatArrangeDO seatArrange) {
		seatArrange.setOperator(ShiroUtils.getUserId().toString());
		seatArrangeScoreService.update(seatArrange);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("region:seatArrangeScore:remove")
	public R remove(Long id) {
		if (seatArrangeScoreService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:seatArrangeScore:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		seatArrangeScoreService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 保存成绩
	 */
	@ResponseBody
	@PostMapping("/saveScore")
	public R saveScore(ScoreTempDOList scoreTemplist) {
		List<ScoreTempDO> list = scoreTemplist.getScoreTempList();
		if (message.equals("1")) {
			for (ScoreTempDO item : list) {
				if (item.getGrade1() == null) {
					item.setGrade1(0f);
				}
				item.setOperator1(ShiroUtils.getUserId().toString());
			}
			if (scoreTempService.saveList1(list) > 0) {
				return R.ok();
			}
		}
		if (message.equals("2")) {
			for (ScoreTempDO item : list) {
				item.setGrade2(0f);
				item.setOperator(ShiroUtils.getUserId().toString());
			}
			if (scoreTempService.saveList2(list) > 0) {
				return R.ok();
			}
		}

		return R.error();
	}

	@GetMapping("/single")
	@RequiresPermissions("region:seatArrangeScore:seatArrangeScore")
	String SeatArrangeSingle() {
		return "center/region/seatArrangeScore/seatArrangeScoreSingle";
	}

	@ResponseBody
	@GetMapping("/listSingle")
	@RequiresPermissions("region:seatArrangeScore:seatArrangeScore")
	public List listSingle(@RequestParam Map<String, Object> params) {
		//查询列表数据

		List<Map<String, Object>> seatArrangeHisList = seatArrangeScoreService.listSingle(params);
		for (Map<String, Object> item : seatArrangeHisList) {
			item.put("时段", FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.get("时段").toString()));
		}
		;
		return seatArrangeHisList;
	}

	/**
	 * 保存成绩
	 */
	@ResponseBody
	@PostMapping("/saveScoreSingle")
	public R saveScore(ScoreDO score) {
		score.setOperator(ShiroUtils.getUserId().toString());
		score.setStatus("0");
		score.setUseStatus("a");
		score.setFlag("a");
		score.setType("a");
		score.setObjid(score.getObjid());
		if (scoreService.save(score) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateScoreSingle")
	public R updateScoreSingle(ScoreDO score) {
		score.setOperator(ShiroUtils.getUserId().toString());
		int i = scoreService.updateScoreSingle(score);
		if(i==0){
			score.setOperator(ShiroUtils.getUserId().toString());
			score.setStatus("0");
			score.setUseStatus("a");
			score.setFlag("a");
			score.setType("a");
			score.setObjid(score.getObjid());
			scoreService.save(score);
		}
		return R.ok();
	}

	@GetMapping("/updateScore")
	String updateScore() {
		return "center/region/seatArrangeScore/seatArrangeScore";
	}

	@ResponseBody
	@GetMapping("/listUpdateScore")
	@RequiresPermissions("region:seatArrangeScore:seatArrangeScore")
	public PageUtils listUpdateScore(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<SeatArrangeDO> seatArrangeList = seatArrangeScoreService.list(query);
		for (SeatArrangeDO item : seatArrangeList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = seatArrangeScoreService.count(query);
		PageUtils pageUtils = new PageUtils(seatArrangeList, total);
		return pageUtils;
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateUpdateScore")
	public R updateUpdateScore(ScoreTempDO scoreTempDO) {
		//查出正式表数据
		ScoreDO score =	scoreTempService.listStuScore2(scoreTempDO);
		//将一样的数据录入正式表
		score.setStatus("0");
		score.setUseStatus("a");
		score.setFlag("a");
		if (scoreService.save(score) > 0) {
			scoreTempService.remove(scoreTempDO.getSeatArrangeid());
			return R.ok();
		}
		return R.error();
	}
}