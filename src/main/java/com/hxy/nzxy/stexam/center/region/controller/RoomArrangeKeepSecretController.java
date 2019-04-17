package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.service.RoomArrangeService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamTimeDO;
import com.hxy.nzxy.stexam.domain.RoomArrangeDO;
import com.hxy.nzxy.stexam.domain.RoomArrangeKeepSecretDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 保密室
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/roomArrangeKeepSecret")
public class RoomArrangeKeepSecretController extends SystemBaseController{
	@Autowired
	private RoomArrangeService roomArrangeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:roomArrangeKeepSecret:roomArrangeKeepSecret")
	String RoomArrange(){
	    return "center/region/roomArrangeKeepSecret/roomArrangeKeepSecret";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:roomArrangeKeepSecret:roomArrangeKeepSecret")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoomArrangeKeepSecretDO> roomArrangeList = roomArrangeService.listKeepSecret(query);
        for (RoomArrangeKeepSecretDO item : roomArrangeList) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
		}
		int total = roomArrangeService.countKeepSecret(query);
		PageUtils pageUtils = new PageUtils(roomArrangeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:roomArrangeKeepSecret:add")
	String add(Model model){
	    return "center/region/roomArrange/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:roomArrangeKeepSecret:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RoomArrangeDO roomArrange = roomArrangeService.get(id);
		model.addAttribute("roomArrange", roomArrange);
	    return "center/region/roomArrange/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:roomArrangeKeepSecret:add")
	public R save( RoomArrangeDO roomArrange){
        roomArrange.setOperator(ShiroUtils.getUserId().toString());
		if(roomArrangeService.save(roomArrange)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 生成保密号
	 */
	@ResponseBody
	@RequestMapping("/updateKeepSecret")
	@RequiresPermissions("region:roomArrangeKeepSecret:edit")
	public R update(@RequestParam Map<String, Object> params){
		List<RoomArrangeDO> list = roomArrangeService.list(params);
		RoomArrangeDO roomArrangeDO=new RoomArrangeDO();

		Collections.shuffle(list);
		int number1 =  Integer.valueOf(params.get("number").toString());
		int number = number1;
		for (RoomArrangeDO item : list) {
			if(String.valueOf(number).length()>4){
				item.setSecretCode(number+"");
			}else if(String.valueOf(number).length()>3){
				item.setSecretCode("0"+number);
			}else if(String.valueOf(number).length()>2){
				item.setSecretCode("00"+number);
			}else if(String.valueOf(number).length()>1){
				item.setSecretCode("000"+number);
			}else{
				item.setSecretCode("0000"+number);
			}
			RoomArrangeDO roomArrangeDO1 = roomArrangeService.get(item.getId());
			//获取首考号
			roomArrangeDO= roomArrangeService.getFirstStudentid(roomArrangeDO1);
			item.setFirstStudentid(roomArrangeDO.getFirstStudentid());
			number++;
		}
		roomArrangeService.batchUpdate(list);
		return R.ok();
	}
	
	/**
	 * 取消保密号
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeKeepSecret:remove")
	public R remove( Long id){
		if(roomArrangeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/updateKeepSecretQx")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeKeepSecret:batchRemove")
	public R remove(@RequestParam Map<String, Object> params){
		List<RoomArrangeDO> list = roomArrangeService.list(params);
		roomArrangeService.batchUpdateQx(list);
		return R.ok();
	}
	
}
