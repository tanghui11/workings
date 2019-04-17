package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.BookCopyService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.BookCopyDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 教材管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/bookCopy")
public class BookCopyController extends SystemBaseController{
	@Autowired
	private BookCopyService bookCopyService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:bookCopy:bookCopy")
	String BookCopy(){
	    return "center/plan/bookCopy/bookCopy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:bookCopy:bookCopy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BookCopyDO> bookCopyList = bookCopyService.list(query);
        for (BookCopyDO item : bookCopyList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = bookCopyService.count(query);
		PageUtils pageUtils = new PageUtils(bookCopyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:bookCopy:add")
	String add(Model model){
	    return "center/plan/bookCopy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:bookCopy:edit")
	String edit(@PathVariable("id") Long id,Model model){
		BookCopyDO bookCopy = bookCopyService.get(id);
		model.addAttribute("bookCopy", bookCopy);
	    return "center/plan/bookCopy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:bookCopy:add")
	public R save( BookCopyDO bookCopy){
        bookCopy.setOperator(ShiroUtils.getUserId().toString());
		if(bookCopyService.save(bookCopy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:bookCopy:edit")
	public R update( BookCopyDO bookCopy){
	    bookCopy.setOperator(ShiroUtils.getUserId().toString());
		bookCopyService.update(bookCopy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:bookCopy:remove")
	public R remove( Long id){
		if(bookCopyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:bookCopy:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		bookCopyService.batchRemove(ids);
		return R.ok();
	}

}
