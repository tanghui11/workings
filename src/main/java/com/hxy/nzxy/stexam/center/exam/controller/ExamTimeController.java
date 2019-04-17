package com.hxy.nzxy.stexam.center.exam.controller;

import com.hxy.nzxy.stexam.center.exam.service.ExamTimeService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamTimeDO;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考试时间
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
 
@Controller
@RequestMapping("/exam/examTime")
public class ExamTimeController extends SystemBaseController{
	@Autowired
	private ExamTimeService examTimeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examTime:examTime")
	String ExamTime(){
	    return "center/exam/examTime/examTime";
	}
	@GetMapping("/examTimeOpen")
	String examTimeOpen(){
		return "center/exam/examTime/examTimeOpen";
	}
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamTimeDO> examTimeList = examTimeService.list(query);
        for (ExamTimeDO item : examTimeList) {
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
           // item.setExamDate(DateUtils.stringToDate(DateUtils.format(item.getExamDate(), DateUtils.DATE_PATTERN),"yyyy-MM-dd"));
        }
		int total = examTimeService.count(query);
		PageUtils pageUtils = new PageUtils(examTimeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:examTime:add")
	String add(Model model){
		model.addAttribute("exa_exam_time_segment", commonService.listFieldDict(getAppName(), "exa_exam_time", "segment"));

		return "center/exam/examTime/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examTime:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamTimeDO examTime = examTimeService.get(id);
		model.addAttribute("examTime", examTime);
		model.addAttribute("exa_exam_time_segment", commonService.listFieldDict(getAppName(), "exa_exam_time", "segment"));

		return "center/exam/examTime/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examTime:add")
	public R save( ExamTimeDO examTime){
        examTime.setOperator(ShiroUtils.getUserId().toString());
		if(examTimeService.save(examTime)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:examTime:edit")
	public R update( ExamTimeDO examTime){
	    examTime.setOperator(ShiroUtils.getUserId().toString());
		examTimeService.update(examTime);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:examTime:remove")
	public R remove( Long id){
		if(examTimeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:examTime:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examTimeService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 根据考试任务ＩＤ查询所有的考时间
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/seachExamTimeAllList")

	public List seachExamTimeAllList(@RequestParam Map<String, Object> params){

		List<ExamTimeDO> examTimeList = examTimeService.seachExamTimeAllList(params);
		for (ExamTimeDO item : examTimeList) {
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
		}
		return examTimeList;
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
		List<ExamTimeDO> examTimeList = examTimeService.list(params);
		request.getSession().setAttribute("totalCount", examTimeList.size());
		for (ExamTimeDO item : examTimeList) {
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (examTimeList != null && examTimeList.size() > 0) {
			String[][] result = new String[examTimeList.size() + 1][8];
			result[0] = new String[] { "序号","开考日期", "时段","开考时间","结束时间", "操作员",  "操作日期"};
			if (examTimeList != null && examTimeList.size() > 0) {
				for (int i = 0; i < examTimeList.size(); i++) {
					ExamTimeDO examTimeDO = examTimeList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = CommonUtil.dateToString(examTimeDO.getExamDate(),"yyyy-MM-dd");
					result[i + 1][2] = String.valueOf(examTimeDO.getSegment());
					result[i + 1][3] = String.valueOf(examTimeDO.getBeginTime());
					result[i + 1][4] = String.valueOf(examTimeDO.getEndTime());
					result[i + 1][5] = String.valueOf(examTimeDO.getOperator());
					result[i + 1][6] = String.valueOf(examTimeDO.getUpdateDate());

					//进度条写入进度
					double dPercent=(double)(i)/examTimeList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="考试时间设置导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="考试时间设置导出信息" +date+".zip";
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
			model.addAttribute("exa_exam_time_segment", commonService.listFieldDict(getAppName(), "exa_exam_time", "segment"));
			return "center/exam/examTime/examTime";
		}

		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", examTimeList.size());

		return null;
	}

	/**
	 * 批量导入
	 */

    //弹出导入页面
    @GetMapping("/importData")
    String importData(String examTaskid,Model model){
    	model.addAttribute("examTaskid",examTaskid);
        return "center/exam/examTime/importData";
    }

     // 批量导入
    @PostMapping("/savBathData")
    @RequiresPermissions("exam:examTime:add")
    public String savBathData(@RequestParam(value="filename") MultipartFile file,
							String examTaskid, HttpServletRequest request, HttpServletResponse response, HttpSession session
    ) throws IOException {
    	System.out.println(examTaskid);
        //判断文件是否为空
        if(file==null){
            request.setAttribute("msg","文件不能为空！");
            return "center/exam/examTime/importData";
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
            return "center/exam/examTime/importData";
        }

        //返回信息
        String message = examTimeService.batchImport(fileName,file ,examTaskid);
        request.setAttribute("msg",message);
        return "center/exam/examTime/importData";
    }
}