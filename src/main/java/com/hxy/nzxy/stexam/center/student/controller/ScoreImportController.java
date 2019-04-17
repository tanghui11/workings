package com.hxy.nzxy.stexam.center.student.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hxy.nzxy.stexam.domain.ScoreImportDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreImportService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 成绩导入临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/scoreImport")
public class ScoreImportController extends SystemBaseController{
	@Autowired
	private ScoreImportService scoreImportService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreImport:scoreImport")
	String ScoreImport(){
	    return "center/student/scoreImport/scoreImport";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreImport:scoreImport")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreImportDO> scoreImportList = scoreImportService.list(query);
        for (ScoreImportDO item : scoreImportList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreImportService.count(query);
		PageUtils pageUtils = new PageUtils(scoreImportList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreImport:add")
	String add(Model model){
	    return "center/student/scoreImport/add";
	}

	@GetMapping("/edit/{kemuid}")
	@RequiresPermissions("student:scoreImport:edit")
	String edit(@PathVariable("kemuid") String kemuid,Model model){
		ScoreImportDO scoreImport = scoreImportService.get(kemuid);
		model.addAttribute("scoreImport", scoreImport);
	    return "center/student/scoreImport/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreImport:add")
	public R save( ScoreImportDO scoreImport){
        scoreImport.setOperator(ShiroUtils.getUserId().toString());
		if(scoreImportService.save(scoreImport)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreImport:edit")
	public R update( ScoreImportDO scoreImport){
	    scoreImport.setOperator(ShiroUtils.getUserId().toString());
		scoreImportService.update(scoreImport);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreImport:remove")
	public R remove( String kemuid){
		if(scoreImportService.remove(kemuid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreImport:batchRemove")
	public R remove(@RequestParam("ids[]") String[] kemuids){
		scoreImportService.batchRemove(kemuids);
		return R.ok();
	}

	//导入页面
	@GetMapping("/leadingInPage")
	String leadingInPage() {

		return "center/student/scoreImport/scoreInput";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/SIsavBathData")
	@RequiresPermissions("student:scoreImport:add")
	public String SIsavBathData(@RequestParam(value = "filename") MultipartFile file,  HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ParseException {
		//判断文件是否为空
		if (file == null) {
			request.setAttribute("msg", "文件不能为空！");
			return "center/student/scoreImport/scoreInput";
		}

		//获取文件名
		String fileName = file.getOriginalFilename();

		//验证文件名是否合格
		if (!ExcelImportUtils.validatedbf(fileName)) {
			session.setAttribute("msg", "文件必须是dbf格式！");
			return "center/student/scoreImport/scoreInput";
		}

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (StringUtils.isEmpty(fileName) || size == 0) {
			request.setAttribute("msg", "文件不能为空！");
			return "center/student/scoreImport/scoreInput";
		}

		//批量导入
		String message = scoreImportService.batchImport(fileName, file);
		request.setAttribute("msg", message);
		return "center/student/scoreImport/scoreInput";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	@RequiresPermissions("student:scoreImport:add")
	@Transactional(rollbackFor = Exception.class)
	public int delete(){
		int i = scoreImportService.delete();
		if(i >= 1){
			i =1;
		}
		return i;
	}
	@ResponseBody
	@PostMapping("/scoreInDB")//stu_score
	@RequiresPermissions("student:scoreImport:add")
	@Transactional(rollbackFor = Exception.class)
	public R scoreInDB(String examTaskid){
		//查询符合条件的导入学生
		String msg="";
		int res = 0;
		int ik = 0;
		int ii = 0;
		List<ScoreImportDO> students = scoreImportService.listAll();// 查询所有的符合条件的学生
		ScoreImportDO information;
		for (int i =0; i<students.size();i++){
			res = scoreImportService.ifPutIn(students.get(i).getCode(), examTaskid, students.get(i).getKemuid());
			if(res<1){
				msg += "没有找到报考信息-->课程代码:"+students.get(i).getKemuid()+" 准考证号:"+students.get(i).getCode()+"  ";
				ik =0;
			}else{
				if(!"".equals(msg) || msg == null){
					ii = scoreImportService.putInFormation(students.get(i));
					if(ii <1){
						ik = 0;
					}else{
						scoreImportService.setEnabled(students.get(i).getKemuid(), students.get(i).getCode());
						ik = 1;
					}
				}
			}
		}

		if (ik == 1){
			msg += "成绩入库成功！";
			return  R.ok(msg);
		}else{
			msg += "成绩入库失败！";
			return  R.error(0,msg);
		}
	}
	@ResponseBody
	@PostMapping("/scoreInDBT")//stu_score
	@RequiresPermissions("student:scoreImport:add")
	@Transactional(rollbackFor = Exception.class)
	public R scoreInDBT(String examTaskid){
		//查询符合条件的导入学生
		String msg="";
		int res = 0;
		int ik = 0;
		int ii = 0;
		List<ScoreImportDO> students = scoreImportService.listAll();// 查询所有的符合条件的学生
		ScoreImportDO information;
		for (int i =0; i<students.size();i++){

				if("".equals(msg) || msg == null){
					ii = scoreImportService.putInFormation(students.get(i));
					if(ii <1){
						ik = 0;
					}else{
						scoreImportService.setEnabled(students.get(i).getKemuid(), students.get(i).getCode());
						ik = 1;
					}
				}
		}

		if (ik == 1){
			msg += "成绩入库成功！";
			return  R.ok(msg);
		}else{
			msg += "成绩入库失败！";
			return  R.error(0,msg);
		}
	}
}
