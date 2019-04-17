package com.hxy.nzxy.stexam.system.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.ExportTableDO;
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

import com.hxy.nzxy.stexam.system.domain.RptSqlDO;
import com.hxy.nzxy.stexam.system.service.RptSqlService;

/**
 * 报表管理
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-21 09:35:51
 */
 
@Controller
@RequestMapping("/system/rptSql")
public class RptSqlController extends SystemBaseController{
	@Autowired
	private RptSqlService rptSqlService;
	
	@GetMapping()
	@RequiresPermissions("system:rptSql:rptSql")
	String RptSql(){
	    return "system/rptSql/rptSql";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:rptSql:rptSql")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据

        Query query = new Query(params);
		List<RptSqlDO> rptSqlList = rptSqlService.list(query);
		for (RptSqlDO item : rptSqlList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = rptSqlService.count(query);
		PageUtils pageUtils = new PageUtils(rptSqlList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:rptSql:add")
	String add(){
	    return "system/rptSql/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:rptSql:edit")
	String edit(@PathVariable("id") String id,Model model){
		RptSqlDO rptSql = rptSqlService.get(id);
		model.addAttribute("rptSql", rptSql);
	    return "system/rptSql/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:rptSql:add")
	public R save(RptSqlDO rptSql){

		rptSql.setId(getUUID());
		rptSql.setOperator(getUserId());
		if(rptSqlService.save(rptSql)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:rptSql:edit")
	public R update( RptSqlDO rptSql){
		rptSqlService.update(rptSql);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:rptSql:remove")
	public R remove( String id){
		if(rptSqlService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:rptSql:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		rptSqlService.batchRemove(ids);
		return R.ok();
	}
	
}
