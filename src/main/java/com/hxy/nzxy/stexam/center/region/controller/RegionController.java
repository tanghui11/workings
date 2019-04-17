package com.hxy.nzxy.stexam.center.region.controller;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.domain.CourseFreeDO;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
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

import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.center.region.service.RegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考区设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/region")
public class RegionController extends SystemBaseController{
	@Autowired
	private RegionService regionService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("region:region:region")
	String Region(Model model){
		model.addAttribute("reg_region_type", commonService.listFieldDict(getAppName(), "reg_region", "type"));
		model.addAttribute("reg_region_photo_flag", commonService.listFieldDict(getAppName(), "reg_region", "photo_flag"));
	    return "center/region/region/region";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:region:region")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RegionDO> regionList = regionService.list(query);
        for (RegionDO item : regionList) {
			item.setType(FieldDictUtil.get(getAppName(), "reg_region", "type", item.getType()));
			item.setPhotoFlag(FieldDictUtil.get(getAppName(), "reg_region", "photo_flag", item.getPhotoFlag()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = regionService.count(query);
		PageUtils pageUtils = new PageUtils(regionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:region:add")
	String add(Model model){
		model.addAttribute("reg_region_type", commonService.listFieldDict(getAppName(), "reg_region", "type"));
		model.addAttribute("reg_region_photo_flag", commonService.listFieldDict(getAppName(), "reg_region", "photo_flag"));

		return "center/region/region/add";
	}
	@ResponseBody
	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:region:edit")
	RegionDO  edit(@PathVariable("id") Long id,Model model){
		RegionDO region = regionService.get(id);
		model.addAttribute("region", region);
		model.addAttribute("reg_region_type", commonService.listFieldDict(getAppName(), "reg_region", "type"));
		model.addAttribute("reg_region_photo_flag", commonService.listFieldDict(getAppName(), "reg_region", "photo_flag"));
		return region;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:region:add")
	public R save( RegionDO region){
		if(region.getType().equals("b")){
			RegionDO regionDO = regionService.get(region.getParentid());
			region.setParentName(regionDO.getName());
		}

        region.setOperator(ShiroUtils.getUserId().toString());
		if(regionService.save(region)>0) {
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_region_nzxy_a");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(region.getId() + "");
			fieldDict.setFieldMean(region.getName());
			fieldDict.setSeq(0);
			if ("b".equals(region.getType()))
			{
				FieldDictDO fieldDictb = new FieldDictDO();
			    fieldDictb.setAppid(Constant.APPID);
			    fieldDictb.setTableName("reg_region_nzxy_b");
			    fieldDictb.setFieldName("id");
			    fieldDictb.setFieldValue(region.getId() + "");
			    fieldDictb.setFieldMean(region.getParentName());
			    fieldDictb.setSeq(0);
		    }
			if(fieldDictService.saveCache(fieldDict)>0) {
				fieldDict.setAppid(Constant.APPID);
				fieldDict.setTableName("reg_region_nzxy");
				fieldDict.setFieldName("code");
				fieldDict.setFieldValue(region.getId() + "");
				fieldDict.setFieldMean(region.getCode()+" "+region.getName());
				fieldDict.setSeq(0);
				fieldDictService.saveCache(fieldDict);


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
	@RequiresPermissions("region:region:edit")
	public R update( RegionDO region){

	    region.setOperator(ShiroUtils.getUserId().toString());
		if(regionService.update(region)>0) {
			//批量修改关联数据parentName
			if (region.getType().equals("a")) {
				RegionDO reg = new RegionDO();
				reg.setParentid(region.getId());
				reg.setParentName(region.getName());
				regionService.updateParentName(reg);
			}
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_region_nzxy_a");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(region.getId() + "");
			fieldDict.setFieldMean(region.getName());
			fieldDict.setSeq(0);
			if ("b".equals(region.getType()))
			{
				FieldDictDO fieldDictb = new FieldDictDO();
				fieldDictb.setAppid(Constant.APPID);
				fieldDictb.setTableName("reg_region_nzxy_b");
				fieldDictb.setFieldName("id");
				fieldDictb.setFieldValue(region.getId() + "");
				fieldDictb.setFieldMean(region.getParentName());
				fieldDictb.setSeq(0);
				fieldDictService.updateCache(fieldDictb);
			}
			if(fieldDictService.updateCache(fieldDict)>0) {
				fieldDict.setAppid(Constant.APPID);
				fieldDict.setTableName("reg_region_nzxy");
				fieldDict.setFieldName("code");
				fieldDict.setFieldValue(region.getId() + "");
				fieldDict.setFieldMean(region.getCode()+" "+region.getName());
				fieldDict.setSeq(0);
				fieldDictService.updateCache(fieldDict);
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
	@RequiresPermissions("region:region:remove")
	public R remove( Long id,String type){
		if(regionService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_region_nzxy_a");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id + "");

			fieldDict.setSeq(0);
			if ("b".equals(type))
			{
				FieldDictDO fieldDictb = new FieldDictDO();
				fieldDictb.setAppid(Constant.APPID);
				fieldDictb.setTableName("reg_region_nzxy_b");
				fieldDictb.setFieldName("id");
				fieldDictb.setFieldValue(id + "");
				fieldDict.setSeq(0);
				fieldDictService.removeCache(fieldDictb);
			}
			if(fieldDictService.removeCache(fieldDict)>0) {
				RegionDO region = regionService.get(id);
				fieldDict.setAppid(Constant.APPID);
				fieldDict.setTableName("reg_region_nzxy");
				fieldDict.setFieldName("code");
				fieldDict.setFieldValue(region.getId() + "");
				fieldDict.setFieldMean(region.getCode()+" "+region.getName());
				fieldDict.setSeq(0);
				fieldDictService.removeCache(fieldDict);

				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:region:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		regionService.batchRemove(ids);
		return R.ok();
	}
	//考区接口
	@ResponseBody
	@RequestMapping("/regionList")
	public List<RegionDO>  regionList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<RegionDO> regionList = regionService.list(params);
		for (RegionDO item : regionList) {
			item.setType(FieldDictUtil.get(getAppName(), "reg_region", "type", item.getType()));
			item.setPhotoFlag(FieldDictUtil.get(getAppName(), "reg_region", "photo_flag", item.getPhotoFlag()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return regionList;
	}
	//查询考办根据regionid接口
	@ResponseBody
	@RequestMapping("/getregionByregionid")
	public RegionDO  getregionByregionid(@RequestParam("id") Long id){
		//查询列表数据
		RegionDO region = regionService.get(id);
		return region;
	}


	//导入页面
	@GetMapping("/KimportData")

	String KimportData( ){

		return "center/region/region/KimportData";
	}
/******************************************************************************************/
	/**
	 * 批量导入
	 */
	@PostMapping("/KsavBathData")
	@RequiresPermissions("region:region:add")
	public String KsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/region/region/KimportData";
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
			return "center/region/region/KimportData";
		}

		//批量导入
		String message = regionService.KbatchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/region/region/KimportData";
	}

	//考区批量导出
	@RequestMapping("/searchOutKExcel")
	public String searchOutKEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request, Model model){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "courseFreeCenter/";
		String strZipPath=configsrc+"courseFreeCenterZip/";
		File file = new File(Filepath);
		if (!file.exists()) {
			file.mkdirs();
		}else {
			String[] tempList = file.list();
			File tempFile = null;
			for (int i = 0; i < tempList.length; i++) {
				if (Filepath.endsWith("/")) {
					tempFile = new File(Filepath+tempList[i]);
				}else {
					tempFile=new File(Filepath+"/"+tempList[i]);
				}
				if (tempFile.isFile()) {
					tempFile.delete();
				}
			}
		}
		File fileZip = new File(strZipPath);
		if (!fileZip.exists()) {
			fileZip.mkdirs();
		}else {
			String[] tempList = fileZip.list();
			File tempFile = null;
			for (int i = 0; i < tempList.length; i++) {
				if (Filepath.endsWith("/")) {
					tempFile = new File(Filepath+tempList[i]);
				}else {
					tempFile=new File(Filepath+"/"+tempList[i]);
				}
				if (tempFile.isFile()) {
					tempFile.delete();
				}
			}
		}

		ZipOutputStream out=null;
		List<RegionDO> courseFreeList = regionService.list(params);
		request.getSession().setAttribute("totalCount", courseFreeList.size());
		for (RegionDO item : courseFreeList) {
			item.setType(FieldDictUtil.get(getAppName(), "reg_region", "type", item.getType()));
			item.setPhotoFlag(FieldDictUtil.get(getAppName(), "reg_region", "photo_flag", item.getPhotoFlag()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		if (courseFreeList != null && courseFreeList.size() > 0) {
			String[][] result = new String[courseFreeList.size() + 1][20];


			result[0] = new String[] { "父编号","代码","类别","名称","父名称"	,"拼音","联系人","联系地址","邮编","固定电话","移动电话","传真","邮箱","是否能照相","考区转移","考场规格","考试提示","考场编排序号","操作员"	,"操作日期"};
			if (courseFreeList != null && courseFreeList.size() > 0) {
				for (int i = 0; i < courseFreeList.size(); i++) {
					RegionDO courseFree = courseFreeList.get(i);
					result[i + 1][0] = String.valueOf( courseFree.getParentid());
					result[i + 1][1] = String.valueOf(courseFree.getCode());
					result[i + 1][2] = String.valueOf(courseFree.getType());
					result[i + 1][3] = String.valueOf(courseFree.getName());
					result[i + 1][4] = String.valueOf(courseFree.getParentName());
					result[i + 1][5] = String.valueOf(courseFree.getPinyin());
					result[i + 1][6] = String.valueOf(courseFree.getLinkman());
					result[i + 1][7] =String.valueOf(courseFree.getAddress());
					result[i + 1][8] =String.valueOf(courseFree.getPostCode());
					result[i + 1][9] =String.valueOf(courseFree.getPhone());
					result[i + 1][10] =String.valueOf(courseFree.getMphone());
					result[i + 1][11] =String.valueOf(courseFree.getFax());
					result[i + 1][12] =String.valueOf(courseFree.getEmail());
					result[i + 1][13] =String.valueOf(courseFree.getPhotoFlag());
					result[i + 1][14] =String.valueOf(courseFree.getExamTransfer());
					result[i + 1][15] =String.valueOf(courseFree.getRoomSize());
					result[i + 1][16] =String.valueOf(courseFree.getExamMsg());
					result[i + 1][17] =String.valueOf(courseFree.getSeq());
					result[i + 1][18] =String.valueOf(courseFree.getOperator());
					result[i + 1][19] =String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/courseFreeList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;

				}
			}
			String tempFileName="考区设置导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="考区设置导出信息" +date+".zip";
			ZipUtils.createZip(Filepath,strZipPath+zipName);
			try {
				File fs = new File(strZipPath+zipName);
				//检查该文件是否存在
				if(!fs.exists()){
					return null;
				}
				buffer1 = new BufferedInputStream(new FileInputStream(fs));
				byte[] buf = new byte[1024];
				int len = 0;
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-disposition", "attachment;filename="+new String(fs.getName().getBytes("gbk"), "iso8859-1"));
				out1 = response.getOutputStream();
				while((len = buffer1.read(buf)) >0){
					out1.write(buf,0,len);
				}
			}catch(Throwable e)
			{
			}finally
			{
				try
				{
					buffer1.close();
					out1.close();
				}catch(Throwable e)
				{

					e.printStackTrace();
				}
			}
		}
		else
		{
			model.addAttribute("reg_region_type", commonService.listFieldDict(getAppName(), "reg_region", "type"));
			model.addAttribute("reg_region_photo_flag", commonService.listFieldDict(getAppName(), "reg_region", "photo_flag"));
			return "center/region/region/region";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", courseFreeList.size());
		return null;
	}
}
