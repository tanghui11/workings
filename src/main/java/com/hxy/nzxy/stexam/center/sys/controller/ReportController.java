package com.hxy.nzxy.stexam.center.sys.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.service.TaskService;
import com.hxy.nzxy.stexam.center.region.service.RegionService;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.school.school.service.CollegeSchoolService;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.center.sys.service.ReportService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 报表管理表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-12-11 17:56:42
 */
 
@Controller
@RequestMapping("/sys/report")
public class ReportController extends SystemBaseController{
	@Autowired
	private ReportService reportService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private TaskService taskService;
	@Autowired
    private RegionService regionService;
	@Autowired
	private CollegeSchoolService collegeSchoolService;
	@GetMapping()
	@RequiresPermissions("sys:report:report")
	String Report(Model model){
		model.addAttribute("appList", commonService.listAllApp(null));
		model.addAttribute("oa_notify_type", commonService.listFieldDict(getAppName(), "oa_notify", "type"));
		model.addAttribute("oa_notify_isTop", commonService.listFieldDict(getAppName(), "oa_notify", "is_top"));
		model.addAttribute("oa_notify_status", commonService.listFieldDict(getAppName(), "oa_notify", "status"));
		model.addAttribute("exa_exam_item_exam_month", commonService.listFieldDict(getAppName(), "exa_exam_item", "exam_month"));
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
		model.addAttribute("exam_task_type", commonService.listFieldDict(getAppName(), "exam_task", "type"));
		model.addAttribute("exa_secret_office_audit_status", commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status"));
		model.addAttribute("sch_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "audit_status"));
		model.addAttribute("sch_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_speciality_reg", "status"));
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
	    return "center/sys/report/report";
	}

	@GetMapping("/JHGL")
	@RequiresPermissions("sys:report:report")
	String JHGL(Model model){

		return "center/sys/report/jhglPage";
	}
	@GetMapping("/zyzsrs")
	@RequiresPermissions("sys:report:report")
	String zyzsrs(Model model){

		return "center/sys/report/zyzsrsPage";
	}
	@GetMapping("/zxgl")
	@RequiresPermissions("sys:report:report")
	String zxgl(Model model){

		return "center/sys/report/zxglPage";
	}
	@GetMapping("/mtgl")
	@RequiresPermissions("sys:report:report")
	String mtgl(Model model){

		return "center/sys/report/mtglPage";
	}
	@GetMapping("/kjgl")
	@RequiresPermissions("sys:report:report")
	String kjgl(Model model){

		return "center/sys/report/kjglPage";
	}
    @GetMapping("/dzskbgl")
    @RequiresPermissions("sys:report:report")
    String dzskbgl(Model model){

        return "center/sys/report/dzskbglPage";
    }
    @GetMapping("/xqkbgl")
    @RequiresPermissions("sys:report:report")
    String xqkbgl(Model model){

        return "center/sys/report/xqkbglPage";
    }
	@GetMapping("/bkgl")
	@RequiresPermissions("sys:report:report")
	String bkgl(Model model){

		return "center/sys/report/bkglPage";
	}
	@GetMapping("/xznkszcb")
	@RequiresPermissions("sys:report:report")
	String xznkszcb(Model model){

		return "center/sys/report/xznkszcb";
	}
    @GetMapping("/xiaLa")
    @ResponseBody
    public List xiaLa(String  z){
        Map map= new HashMap();
		switch(z){
			case "appList":
				return commonService.listAllApp(null);
			case "oa_notify_type":
				return commonService.listFieldDict(getAppName(), "oa_notify", "type");
			case "oa_notify_status":
				return commonService.listFieldDict(getAppName(), "oa_notify", "status");
			case "exa_exam_item_exam_month":
				return commonService.listFieldDict(getAppName(), "exa_exam_item", "exam_month");
			case "exa_exam_course_type":
				return commonService.listFieldDict(getAppName(), "exa_exam_course", "type");
			case "exa_exam_course_classify":
				return commonService.listFieldDict(getAppName(), "exa_exam_course", "classify");
			case "exa_exam_course_card_type":
				return commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type");
			case "exam_task_type":
				return commonService.listFieldDict(getAppName(), "exam_task", "type");
			case "exa_secret_office_audit_status":
				return commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status");
			case "sch_speciality_reg_audit_status":
				return commonService.listFieldDict(getAppName(), "sch_speciality_reg", "audit_status");
			case "sch_speciality_reg_status":
				return commonService.listFieldDict(getAppName(), "sch_speciality_reg", "status");
			case "stu_student_gender":
				return commonService.listFieldDict(getAppName(), "stu_student", "gender");
			case "stu_student_home_type":
				return commonService.listFieldDict(getAppName(), "stu_student", "home_type");
			case "stu_student_politics":
				return commonService.listFieldDict(getAppName(), "stu_student", "politics");
			case "stu_student_nation":
				return commonService.listFieldDict(getAppName(), "stu_student", "nation");
			case "stu_student_profession":
				return commonService.listFieldDict(getAppName(), "stu_student", "profession");
			case "pla_speciality_record_type":
				return commonService.listFieldDict(getAppName(), "pla_speciality_record", "type");
			case "pla_speciality_record_type_all":
				List<FieldDictDO> fieldDictDOS = commonService.listFieldDict(getAppName(), "pla_speciality_record",
						"type");
				FieldDictDO field= new  FieldDictDO();
				field.setId("x");
				field.setName("全部");
				fieldDictDOS.add(0,field);
				return fieldDictDOS;
			case "stu_student_certificate_type":
				return commonService.listFieldDict(getAppName(), "stu_student", "certificate_type");
			case "stu_student_speciality_education":
				return commonService.listFieldDict(getAppName(), "stu_student_speciality", "education");
			case "oa_notify_isTop":
				return commonService.listFieldDict(getAppName(), "oa_notify", "is_top");
			case "sch_school_speciality_reg_reg_season":
				return commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "reg_season");
			case "bk_task":
				 map= new HashMap();
				map.put("type","a");
				map.put("confirm_status","b");
				map.put("audit_status","b");
                List<TaskDO> list = taskService.serchTaskAll(map);
				for (TaskDO item : list) {
					item.setExamMonth( FieldDictUtil.get(getAppName(), "exam_task", "exam_month",item.getExamMonth()));
					item.setConfirmStatus(FieldDictUtil.get(getAppName(), "exam_task", "confirm_status",item.getConfirmStatus()));
					item.setAuditStatus(FieldDictUtil.get(getAppName(), "exam_task", "audit_status",item.getAuditStatus()));
					item.setName(item.getExamYear()+"年"+item.getExamMonth());
				}
				return list;
            case "childrenRegion":
                map= new HashMap();
                map.put("parentid",ShiroUtils.getUser().getWorkerid());

                List<RegionDO>  list1 = regionService.list(map);
                for (RegionDO item : list1) {
                    item.setName(item.getCode()+" "+item.getName());
                }
                return list1;
			case "school_college":
				map= new HashMap();
				//查询列表数据
				String type = ShiroUtils.getUser().getType();
				if(type.equals("3")){
					map.put("schoolid",ShiroUtils.getUser().getWorkerid());
				}else if(type.equals("4")){
					map.put("id",ShiroUtils.getUser().getWorkerid());
				}
				List<CollegeDO> collegeSchoolList = collegeSchoolService.listCollege(map);
				for (CollegeDO item : collegeSchoolList) {
				}
				return collegeSchoolList;
			default:
				System.out.println("default");break;
		}




        return null;
    }
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:report:report")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ReportDO> reportList = reportService.list(query);
        for (ReportDO item : reportList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = reportService.count(query);
		PageUtils pageUtils = new PageUtils(reportList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:report:add")
	String add(Model model){
	    return "center/sys/report/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:report:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ReportDO report = reportService.get(id);
		model.addAttribute("report", report);
	    return "center/sys/report/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:report:add")
	public R save( ReportDO report){
        report.setOperator(ShiroUtils.getUserId().toString());
		if(reportService.save(report)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:report:edit")
	public R update( ReportDO report){
	    report.setOperator(ShiroUtils.getUserId().toString());
		reportService.update(report);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:report:remove")
	public R remove( Integer id){
	    if(id==null){
            return R.error("请选择要进行删除的数据!");
        }
		if(reportService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:report:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
        if(ids==null){
            return R.error("请选择要进行删除的数据!");
        }

		reportService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 方法中包含了一个数量参数是确定有几条sql的，sqlNum
	 * @param map
	 * @return
	 */
	@PostMapping("/reports")
	@ResponseBody
	@RequiresPermissions("sys:report:report")
	public R createReport(@RequestParam Map<String, Object> map){
		String appid = map.get("appid").toString();
		String code = map.get("code").toString();
		String name = map.get("name").toString();
		String param = "#"+map.get("param").toString()+"#";
		String modelid = map.get("modelid").toString();
		String path = map.get("path").toString();
		String operator = ShiroUtils.getUserId().toString();
		int sqlNum = Integer.valueOf(map.get("sqlNum").toString());
		map.put("param",param);
		map.put("reportId",map.get("code"));
		String msg = "";
		String i ="";
		int y =0;
		map.put("operator", operator);
		if("".equals(appid) || "".equals(code) || "".equals(name) || "".equals(param) || "".equals(modelid) || "".equals(path)){
			msg = "模板创建失败！";
			return R.error(0,msg);
		}else{
			if(reportService.isInDB(map) == 0){
				 reportService.keepInDB(map);
				 i = reportService.list(map).get(0).getId();

			}else{
				msg = "该报表编号已存在";
				return R.error(0,msg);
			}

			if (i != "" ) {
				for(int l =0; l< sqlNum; l++){
					if(l==0){
						y = reportService.keepSQL(Integer.parseInt(i), map.get("type1").toString(), map.get("sql1").toString(), operator);
					}
					if(l==1){
						y = reportService.keepSQL(Integer.parseInt(i), map.get("type2").toString(), map.get("sql2").toString(), operator);
					}
					if(l==2){
						y = reportService.keepSQL(Integer.parseInt(i), map.get("type3").toString(), map.get("sql3").toString(), operator);
					}
					if(l==3){
						y = reportService.keepSQL(Integer.parseInt(i), map.get("type4").toString(), map.get("sql4").toString(), operator);
					}
					if(l==4){
						y = reportService.keepSQL(Integer.parseInt(i), map.get("type5").toString(), map.get("sql5").toString(), operator);
					}

				}
				if(y != 0){
					msg = "模板创建成功！";
					return R.ok(msg);
				}
			}else{
				msg = "模板创建失败！";
				return R.error(0,msg);
			}
		}

		return R.ok();
	}

	/**
	 * 获取报表条件
	 * @param code
	 * @return
	 */
	@GetMapping("/reportCodeInclude")
	@ResponseBody
	@RequiresPermissions("sys:report:report")
	public ReportDO reportCodeInclude(@RequestParam("code") String code){
		//获取报表信息
		ReportDO report = reportService.getReportInfo(code);
		return report;
	}

	@GetMapping("/reportCode")
	@ResponseBody
	@RequiresPermissions("sys:report:report")
	public void reportCode(@RequestParam Map<String ,Object> map, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException, SQLException, ClassNotFoundException {
        HashMap<String, Object> objectHashMap = new HashMap<>();
        for(String key:map.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
            String value = map.get(key).toString();//
            objectHashMap.put(key,value);
        }
        if(ShiroUtils.getUser().getType().equals("2")){
            objectHashMap.put("regionName",FieldDictUtil.get(getAppName(), "reg_region_nzxy", "id", ShiroUtils.getUser().getWorkerid()));
            map.put("regionid",ShiroUtils.getUser().getWorkerid());
        }
        if(ShiroUtils.getUser().getType().equals("3")){
            objectHashMap.put("schoolName",FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", ShiroUtils.getUser().getWorkerid()));
            objectHashMap.put("schoolCode",FieldDictUtil.get(getAppName(), "sch_school_nzxy", "code", ShiroUtils.getUser().getWorkerid()));
            map.put("schoolid",ShiroUtils.getUser().getWorkerid());
        }
        if(ShiroUtils.getUser().getType().equals("4")){
            objectHashMap.put("collegeName",FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", ShiroUtils.getUser().getWorkerid()));
            map.put("collegeid",ShiroUtils.getUser().getWorkerid());
        }

	    //获取报表信息
		ReportDO report = reportService.getReportInfo(map.get("code").toString());
		//获取所要执行的sql
		String id = report.getId();
		List<ReportSqlDO> list = reportService.getReportSql(Integer.parseInt(id));
		List<Map<String, Object>> list0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list4 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list5 = new ArrayList<Map<String, Object>>();
		//将对应的sql存储到map中   type:sql

		for (int i = 0; i < list.size(); i++) {
			//执行sql的返回值放到map中
			if (i == 0) {
				map.put("value", list.get(0).getSql().toString());
				list1 = reportService.runSql(map);
			}
			if (i == 1) {
				map.put("value", list.get(1).getSql().toString());
				list2 = reportService.runSql(map);
			}
			if (i == 2) {
				map.put("value", list.get(2).getSql().toString());
				list3 = reportService.runSql(map);
			}
			if (i == 3) {
				map.put("value", list.get(3).getSql().toString());
				list4 = reportService.runSql(map);
			}
			if (i == 4) {
				map.put("value", list.get(4).getSql().toString());
				list5 = reportService.runSql(map);
			}
		}
		if (list1.size() != 0) {
			list0.addAll(list1);
		}
		if (list2.size() != 0) {
			list0.addAll(list2);
		}
		if (list3.size() != 0) {
			list0.addAll(list3);
		}
		if (list4.size() != 0) {
			list0.addAll(list4);
		}
		if (list5.size() != 0) {
			list0.addAll(list5);
		}

		JRDataSource jrDataSource = new JRBeanCollectionDataSource(list0);


		//此处demo直接使用的磁盘绝对路径了,实际生产老实取文件路径
		File reporeFile = new File(report.getPath());
		JasperHelper.showPdf(report.getName(), reporeFile.getPath(), request, response, objectHashMap, jrDataSource);
	}
    /*
     * @param meunType 菜单类型,示例：C1,S2,R3
     * @return
     */
    @GetMapping("/menuSelected")
    @ResponseBody
    @RequiresPermissions("sys:report:report")
    public List<ReportDO> menuSelected(@RequestParam("meunType") String meunType){
        List<ReportDO> report = reportService.menuSelected(meunType);
        return report;
    }
    @ResponseBody
    @GetMapping("/listKcszyxf")
    public PageUtils listKcszyxf(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SpecialityRecordDO> listKcszyxf = reportService.listKcszyxf(query);
        for (SpecialityRecordDO item : listKcszyxf) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = reportService.countKcszyxf(query);
        PageUtils pageUtils = new PageUtils(listKcszyxf, total);
        return pageUtils;
    }
    @GetMapping("/kcszyxf")
    String Kcszyxf(Model model){

        return "center/sys/report/kcszyxf";
    }
}
