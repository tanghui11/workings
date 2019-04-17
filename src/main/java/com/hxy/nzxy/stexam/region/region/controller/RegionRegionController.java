package com.hxy.nzxy.stexam.region.region.controller;

import java.io.IOException;
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

import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.region.region.service.RegionRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 考区设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/regionRegion")
public class RegionRegionController extends SystemBaseController{
	@Autowired
	private RegionRegionService regionRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:regionRegion:regionRegion")
	String RegionRegion(Model model){
		String regType = ShiroUtils.getUser().getRegType();

		model.addAttribute("region",ShiroUtils.getUser().getWorkerid());
		model.addAttribute("reg_region_type", commonService.listFieldDict(getAppName(), "reg_region", "type"));
		model.addAttribute("reg_region_photo_flag", commonService.listFieldDict(getAppName(), "reg_region", "photo_flag"));
		if (regType.equals("a")){
			return "region/region/regionRegion/regionRegion";
		}else{
			RegionDO regionRegion = regionRegionService.get(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
			model.addAttribute("regionRegion", regionRegion);
			return "region/region/regionRegion/regionRegionXian";
		}

	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:regionRegion:regionRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){

			//查询列表数据
			Query query = new Query(params);
			List<RegionDO> regionRegionList = regionRegionService.list(query);
			for (RegionDO item : regionRegionList) {
				item.setType(FieldDictUtil.get(getAppName(), "reg_region", "type", item.getType()));
				item.setPhotoFlag(FieldDictUtil.get(getAppName(), "reg_region", "photo_flag", item.getPhotoFlag()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = regionRegionService.count(query);
			PageUtils pageUtils = new PageUtils(regionRegionList, total);
			return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:regionRegion:add")
	String add(Model model){
	    return "region/region/regionRegion/add";
	}

	@ResponseBody
	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:regionRegion:edit")
	RegionDO edit(@PathVariable("id") Long id,Model model){
		RegionDO regionRegion = regionRegionService.get(id);
		model.addAttribute("regionRegion", regionRegion);
		model.addAttribute("reg_region_type", commonService.listFieldDict(getAppName(), "reg_region", "type"));
		model.addAttribute("reg_region_photo_flag", commonService.listFieldDict(getAppName(), "reg_region", "photo_flag"));
	    return regionRegion;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:regionRegion:add")
	public R save( RegionDO regionRegion){

		if(regionRegion.getType().equals("b")){
			RegionDO regionDO = regionRegionService.get(regionRegion.getParentid());
			regionRegion.setParentName(regionDO.getName());
		}

        regionRegion.setOperator(ShiroUtils.getUserId().toString());
		if(regionRegionService.save(regionRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:regionRegion:edit")
	public R update( RegionDO regionRegion){
	    regionRegion.setOperator(ShiroUtils.getUserId().toString());
		regionRegionService.update(regionRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:regionRegion:remove")
	public R remove( Long id){
		if(regionRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:regionRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		regionRegionService.batchRemove(ids);
		return R.ok();
	}


	//导入页面
	@GetMapping("/QXimportData")

	String QXimportData( ){

		return "region/region/regionRegion/QXimportData";
	}


	/**
	 * 批量导入
	 */
	@PostMapping("/QXsavBathData")
	@RequiresPermissions("region:regionRegion:add")
	public String TsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "region/region/regionRegion/QXimportData";
		}

		//获取文件名
		String fileName=file.getOriginalFilename();

		//验证文件名是否合格
	/*	if(!ExcelImportUtils.validateExcel(fileName)){
			session.setAttribute("msg","文件必须是excel格式！");
			return "redirect:toUserKnowledgeImport";
		}*/

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size=file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			request.setAttribute("msg","文件不能为空！");
			return "region/region/regionRegion/QXimportData";
		}

		//批量导入
		String message = regionRegionService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "region/region/regionRegion/QXimportData";
	}
}
