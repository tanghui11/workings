package com.hxy.nzxy.stexam.center.student.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.config.FtpConfig;
import org.apache.commons.fileupload.disk.DiskFileItem;
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

import com.hxy.nzxy.stexam.domain.StudentTempDO;
import com.hxy.nzxy.stexam.center.student.service.StudentTempService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.hxy.nzxy.stexam.common.utils.FileUtil.deleteDir;

/**
 * 考生基本信息临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:26
 */
 
@Controller
@RequestMapping("/student/studentTemp")
public class StudentTempController extends SystemBaseController {
	@Autowired
	private StudentTempService studentTempService;
	@Autowired
	private CommonService commonService;
	@Autowired
	FtpConfig ftpConfig;
	@Autowired
	private ZipUtils zipUtils;

	@GetMapping()
	@RequiresPermissions("student:studentTemp:studentTemp")
	String StudentTemp(Model model) {
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		return "center/student/studentTemp/studentTemp";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentTemp:studentTemp")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<StudentTempDO> studentTempList = studentTempService.list(query);
		for (StudentTempDO item : studentTempList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = studentTempService.count(query);
		PageUtils pageUtils = new PageUtils(studentTempList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("student:studentTemp:add")
	String add(Model model) {
		return "center/student/studentTemp/add";
	}

	@GetMapping("/edit/{ksZkz}")
	@RequiresPermissions("student:studentTemp:edit")
	String edit(@PathVariable("ksZkz") String ksZkz, Model model) {
		StudentTempDO studentTemp = studentTempService.get(ksZkz);
		model.addAttribute("studentTemp", studentTemp);
		return "center/student/studentTemp/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentTemp:add")
	public R save(StudentTempDO studentTemp) {
		studentTemp.setOperator(ShiroUtils.getUserId().toString());
		if (studentTempService.save(studentTemp) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentTemp:edit")
	public R update(StudentTempDO studentTemp) {
		studentTemp.setOperator(ShiroUtils.getUserId().toString());
		studentTempService.update(studentTemp);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("student:studentTemp:remove")
	public R remove(String type) {
		if (studentTempService.remove(type) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentTemp:batchRemove")
	public R remove(@RequestParam("ids[]") String[] types) {
		studentTempService.batchRemove(types);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/moveOut")
	@ResponseBody
	@RequiresPermissions("student:studentTemp:remove")
	public R moveOut(String studentid) {
		if (studentTempService.moveOut(studentid) > 0) {
			return R.ok();
		}
		return R.error();
	}


	//导入页面
	@GetMapping("/leadingInPage")
	String leadingInPage() {

		return "center/student/studentTemp/studentLeadIn";
	}


	/**
	 * 批量导入
	 */
	@PostMapping("/ZSsavBathData")
	@RequiresPermissions("student:studentTemp:add")
	public String ZSsavBathData(@RequestParam(value = "filename") MultipartFile file, @RequestParam(value = "type") String type, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException, ParseException {
		//判断文件是否为空
		if (file == null || file.getSize() == 0) {
			request.setAttribute("msg", "文件不能为空！");
			return "center/student/studentTemp/studentLeadIn";
		}

		//获取文件名
		String fileName = file.getOriginalFilename();

		//验证文件名是否合格
		if (!ExcelImportUtils.validatedbf(fileName)) {
			session.setAttribute("msg", "文件必须是dbf格式！");
			model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "type", "type"));
			return "redirect:center/student/studentTemp/studentLeadIn";
		}

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (StringUtils.isEmpty(fileName) || size == 0) {
			request.setAttribute("msg", "文件不能为空！");
			return "center/student/studentTemp/studentLeadIn";
		}



		//批量导入
		String message = studentTempService.batchImport(fileName, file, type);
		request.setAttribute("msg", message);
		return "center/student/studentTemp/studentLeadIn";
	}

	//考生信息临时表导入到正式表弹出页
	@GetMapping("/leadingInStudent")
	String leadingInStudent() {

		return "center/student/studentTemp/leadInStudent";
	}

	/**
	 * 考生信息临时表导入到正式表
	 *
	 * @param params
	 * @param havePhoto true：必须有照片才可入库， false：全部入库，无论是否有照片
	 * @return
	 */
	@PostMapping("/studentRegions")
	@RequiresPermissions("student:studentTemp:add")
	public String inputStudentRegion(HttpServletRequest request, @RequestParam Map<String, Object> params, boolean havePhoto, @RequestParam(value = "filename") MultipartFile multipartFiles) throws IOException {
		String message = "";
		List<StudentTempDO> studentList = studentTempService.list(params);
		//下面是解压压缩文件multipartFile
		String paths = zipUtils.unzipfile(multipartFiles).get("path").toString();

		File file = new File(paths);
		File[] files = file.listFiles();
		String yearNumber = "";
		String regionCode = "";
		String path[] = new String[2];
		String path2 = "";
		request.setCharacterEncoding("UTF-8");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		/** 页面控件的文件流* */
		MultipartFile multipartFile = null;
		Map map = multipartRequest.getFileMap();
		for (Iterator ii = map.keySet().iterator(); ii.hasNext(); ) {
			Object obj = ii.next();
			multipartFile = (MultipartFile) map.get(obj);
		}

		InputStream inputStream;
				inputStream = multipartFile.getInputStream();
				for (int ii = 0; ii < files.length; ii++) {
					File filek = files[ii];
					if (filek.isFile()) {
						for (StudentTempDO item : studentList) {
							if (item.getKsZkz() == filek.getName().substring(0, 12) || item.getKsZkz().equals(filek.getName().substring(0, 12))) {
								yearNumber = item.getKsZkz().substring(4, 7);
								regionCode = item.getKsZkz().substring(0, 4);
								path = FtpUtil.pictureUploadByConfig(ftpConfig, item.getKsZkz() + ".jpg", yearNumber + "/" + regionCode, inputStream);
								path2 = path[0];
								item.setPicture(String.valueOf(path2));
							}
						}
			} else {
				File filess[] = filek.listFiles();
				for (int kk = 0; kk < filess.length; kk++) {
					File filel = filess[kk];
					if (filel.isFile()) {
						for (StudentTempDO item : studentList) {
							if (item.getKsZkz() == filel.getName().substring(0, 12) || item.getKsZkz().equals(filel.getName().substring(0, 12))) {
								yearNumber = item.getKsZkz().substring(4, 7);
								regionCode = item.getKsZkz().substring(0, 4);
								path = FtpUtil.pictureUploadByConfig(ftpConfig, item.getKsZkz() + ".jpg", yearNumber + "/" + regionCode, inputStream);
								path2 = path[0];
								item.setPicture(path2);
							}
						}
					}
				}
			}
		}
				/*照片库中有的考生才能导入到正式表*/
				if (havePhoto == true) {
					for (StudentTempDO lists : studentList) {
						if (lists.getPicture() != null && lists.getPicture() != "") {
							if(studentTempService.isNotExist(lists.getKsZkz())>0){
								studentTempService.ZSsave(lists);
								studentTempService.delete(lists.getKsZkz());
							}else{
								message += "准考证号为"+lists.getKsZkz()+"的信息已存在或者信息有误！";
							}


						}
					}
				}
				/*不论照片，所有考生信息都导入到正式表*/
				if (havePhoto == false) {
					for (StudentTempDO lists : studentList) {
						if(studentTempService.isNotExist(lists.getKsZkz())>0){
							studentTempService.ZSsave(lists);
							studentTempService.delete(lists.getKsZkz());
						}else{
							message += "准考证号为"+lists.getKsZkz()+"的信息已存在或者信息有误！";
						}
					}
				}
					if (file.isDirectory()) {
						String[] children = file.list();
						out:for (int iu=0; iu<children.length; iu++) {
							boolean success = deleteDir(new File(file, children[iu]));
							if (!success) {
								break out;
							}
						}
					}
					if ("".equals(message ) || message == null){
					message += "导入成功";
					}
                    request.setAttribute("msg",message);
				return "center/student/studentTemp/leadInStudent";
	}


}