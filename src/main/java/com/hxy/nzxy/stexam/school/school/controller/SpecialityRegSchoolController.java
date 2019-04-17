package com.hxy.nzxy.stexam.school.school.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityRecordService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.school.school.dao.SpecialityRegSchoolDao;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
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

import com.hxy.nzxy.stexam.domain.SpecialityRegDO;
import com.hxy.nzxy.stexam.school.school.service.SpecialityRegSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 专业开设备案
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
 
@Controller
@RequestMapping("/school/specialityRegSchool")
public class SpecialityRegSchoolController extends SystemBaseController{
	@Autowired
	private SpecialityRegSchoolService specialityRegSchoolService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SpecialityRecordService specialityRecordService;
	@Autowired
	private SpecialityService specialityService;
	@GetMapping()
	@RequiresPermissions("school:specialityRegSchool:specialityRegSchool")
	String SpecialityRegSchool(Model model, HttpServletRequest request){
        Map map= new HashMap();
        map.put("status","a");
        map.put("type","b");
        map.put("schoolid",ShiroUtils.getUser().getWorkerid());
        //当前用户的开设专业
        List<SpecialityRecordDO> specialityRecordList = specialityRecordService.list(map);
        //查询对应的专业
        List<String> list=specialityRecordList.stream().map(SpecialityRecordDO::getSpecialityid).collect(Collectors.toList());
        List<SpecialityDO> specialityList=specialityService.listSpeciality(list);
        model.addAttribute("specialityList", specialityList);
	    return "school/school/specialityRegSchool/specialityRegSchool";
	}
	//开设专业弹出层
	@GetMapping("/Speciality")
	String Speciality(Model model, HttpServletRequest request){
		Map map= new HashMap();
		map.put("status","a");
		map.put("type","b");

		//当前用户的开设专业
		List<SpecialityRecordDO> specialityRecordList = specialityRecordService.list(map);
		//查询对应的专业
		List<String> list=specialityRecordList.stream().map(SpecialityRecordDO::getSpecialityid).collect(Collectors.toList());
		List<SpecialityDO> specialityList=specialityService.listSpeciality(list);
		model.addAttribute("specialityList", specialityList);
		return "school/school/specialityRegSchool/speciality";
	}
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("school:specialityRegSchool:specialityRegSchool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("schoolid",ShiroUtils.getUser().getWorkerid());
        Query query = new Query(params);
		List<SpecialityRegDO> specialityRegSchoolList = specialityRegSchoolService.list(query);
        for (SpecialityRegDO item : specialityRegSchoolList) {

            SpecialityRecordDO specialityRecordDO = specialityRecordService.get(item.getSpecialityRecordid());
            List<String> list =new ArrayList<>();
            list.add(specialityRecordDO.getSpecialityid());

            List<SpecialityDO> specialityDOS = specialityService.listSpeciality(list);
           if( specialityDOS.size()>0){
               item.setSpecialityCode(specialityDOS.get(0).getId());
               item.setSpecialityName(specialityDOS.get(0).getName()+" "+specialityRecordDO.getDirection());
           }
            item.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "audit_status", item.getAuditStatus()));
            item.setStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "status", item.getStatus()));

            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = specialityRegSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(specialityRegSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:specialityRegSchool:add")
	String add(Model model, HttpServletRequest request){
		Map map= new HashMap();
		map.put("status","a");
		map.put("type","b");
		map.put("schoolid",ShiroUtils.getUser().getWorkerid());
		//当前用户的开设专业
        List<SpecialityRecordDO> specialityRecordList = specialityRecordService.list(map);
        //查询对应的专业
        List<String> list=specialityRecordList.stream().map(SpecialityRecordDO::getSpecialityid).collect(Collectors.toList());
        List<SpecialityDO> specialityList=specialityService.listSpeciality(list);
		model.addAttribute("specialityList", specialityList);

        model.addAttribute("sch_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "audit_status"));
        model.addAttribute("sch_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "status"));

        return "school/school/specialityRegSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:specialityRegSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SpecialityRegDO specialityRegSchool = specialityRegSchoolService.get(id);
		model.addAttribute("specialityRegSchool", specialityRegSchool);
        model.addAttribute("sch_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "audit_status"));
        model.addAttribute("sch_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "status"));

        return "school/school/specialityRegSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:specialityRegSchool:add")
	public R save( SpecialityRegDO specialityRegSchool,HttpServletRequest request){
        specialityRegSchool.setSchoolid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
        specialityRegSchool.setAuditStatus("a");
		specialityRegSchool.setStatus("a");
        specialityRegSchool.setOperator(ShiroUtils.getUserId().toString());
	//	SpecialityRecordDO specialityReg=  specialityRecordService.getSpecialityRecordid(specialityRegSchool);
		specialityRegSchool.setOperator(ShiroUtils.getUserId().toString());
	//	specialityRegSchool.setSpecialityRecordid(specialityReg.getId());
        if(specialityRegSchoolService.save(specialityRegSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:specialityRegSchool:edit")
	public R update( SpecialityRegDO specialityRegSchool){
	    specialityRegSchool.setOperator(ShiroUtils.getUserId().toString());
		specialityRegSchoolService.update(specialityRegSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:specialityRegSchool:remove")
	public R remove( Long id){
		if(specialityRegSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:specialityRegSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		specialityRegSchoolService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 当前自定义代码是否重复
	 */
	@ResponseBody
	@RequestMapping("/code")
	public int code(@RequestParam Map<String, Object> params){
		//查询列表数据
		int total = specialityRegSchoolService.count(params);
		return total;
	}
    /**
     * 当前专业是否重复
     */
    @ResponseBody
    @RequestMapping("/speciality")
    public int speciality(@RequestParam SpecialityRegDO specialityRegSchool,HttpServletRequest request){
        //查询列表数据

        Map map= new HashMap();
        map.put("status","a");
        map.put("type","b");
        map.put("schoolid",ShiroUtils.getUser().getWorkerid());
        //当前组织的开设专业
        List<SpecialityRecordDO> specialityRecordList = specialityRecordService.list(map);
        List<String> SpecialityidList=specialityRecordList.stream().map(SpecialityRecordDO::getSpecialityid).collect(Collectors.toList());
        boolean checkDeviceCollectCode = SpecialityidList.contains(specialityRegSchool.getSpecialityCode());
        if (checkDeviceCollectCode == true) {
            return 1;
        } else {
            return 0;
        }

    }
	/**
	 * 批量审核接口
	 */
	@PostMapping( "/batchAuditStatus")
	@ResponseBody
	@RequiresPermissions("school:specialityRegSchool:batchAuditStatus")
	public R batchAuditStatus(@RequestParam("ids[]") Long[] ids,@RequestParam("status") String status){
		if(status.equals("a")||status.equals("b")){
			specialityRegSchoolService.batchAuditStatus(ids,status);
			return R.ok();
		}else{
			return R.ok("参数不对");
		}


	}

	/**
	 * 专业开设申请导出
	 * @param params
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchOutYXExcel")
	public String searchOutYXExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "specialityRegSchool/";
		String strZipPath=configsrc+"specialityRegSchoolZip/";
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
		//查询列表数据
		params.put("schoolid",ShiroUtils.getUser().getWorkerid());
		Query query = new Query(params);
		List<SpecialityRegDO> specialityRegSchoolList = specialityRegSchoolService.list(query);
		for (SpecialityRegDO item : specialityRegSchoolList) {

			SpecialityRecordDO specialityRecordDO = specialityRecordService.get(item.getSpecialityRecordid());
			List<String> list =new ArrayList<>();
			list.add(specialityRecordDO.getSpecialityid());

			List<SpecialityDO> specialityDOS = specialityService.listSpeciality(list);
			if( specialityDOS.size()>0){
				item.setSpecialityCode(specialityDOS.get(0).getId());
				item.setSpecialityName(specialityDOS.get(0).getName()+" "+specialityRecordDO.getDirection());
			}
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "audit_status", item.getAuditStatus()));
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_speciality_reg", "status", item.getStatus()));

			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		if (specialityRegSchoolList != null && specialityRegSchoolList.size() > 0) {
			String[][] result = new String[specialityRegSchoolList.size() + 1][8];




			result[0] = new String[] { "序号","专业代码","专业名称","自定义代码","审核状态","使用状态","操作人","操作时间"};
			if (specialityRegSchoolList != null && specialityRegSchoolList.size() > 0) {
				for (int i = 0; i < specialityRegSchoolList.size(); i++) {
					SpecialityRegDO courseFree = specialityRegSchoolList.get(i);
					result[i + 1][0] = String.valueOf(i+1);
					result[i + 1][1] = String.valueOf(courseFree.getSpecialityCode());
					result[i + 1][2] = String.valueOf(courseFree.getSpecialityName());
					result[i + 1][3] = String.valueOf(courseFree.getCode());
					result[i + 1][4] = String.valueOf(courseFree.getAuditStatus());
					result[i + 1][5] = String.valueOf(courseFree.getStatus());
					result[i + 1][6] = String.valueOf(courseFree.getOperator());
					result[i + 1][7] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/specialityRegSchoolList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="专业开设申请导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="专业开设申请导出信息" +date+".zip";
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
			return "school/school/specialityRegSchool/specialityRegSchool";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", specialityRegSchoolList.size());
		return null;
	}

	/**
	 * 获取专业
	 * @param
	 * @return
	 */
	@ResponseBody
	@GetMapping("/schoolSpeciality")
	public PageUtils schoolSpeciality(@RequestParam Map<String, Object> params){
		String schoolid = ShiroUtils.getUser().getWorkerid();
		params.put("schoolid", schoolid);
 		//查询列表数据
		Query query = new Query(params);
		List<SpecialityRegDO> specialityRegSchoolList = specialityRegSchoolService.schoolSpeciality(query);//获取所有speciality_recourdid
		List<SpecialityDO> specialityList = null;
		List<SpecialityDO> specialityList2 = null;
		if(specialityRegSchoolList != null){
			if(!"".equals(query.get("specialityid")) && query.get("specialityid")!= null){
				specialityList = specialityRegSchoolService.getSpeciality(specialityRegSchoolList);
				specialityList2 = specialityRegSchoolService.getSpeciality2(params);
				for(int k =0; k< specialityList.size();k++){
					if (specialityList2.get(0).getId().equals( specialityList.get(k).getId() ) || specialityList2.get(0).getId() == specialityList.get(k).getId()){
						specialityList.clear();
						specialityList.addAll(specialityList2);
					}
				}

			}else{
				specialityList = specialityRegSchoolService.getSpeciality(specialityRegSchoolList);
			}
		}
		int total = specialityRegSchoolService.getSpecialityCount(specialityRegSchoolList);
		PageUtils pageUtils = new PageUtils(specialityList, total);
		return pageUtils;
	}

}
