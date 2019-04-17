package com.hxy.nzxy.stexam.region.region.controller;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceDO;
import com.hxy.nzxy.stexam.region.region.dao.GroupsRegionDao;
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

import com.hxy.nzxy.stexam.domain.GroupsDO;
import com.hxy.nzxy.stexam.region.region.service.GroupsRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 集体设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/groupsRegion")
public class GroupsRegionController extends SystemBaseController{
	@Autowired
	private GroupsRegionService groupsRegionService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("region:groupsRegion:groupsRegion")
	String GroupsRegion(Model model){
		model.addAttribute("reg_groups_type", commonService.listFieldDict(getAppName(), "reg_groups", "type"));
		model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
		model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
		return "region/region/groupsRegion/groupsRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//获取当前用户id
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		//查询列表数据
        Query query = new Query(params);
		List<GroupsDO> groupsRegionList = groupsRegionService.list(query);
        for (GroupsDO item : groupsRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setClassify(FieldDictUtil.get(getAppName(), "reg_groups", "classify", item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "reg_groups", "status", item.getStatus()));
        }
		int total = groupsRegionService.count(query);
		PageUtils pageUtils = new PageUtils(groupsRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:groupsRegion:add")
	String add(Model model){
		model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
		model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
		return "region/region/groupsRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:groupsRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		GroupsDO groupsRegion = groupsRegionService.get(id);
		model.addAttribute("groupsRegion", groupsRegion);
		model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
		model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
	    return "region/region/groupsRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:groupsRegion:add")
	public R save( GroupsDO groupsRegion){
		groupsRegion.setRegionid(ShiroUtils.getUser().getWorkerid());
		groupsRegion.setType("a");
        groupsRegion.setOperator(ShiroUtils.getUserId().toString());
		if(groupsRegionService.save(groupsRegion)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(groupsRegion.getId()+"");
			fieldDict.setFieldMean(groupsRegion.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.saveCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("保存数据成功，修改缓存失败！");
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:groupsRegion:edit")
	public R update( GroupsDO groupsRegion){
	    groupsRegion.setOperator(ShiroUtils.getUserId().toString());
		if(groupsRegionService.update(groupsRegion)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(groupsRegion.getId()+"");
			fieldDict.setFieldMean(groupsRegion.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.updateCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("修改数据成功，修改缓存失败！");
			}
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:groupsRegion:remove")
	public R remove( Long id){
		if(groupsRegionService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id+"");
			if(fieldDictService.removeCache(fieldDict)>0) {
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
	@RequiresPermissions("region:groupsRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		if(groupsRegionService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			if(fieldDictService.batchremoveCache(fieldDict,CommonUtil.longToString(ids) )>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.ok();
	}

	@RequestMapping("/groupList")
	String groupList(Model model){
		model.addAttribute("reg_groups_type", commonService.listFieldDict(getAppName(), "reg_groups", "type"));
		model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
		model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
		return "region/region/groupsRegion/groupsList";
	}


	/**
	 * 通用课程顶替批量导出
	 * @param params
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchOutRGExcel")
	public String searchOutRGExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "groupsRegion/";
		String strZipPath=configsrc+"groupsRegionZip/";
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
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		List<GroupsDO> groupsRegionList = groupsRegionService.list(params);
		request.getSession().setAttribute("totalCount", groupsRegionList.size());
		for (GroupsDO item : groupsRegionList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setClassify(FieldDictUtil.get(getAppName(), "reg_groups", "classify", item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "reg_groups", "status", item.getStatus()));
		}
		if (groupsRegionList != null && groupsRegionList.size() > 0) {
			String[][] result = new String[groupsRegionList.size() + 1][7];
			result[0] = new String[] {"代码","名称","拼音","层次","状态","操作员","操作日期"};
			if (groupsRegionList != null && groupsRegionList.size() > 0) {
				for (int i = 0; i < groupsRegionList.size(); i++) {
					GroupsDO courseFree = groupsRegionList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getCode());
					result[i + 1][1] = String.valueOf(courseFree.getName());
					result[i + 1][2] = String.valueOf(courseFree.getPinyin());
					result[i + 1][3] = String.valueOf(courseFree.getClassify());
					result[i + 1][4] = String.valueOf(courseFree.getStatus());
					result[i + 1][5] = String.valueOf(courseFree.getOperator());
					result[i + 1][6] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/groupsRegionList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="集体代码导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="集体代码导出信息" +date+".zip";
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
			return "region/region/groupsRegion/groupsRegion";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", groupsRegionList.size());
		return null;
	}


	/***
	 * this is jumpPage
	 * 导入页面
	 */

	@GetMapping("/JTimportData")

	String JTimportData( ){

		return "region/region/groupsRegion/JTimportData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/JTsavBathData")
	@RequiresPermissions("region:groupsRegion:add")
	public String JTsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "region/region/groupsRegion/JTimportData";
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
			return "region/region/groupsRegion/JTimportData";
		}

		//批量导入
		String message = groupsRegionService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "region/region/groupsRegion/JTimportData";
	}
}
