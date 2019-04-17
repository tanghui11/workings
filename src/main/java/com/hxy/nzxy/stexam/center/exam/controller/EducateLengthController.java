package com.hxy.nzxy.stexam.center.exam.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
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

import com.hxy.nzxy.stexam.domain.EducateLengthDO;
import com.hxy.nzxy.stexam.center.exam.service.EducateLengthService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 学制定义
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
 
@Controller
@RequestMapping("/exam/educateLength")
public class EducateLengthController extends SystemBaseController{
	@Autowired
	private EducateLengthService educateLengthService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:educateLength:educateLength")
	String EducateLength(){
	    return "center/exam/educateLength/educateLength";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:educateLength:educateLength")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EducateLengthDO> educateLengthList = educateLengthService.list(query);
        for (EducateLengthDO item : educateLengthList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
			item.setLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getLength()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = educateLengthService.count(query);
		PageUtils pageUtils = new PageUtils(educateLengthList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:educateLength:add")
	String add(Model model){
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_classify", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "classify"));
		model.addAttribute("sch_school_speciality_reg_educate_length", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "educate_length"));
		return "center/exam/educateLength/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:educateLength:edit")
	String edit(@PathVariable("id") Long id,Model model){
		EducateLengthDO educateLength = educateLengthService.get(id);
		model.addAttribute("educateLength", educateLength);
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_classify", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "classify"));
		model.addAttribute("sch_school_speciality_reg_educate_length", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "educate_length"));
	    return "center/exam/educateLength/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:educateLength:add")
	public R save( EducateLengthDO educateLength){
			//学制开关
			Configuration config = getConfig("config.properties");
			String educate_length =config.getString("educate_length");
			if(educate_length.equals("true")){
				educateLength.setLength("b");
			}
        educateLength.setOperator(ShiroUtils.getUserId().toString());
		if(educateLengthService.save(educateLength)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:educateLength:edit")
	public R update( EducateLengthDO educateLength){
	    educateLength.setOperator(ShiroUtils.getUserId().toString());
		educateLengthService.update(educateLength);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:educateLength:remove")
	public R remove( Long id){
		if(educateLengthService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:educateLength:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		educateLengthService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 学制接口
	 */
	@ResponseBody
	@GetMapping("/listEducateLength")
	public List<EducateLengthDO> listEducateLength(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<EducateLengthDO> educateLengthList = educateLengthService.list(params);
		for (EducateLengthDO item : educateLengthList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
			item.setLengthName(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getLength()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return educateLengthList;
	}
}
