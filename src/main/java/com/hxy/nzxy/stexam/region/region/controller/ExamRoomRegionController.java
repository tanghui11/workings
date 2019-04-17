package com.hxy.nzxy.stexam.region.region.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.ExamSiteDO;
import com.hxy.nzxy.stexam.domain.ExamSiteSubmitDO;
import com.hxy.nzxy.stexam.region.region.service.ExamSiteRegionService;
import com.hxy.nzxy.stexam.region.region.service.ExamSiteSubmitRegionService;
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

import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.region.region.service.ExamRoomRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考场
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:33
 */
 
@Controller
@RequestMapping("/region/examRoomRegion")
public class ExamRoomRegionController extends SystemBaseController{
	@Autowired
	private ExamRoomRegionService examRoomRegionService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private ExamSiteRegionService examSiteRegionService;
	@Autowired
	private ExamSiteSubmitRegionService examSiteSubmitRegionService;
	@GetMapping()
	@RequiresPermissions("region:examRoomRegion:examRoomRegion")
	String ExamRoomRegion(){
	    return "region/region/examRoomRegion/examRoomRegion";
	}
	/**
	 * 考点信息
	 */
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examRoomRegion:examRoomRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		String regionid= String.valueOf(params.get("regionid"));

		if(regionid!=null&&!"".equals(regionid)) {
			List<ExamSiteDO> examSiteList = examSiteRegionService.list(query);
			for (ExamSiteDO item : examSiteList) {
				item.setStatus(FieldDictUtil.get(getAppName(), "reg_exam_site", "status", item.getStatus()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = examSiteRegionService.count(query);
			PageUtils pageUtils = new PageUtils(examSiteList, total);
			return pageUtils;
		}else {
			return new PageUtils(new ArrayList<>(), 0);
		}
	}

	/**
	 * 已上报考点信息
	 */
	@ResponseBody
	@GetMapping("/ylist")
	@RequiresPermissions("region:examSiteSubmitRegion:examSiteSubmitRegion")
	public PageUtils ylist(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		Query query = new Query(params);

			List<ExamSiteSubmitDO> examSiteSubmitRegionList = examSiteSubmitRegionService.list(query);
			for (ExamSiteSubmitDO item : examSiteSubmitRegionList) {
				item.setStatus(FieldDictUtil.get(getAppName(), "reg_exam_site_submit", "status", item.getStatus()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = examSiteSubmitRegionService.count(query);
			PageUtils pageUtils = new PageUtils(examSiteSubmitRegionList, total);
			return pageUtils;

	}
	@ResponseBody
	@GetMapping("/listRoomRegion")
	@RequiresPermissions("region:examRoomRegion:examRoomRegion")
	public PageUtils listRoomRegion(@RequestParam Map<String, Object> params){
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		//查询列表数据
        Query query = new Query(params);
		List<ExamRoomDO> examRoomRegionList = examRoomRegionService.list(query);
        for (ExamRoomDO item : examRoomRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examRoomRegionService.count(query);
		PageUtils pageUtils = new PageUtils(examRoomRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:examRoomRegion:add")
	String add(Model model){
	    return "region/region/examRoomRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examRoomRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamRoomDO examRoomRegion = examRoomRegionService.get(id);
		model.addAttribute("examRoomRegion", examRoomRegion);
	    return "region/region/examRoomRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examRoomRegion:add")
	public R save(@RequestParam Map<String, Object> params){
		//获取所有已上报的考点
			//查询列表数据
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		params.put("status","a");
		List<ExamRoomDO> ListRoom1=examRoomRegionService.getSite(params);
		for ( ExamRoomDO room1:ListRoom1
			 ) {
		List  list=new ArrayList();
		Integer roomNo = room1.getRoomNo();
		if(roomNo>0){
			for(int i=1;i<=roomNo;i++){
				ExamRoomDO room=new ExamRoomDO();
				room.setRegionid(room1.getRegionid());
				room.setExamTaskid(room1.getExamTaskid());
				room.setExamSiteSubmitid(room1.getExamSiteSubmitid());
				room.setExamSiteid(room1.getExamSiteid());
				room.setRoomNo(i);
				room.setSeatNum(30);
				room.setOperator(ShiroUtils.getUserId().toString());
				list.add(room);
			}
		}

		if(	examRoomRegionService.batchSave(list)>0){

			ExamSiteSubmitDO examSiteSubmitDO=  new ExamSiteSubmitDO();
			examSiteSubmitDO.setId(room1.getId());
			examSiteSubmitDO.setStatus("b");
			examSiteSubmitRegionService.update(examSiteSubmitDO);

				}
      //  examRoomRegion.setOperator(ShiroUtils.getUserId().toString());
		//if(examRoomRegionService.save(examRoomRegion)>0){
		//	return R.ok();
	//	}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examRoomRegion:edit")
	public R update( ExamRoomDO examRoomRegion){
	    examRoomRegion.setOperator(ShiroUtils.getUserId().toString());
		examRoomRegionService.update(examRoomRegion);
		return R.ok();
	}
	
	/**
	 * 删除考场（上报考点的删除）
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examRoomRegion:remove")
	public R remove( Long id){
		if(examSiteSubmitRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	/**
	 * 删除初始化
	 */
	@PostMapping( "/removeRoom")
	@ResponseBody
	@RequiresPermissions("region:examRoomRegion:remove")
	public R removeRoom( @RequestParam Map<String, Object> params){
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		params.put("status","b");
		List<ExamRoomDO> ListRoom1=examRoomRegionService.getSite(params);
		for ( ExamRoomDO room1:ListRoom1
				) {
			ExamSiteSubmitDO examSiteSubmitDO=  new ExamSiteSubmitDO();
			examSiteSubmitDO.setId(room1.getId());
			examSiteSubmitDO.setStatus("a");
			examSiteSubmitRegionService.update(examSiteSubmitDO);
			if(examRoomRegionService.remove(room1.getId())>0){


			}
		}
			return R.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:examRoomRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examRoomRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
