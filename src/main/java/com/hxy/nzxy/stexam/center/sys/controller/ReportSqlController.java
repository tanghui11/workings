package com.hxy.nzxy.stexam.center.sys.controller;

import java.util.List;
import java.util.Map;

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

import com.hxy.nzxy.stexam.domain.ReportSqlDO;
import com.hxy.nzxy.stexam.center.sys.service.ReportSqlService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 报表sql表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-12-11 17:56:42
 */
 
@Controller
@RequestMapping("/sys/reportSql")
public class ReportSqlController extends SystemBaseController{
	@Autowired
	private ReportSqlService reportSqlService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("sys:reportSql:reportSql")
	String ReportSql(){
	    return "center/sys/reportSql/reportSql";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:reportSql:reportSql")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ReportSqlDO> reportSqlList = reportSqlService.list(query);
        for (ReportSqlDO item : reportSqlList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = reportSqlService.count(query);
		PageUtils pageUtils = new PageUtils(reportSqlList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:reportSql:add")
	String add(Model model){
	    return "center/sys/reportSql/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:reportSql:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ReportSqlDO reportSql = reportSqlService.get(id);
		model.addAttribute("reportSql", reportSql);
	    return "center/sys/reportSql/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:reportSql:add")
	public R save( ReportSqlDO reportSql){
        reportSql.setOperator(ShiroUtils.getUserId().toString());
		if(reportSqlService.save(reportSql)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:reportSql:edit")
	public R update( ReportSqlDO reportSql){
	    reportSql.setOperator(ShiroUtils.getUserId().toString());
		reportSqlService.update(reportSql);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:reportSql:remove")
	public R remove( Integer id){
	    if(id==null){
            return R.error("请选择要进行删除的数据!");
        }
		if(reportSqlService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:reportSql:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
        if(ids==null){
            return R.error("请选择要进行删除的数据!");
        }

		reportSqlService.batchRemove(ids);
		return R.ok();
	}
	
}
