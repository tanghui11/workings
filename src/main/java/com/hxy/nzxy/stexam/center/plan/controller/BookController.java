package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.BookService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.BookDO;
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
 * 教材管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-07-30 10:05:50
 */
 
@Controller
@RequestMapping("/plan/book")
public class BookController extends SystemBaseController {
	@Autowired
	private BookService bookService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("plan:book:book")
	String Book(){
	    return "center/plan/book/book";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:book:book")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BookDO> bookList = bookService.list(query);
		for (BookDO item : bookList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_book", "type", item.getType()));
			item.setCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid())+"("+item.getCourseid()+")");
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_book", "classify", item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_book", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = bookService.count(query);
		PageUtils pageUtils = new PageUtils(bookList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:book:add")
	String add(Model model){
		model.addAttribute("pla_book_type", commonService.listFieldDict(getAppName(), "pla_book", "type"));
		model.addAttribute("pla_book_classify", commonService.listFieldDict(getAppName(), "pla_book", "classify"));
		model.addAttribute("pla_book_status", commonService.listFieldDict(getAppName(), "pla_book", "status"));

		return "center/plan/book/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:book:edit")
	String edit(@PathVariable("id") Long id,Model model){
		BookDO book = bookService.get(id);
		model.addAttribute("pla_book_type", commonService.listFieldDict(getAppName(), "pla_book", "type"));
		model.addAttribute("pla_book_classify", commonService.listFieldDict(getAppName(), "pla_book", "classify"));
		model.addAttribute("pla_book_status", commonService.listFieldDict(getAppName(), "pla_book", "status"));
		model.addAttribute("book", book);
	    return "center/plan/book/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:book:add")
	public R save( BookDO book){
		book.setOperator(ShiroUtils.getUserId().toString());

		if(bookService.save(book)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_book_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(book.getId()+"");
			fieldDict.setFieldMean(book.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.saveCache(fieldDict)>0) {
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
	@RequiresPermissions("plan:book:edit")
	public R update( BookDO book){
		book.setOperator(ShiroUtils.getUserId().toString());
		if(bookService.update(book)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_book_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(book.getId()+"");
			fieldDict.setFieldMean(book.getName());
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
	@RequiresPermissions("plan:book:remove")
	public R remove( Long id){
		if(bookService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_book_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id+"");
			fieldDict.setSeq(0);
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
	@RequiresPermissions("plan:book:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		if(bookService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("pla_book_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setSeq(0);
			if(fieldDictService.batchremoveCache(fieldDict,CommonUtil.longToString(ids))>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}

	/**
	 * 根据课程ID查询相应教材List
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/seachBookList")

	public List seachBookList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<BookDO> bookList = bookService.seachBookList(params);
		for (BookDO item : bookList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_book", "type", item.getType()));
			item.setCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid())+"("+item.getCourseid()+")");
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_book", "classify", item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_book", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return bookList;
	}



	//弹出导入页面
	@GetMapping("/importData")
	String importData( ){
		return "center/plan/book/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("plan:book:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/book/importData";
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
			return "center/plan/book/importData";
		}

		//返回错误信息
		String message = bookService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/plan/book/importData";
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
		List<BookDO> bookList = bookService.list(params);
		request.getSession().setAttribute("totalCount", bookList.size());
		for (BookDO item : bookList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_book", "type", item.getType()));
			item.setCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid())+"("+item.getCourseid()+")");
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_book", "classify", item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_book", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (bookList != null && bookList.size() > 0) {
			String[][] result = new String[bookList.size() + 1][15];
			result[0] = new String[] { "序号","教材类别", "课程名称","教材名称", "拼音",  "主编","出版社","版次","单价","教材类型","备注","状态","操作员","操作日期"};
			if (bookList != null && bookList.size() > 0) {
				for (int i = 0; i < bookList.size(); i++) {
					BookDO bookDO = bookList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(bookDO.getClassify());
					result[i + 1][2] = String.valueOf(bookDO.getCourseid());
					result[i + 1][3] = String.valueOf(bookDO.getName());
					result[i + 1][4] = String.valueOf(bookDO.getPinyin());
					result[i + 1][5] = String.valueOf(bookDO.getChiefEditor());
					result[i + 1][6] = String.valueOf(bookDO.getPublisher());
					result[i + 1][7] = String.valueOf(bookDO.getVersion());
					result[i + 1][8] = String.valueOf(bookDO.getPrice());
					result[i + 1][9] = String.valueOf(bookDO.getType());
					result[i + 1][10] = String.valueOf(bookDO.getRemark());
					result[i + 1][11] = String.valueOf(bookDO.getStatus());
					result[i + 1][12] = String.valueOf(bookDO.getOperator());
					result[i + 1][13] = String.valueOf(bookDO.getUpdateDate());
//进度条写入进度
					double dPercent=(double)(i)/bookList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%


				}
			}
			String tempFileName="教材管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="教材管理导出信息" +date+".zip";
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
			return "center/plan/book/book";
		}
//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", bookList.size());

		return null;
	}


}
