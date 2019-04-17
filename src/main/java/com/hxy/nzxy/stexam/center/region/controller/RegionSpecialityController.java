package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.service.RegionService;
import com.hxy.nzxy.stexam.center.region.service.RegionSpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.RegionSpecialityDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考区专业课程报考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/regionSpeciality")
public class RegionSpecialityController extends SystemBaseController{
	@Autowired
	private RegionSpecialityService regionSpecialityService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private RegionService regionService;
	@GetMapping()
	@RequiresPermissions("region:regionSpeciality:regionSpeciality")
	String RegionSpeciality(@RequestParam Map<String, String> params ,Model model){

		model.addAttribute("regionid",params.get("regionid"));

		return "center/region/regionSpeciality/regionSpeciality";
}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:regionSpeciality:regionSpeciality")
	public PageUtils list(@RequestParam Map<String, Object> params,Model model){
		//查询列表数据
		Query query = new Query(params);
		String regionid= String.valueOf(params.get("regionid"));
//		params.put("specialityid",params.get(o));
		if(regionid!=null&&!"".equals(regionid)) {

			List<RegionSpecialityDO> regionSpecialityList = regionSpecialityService.list(query);
			for (RegionSpecialityDO item : regionSpecialityList) {
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
				item.setSubjectName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSubjectCode())+item.getDirection());

			}
			int total = regionSpecialityService.count(query);
			PageUtils pageUtils = new PageUtils(regionSpecialityList, total);

		return pageUtils;}
		else
		{

			return new PageUtils(new ArrayList<>(), 0);
		}
	}

	@GetMapping("/add")
	@RequiresPermissions("region:regionSpeciality:add")
	String add(@RequestParam Map<String, String> params,Model model){
		String regionid =params.get("regionid");
		if(regionid!=null &&!"".equals(regionid))
		{
			RegionDO region = regionService.get(Long.valueOf(regionid));
			model.addAttribute("region", region);
			model.addAttribute("regionName", region.getName()+"("+region.getId()+")");
		}
	    return "center/region/regionSpeciality/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:regionSpeciality:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RegionSpecialityDO regionSpeciality = regionSpecialityService.get(id);
		model.addAttribute("regionSpeciality", regionSpeciality);
	    return "center/region/regionSpeciality/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:regionSpeciality:add")
	public R save( RegionSpecialityDO regionSpeciality){
        regionSpeciality.setOperator(ShiroUtils.getUserId().toString());

		if(regionSpecialityService.save(regionSpeciality)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:regionSpeciality:edit")
	public R update( RegionSpecialityDO regionSpeciality){
	    regionSpeciality.setOperator(ShiroUtils.getUserId().toString());
		regionSpecialityService.update(regionSpeciality);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:regionSpeciality:remove")
	public R remove( Long id){
	    if(id!=null) {
            if (regionSpecialityService.remove(id) > 0) {
                return R.ok();
            }
            return R.error();
        }else
        {
            { return R.ok("无数据删除");}
        }
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:regionSpeciality:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
	    if(ids!=null) {
            regionSpecialityService.batchRemove(ids);
            return R.ok();
        }else
        { return R.ok("无数据删除");}
	}
	@ResponseBody
	@PostMapping("/bathSave")
	//@RequiresPermissions("region:regionSpeciality:bathSave")
	public R batchSave(@RequestParam Map<String, Object> map){

		List<RegionSpecialityDO>  list = regionSpecialityService.seachSubjectIdlist(map);
		if(list!=null &&list.size()>0) {
            list.forEach(item -> {
                item.setOperator(ShiroUtils.getUserId().toString());
                item.setRegionid(Long.valueOf(String.valueOf(map.get("regionid"))));
            });
            if (regionSpecialityService.batchSave(list) > 0) {
                return R.ok("全部授权成功！");
            }else
            {
                return R.error();
            }
        }else
        {
            return R.ok("没有授权的专业！");
        }

	}

    /**
     * 显示考区专业权限页面
     * @param model
     * @param params
     * @return
     */
    @GetMapping("/showRegionSubject")
    String Courses(Model model,@RequestParam Map<String, Object> params){
        String regionid=(String)params.get("regionid");
        System.out.println(regionid);
        if(regionid!=null &&!"".equals(regionid)) {
            RegionDO region = regionService.get(Long.valueOf(regionid));
            model.addAttribute("region", region);
        }
        return "center/region/regionSpeciality/showRegionSubject";
    }
    @ResponseBody
    @RequestMapping ("/seachSubjectIdlist")
    //@RequiresPermissions("region:regionSpeciality:bathSave")
    public PageUtils seachSubjectIdlist(@RequestParam Map<String, Object> map){

		//查询列表数据
		Query query = new Query(map);
		List<RegionSpecialityDO> regionSpecialityList = regionSpecialityService.seachSubjectIdlist(query);
		for (RegionSpecialityDO item : regionSpecialityList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = regionSpecialityService.countSubject(query);
		PageUtils pageUtils = new PageUtils(regionSpecialityList, total);
		return pageUtils;
    }

	//弹出导入页面
	@GetMapping("/importData")
	String importData( String regionid,Model model){
    	model.addAttribute("regionid",regionid);
		return "center/region/regionSpeciality/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("region:regionSpeciality:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session,String regionid
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/region/regionSpeciality/importData";
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
			return "center/region/regionSpeciality/importData";
		}

		//批量导入
		String message = regionSpecialityService.batchImport(fileName,file,regionid);
		request.setAttribute("msg",message);
		return "center/region/regionSpeciality/importData";
	}

	//导出
	@RequestMapping("/searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params,HttpServletRequest request, HttpServletResponse response){
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
		List<RegionSpecialityDO> regionSpecialityList = regionSpecialityService.list(params);
		request.getSession().setAttribute("totalCount", regionSpecialityList.size());
		for (RegionSpecialityDO item : regionSpecialityList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setSubjectName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSubjectCode())+item.getDirection());

		}
		if (regionSpecialityList != null && regionSpecialityList.size() > 0) {
			String[][] result = new String[regionSpecialityList.size() + 1][6];
			result[0] = new String[] { "序号","专业代码", "专业名称","操作员", "操作日期"};
			if (regionSpecialityList != null && regionSpecialityList.size() > 0) {
				for (int i = 0; i < regionSpecialityList.size(); i++) {
					RegionSpecialityDO regionSpecialityDO = regionSpecialityList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(regionSpecialityDO.getSubjectCode());
					result[i + 1][2] = String.valueOf(regionSpecialityDO.getSubjectName());
					result[i + 1][3] = String.valueOf(regionSpecialityDO.getOperator());
					result[i + 1][4] = String.valueOf(regionSpecialityDO.getUpdateDate());

					//进度条写入进度
					double dPercent=(double)(i)/regionSpecialityList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%

				}
			}
			String tempFileName="考区专业报考设置导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="考区专业报考设置导出信息" +date+".zip";
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
			return "center/plan/regionSpeciality/regionSpeciality";
		}
		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", regionSpecialityList.size());
		return null;
	}






}