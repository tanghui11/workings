package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.service.StudentOutService;
import com.hxy.nzxy.stexam.center.student.service.impl.StudentSpecialityServiceImpl;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentOutDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 考绩转出
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
 
@Controller
@RequestMapping("/region/studentOut")
public class StudentOutController extends SystemBaseController{
	@Autowired
	private StudentOutService studentOutService;
    @Autowired
    private CommonService commonService;
    private StudentSpecialityServiceImpl studentSpecialityService;

    @GetMapping()
	@RequiresPermissions("region:studentOut:studentOut")
	String StudentOut(Model model){

		model.addAttribute("reg_student_out_status", commonService.listFieldDict(getAppName(), "reg_student_out", "status"));

		return "center/region/studentOut/studentOut";
	}


    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("region:studentOut:studentOut")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<StudentOutDO> studentOutList = studentOutService.list(query);
        for (StudentOutDO item : studentOutList) {
			item.setProvince(FieldDictUtil.get(getAppName(),"stu_student_out","province",item.getProvince()));
			item.setCity(FieldDictUtil.get(getAppName(),"stu_student_out","city",item.getCity()));
			item.setCause(FieldDictUtil.get(getAppName(),"stu_student_out","cause",item.getCause()));
			item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_out","status",item.getStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = studentOutService.count(query);
        PageUtils pageUtils = new PageUtils(studentOutList, total);
        return pageUtils;
    }

	/**
	 *功能描述 考籍转出
	 * @author ypp
	 * @date 2018/11/26
	 * @param
	 * @return R
	 */
	@ResponseBody
	@PostMapping("/out")
	@RequiresPermissions("region:studentOut:remove")
	public R out(@RequestParam("id") Long id){
		studentOutService.out(id);
		return R.ok();
	}

}