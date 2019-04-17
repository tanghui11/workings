package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.service.GroupsService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.GroupsDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 集体设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/groups")
public class GroupsController extends SystemBaseController{
	@Autowired
	private GroupsService groupsService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("region:groups:groups")
	String Groups(Model model){
		model.addAttribute("reg_groups_type", commonService.listFieldDict(getAppName(), "reg_groups", "type"));
		model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
		model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
		return "center/region/groups/groups";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GroupsDO> groupsList = groupsService.list(query);
        for (GroupsDO item : groupsList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			//item.setType(FieldDictUtil.get(getAppName(), "reg_groups", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "reg_groups", "classify", item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "reg_groups", "status", item.getStatus()));
        }
		int total = groupsService.count(query);
		PageUtils pageUtils = new PageUtils(groupsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:groups:add")
	String add(Model model){
		//model.addAttribute("reg_groups_type", commonService.listFieldDict(getAppName(), "reg_groups", "type"));
		model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
		model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
		return "center/region/groups/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:groups:edit")
	String edit(@PathVariable("id") Long id,Model model){
		GroupsDO groups = groupsService.get(id);
		model.addAttribute("groups", groups);
		//model.addAttribute("reg_groups_type", commonService.listFieldDict(getAppName(), "reg_groups", "type"));
		model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
		model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
	    return "center/region/groups/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:groups:add")
	public R save( GroupsDO groups){
        groups.setOperator(ShiroUtils.getUserId().toString());
        groups.setRegionid("0");
        groups.setType("b");
		if(groupsService.save(groups)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(groups.getId()+"");
			fieldDict.setFieldMean(groups.getName());
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
	@RequiresPermissions("region:groups:edit")
	public R update( GroupsDO groups){
	    groups.setOperator(ShiroUtils.getUserId().toString());
		if(groupsService.update(groups)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("reg_groups_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(groups.getId()+"");
			fieldDict.setFieldMean(groups.getName());
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
	@RequiresPermissions("region:groups:remove")
	public R remove( Long id){
		if(groupsService.remove(id)>0){
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
	@RequiresPermissions("region:groups:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		if(groupsService.batchRemove(ids)>0)
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
		return R.error();
	}
	@RequestMapping("groupList")
	String groupList(Model model){
		model.addAttribute("reg_groups_type", commonService.listFieldDict(getAppName(), "reg_groups", "type"));
		model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
		model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
		return "center/region/groups/groupsList";
	}

	//导出
	@RequestMapping("/searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params,HttpServletRequest request, HttpServletResponse response, Model model){
		//进度条清session
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "Student/";
		String strZipPath=configsrc+"StudentZip/";
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
		ZipOutputStream out = null;
		List<GroupsDO> groupsList = groupsService.list(params);
		request.getSession().setAttribute("totalCount", groupsList.size());
		for (GroupsDO item : groupsList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			//item.setType(FieldDictUtil.get(getAppName(), "reg_groups", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "reg_groups", "classify", item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "reg_groups", "status", item.getStatus()));
		}
		if (groupsList != null && groupsList.size() > 0) {
			String[][] result = new String[groupsList.size() + 1][9];
			result[0] = new String[] { "序号","代码", "名称","拼音", "层次", "状态","操作员","操作日期"};
			if (groupsList != null && groupsList.size() > 0) {
				for (int i = 0; i < groupsList.size(); i++) {
					GroupsDO groupsDO = groupsList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(groupsDO.getCode());
					result[i + 1][2] = String.valueOf(groupsDO.getName());
					result[i + 1][3] = String.valueOf(groupsDO.getPinyin());
					result[i + 1][4] = String.valueOf(groupsDO.getClassify());
					result[i + 1][5] = String.valueOf(groupsDO.getStatus());
					result[i + 1][6] = String.valueOf(groupsDO.getOperator());
					result[i + 1][7] = String.valueOf(groupsDO.getUpdateDate());
					//进度条写入进度
					double dPercent=(double)(i)/groupsList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="集体信息维护导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="集体信息维护导出信息" +date+".zip";
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
			model.addAttribute("reg_groups_type", commonService.listFieldDict(getAppName(), "reg_groups", "type"));
			model.addAttribute("reg_groups_classify", commonService.listFieldDict(getAppName(), "reg_groups", "classify"));
			model.addAttribute("reg_groups_status", commonService.listFieldDict(getAppName(), "reg_groups", "status"));
			return "center/region/groups/groups";
		}
		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", groupsList.size());
		return null;
	}
	//弹出导入页面
	@GetMapping("/importData")
	String importData( ){
		return "center/region/groups/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("region:groups:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/region/groups/importData";
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
			return "center/region/groups/importData";
		}

		//返回错误信息
		String message = groupsService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/region/groups/importData";
	}
}