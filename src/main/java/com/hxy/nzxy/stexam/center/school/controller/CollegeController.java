package com.hxy.nzxy.stexam.center.school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
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
import javax.servlet.http.HttpServletRequest;
import com.hxy.nzxy.stexam.domain.CollegeDO;
import com.hxy.nzxy.stexam.center.school.service.CollegeService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 学院管理
 *
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:26
 */

@Controller
@RequestMapping("/school/college")
public class CollegeController {
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping("{id}")
	@RequiresPermissions("school:college:college")
	String College(@PathVariable("id") String id, Model model, HttpServletRequest request){
		request.getSession().setAttribute("schoolid",id);
		model.addAttribute("id",id);
		return "center/school/college/college";
	}
	@GetMapping()
	@RequiresPermissions("school:college:college")
	String College(){
		return "center/school/college/college";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:college:college")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CollegeDO> collegeList = collegeService.list(query);
		for (CollegeDO item : collegeList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = collegeService.count(query);
		PageUtils pageUtils = new PageUtils(collegeList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("school:college:add")
	String add(){
		return "center/school/college/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:college:edit")
	String edit(@PathVariable("id") String id,Model model){
		CollegeDO college = collegeService.get(id);
		model.addAttribute("college", college);
		return "center/school/college/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:college:add")
	public R save( CollegeDO college,HttpServletRequest request){
	;
		String  schoolid= (String) request.getSession().getAttribute("schoolid");
		college.setSchoolid(schoolid);
		college.setOperator(ShiroUtils.getUserId().toString());
		if(collegeService.save(college)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("sch_college_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(college.getId()+"");
			fieldDict.setFieldMean(college.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.saveCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("添加数据成功，添加缓存失败！");
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:college:edit")
	public R update( CollegeDO college){
		college.setOperator(ShiroUtils.getUserId().toString());
		if(collegeService.update(college)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("sch_college_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(college.getId()+"");
			fieldDict.setFieldMean(college.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.updateCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("修改数据成功，修改缓存失败！");
			}
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:college:remove")
	public R remove( String id){
		if(collegeService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("sch_college_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id);
			fieldDict.setSeq(0);
			if(fieldDictService.removeCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，修改缓存失败！");
			}
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:college:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		if(collegeService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("sch_college_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setSeq(0);
			if(fieldDictService.batchremoveCache(fieldDict,ids)>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，修改缓存失败！");
			}
		}
		return R.error();
	}

	@ResponseBody
	@GetMapping("/list/{schoolid}")
	public PageUtils list(@RequestParam Map<String, Object> params,@PathVariable("schoolid") String schoolid){
		//查询列表数据
		params.put("schoolid",schoolid);
		Query query = new Query(params);
		List<CollegeDO> collegeList = collegeService.list(query);
		for (CollegeDO item : collegeList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = collegeService.count(query);
		PageUtils pageUtils = new PageUtils(collegeList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/seachCollege")

	public List  seachCollege(@RequestParam Map<String, Object> params){
		//查询列表数据

		List<CollegeDO> collegeList = collegeService.seachCollege(params);

		return collegeList;
	}
}
