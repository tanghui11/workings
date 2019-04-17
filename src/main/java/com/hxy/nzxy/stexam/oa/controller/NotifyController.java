package com.hxy.nzxy.stexam.oa.controller;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.oa.domain.NotifyDO;
import com.hxy.nzxy.stexam.oa.domain.NotifyOrgDO;
import com.hxy.nzxy.stexam.oa.domain.NotifyRecordDO;
import com.hxy.nzxy.stexam.oa.service.NotifyOrgService;
import com.hxy.nzxy.stexam.oa.service.NotifyRecordService;
import com.hxy.nzxy.stexam.oa.service.NotifyService;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.system.service.OrgService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 通知通告
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */

@Controller
@RequestMapping("/oa/notify")
public class NotifyController extends OaBaseController {
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private NotifyRecordService notifyRecordService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private NotifyOrgService notifyOrgService;
    @Autowired
    private OrgService orgService;

    @GetMapping()
    @RequiresPermissions("oa:notify:notify")
    String oaNotify(Model model) {
        model.addAttribute("orgid", getOrgId());
        return "oa/notify/notify";
    }

    @GetMapping("/notifyView")
    @RequiresPermissions("oa:notify:notifyView")
    public String notifyView() {
        return "oa/notify/notifyView";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("oa:notify:notify")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        params.put("orgid", getOrgId());
        Query query = new Query(params);
        List<NotifyDO> notifyList = notifyService.list(query);
        for (NotifyDO item : notifyList) {
            item.setStatus(FieldDictUtil.get(getAppName(), "oa_notify", "status", item.getStatus()));
            item.setIsTop(FieldDictUtil.get(getAppName(), "oa_notify", "is_top", item.getIsTop()));
            item.setType(FieldDictUtil.get(getAppName(), "oa_notify", "type", item.getType()));
            /*(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));*/
        }
        int total = notifyService.count(query);
        PageUtils pageUtils = new PageUtils(notifyList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/listNotifyView")
    @RequiresPermissions("oa:notify:notifyView")
    public PageUtils listNotifyView(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        params.put("orgid", getOrgId());
        params.put("status","b");
        Query query = new Query(params);
        List<NotifyDO> notifyList = notifyService.listNotifyView(query);
        for (NotifyDO item : notifyList) {
            item.setStatus(FieldDictUtil.get(getAppName(), "oa_notify", "status", item.getStatus()));
            item.setIsTop(FieldDictUtil.get(getAppName(), "oa_notify", "is_top", item.getIsTop()));
            item.setType(FieldDictUtil.get(getAppName(), "oa_notify", "type", item.getType()));
            /*(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));*/
        }
        int total = notifyService.notifyViewCount(query);
        PageUtils pageUtils = new PageUtils(notifyList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("oa:notify:add")
    String add(Model model) {
        model.addAttribute("orgid", getOrgId());
        model.addAttribute("oa_notify_type", commonService.listFieldDict(getAppName(), "oa_notify", "type"));
        model.addAttribute("oa_notify_isTop", commonService.listFieldDict(getAppName(), "oa_notify", "is_top"));
        model.addAttribute("oa_notify_status", commonService.listFieldDict(getAppName(), "oa_notify", "status"));
        return "oa/notify/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("oa:notify:edit")
    String edit(@PathVariable("id") String id, Model model) {
        NotifyDO notify = notifyService.get(id);
        model.addAttribute("orgid", getOrgId());
        model.addAttribute("oa_notify_type", commonService.listFieldDict(getAppName(), "oa_notify", "type"));
        model.addAttribute("oa_notify_isTop", commonService.listFieldDict(getAppName(), "oa_notify", "is_top"));
        model.addAttribute("oa_notify_status", commonService.listFieldDict(getAppName(), "oa_notify", "status"));
        model.addAttribute("notify", notify);
        return "oa/notify/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("oa:notify:add")
    public R save(NotifyDO notify) {
        //判断接收机构是否为空
        if (notify.getOrgIds() == null || notify.getOrgIds().length() == 0) {
            return R.error("接收机构不能为空");
        }
        notify.setId(getUUID());
        notify.setUpdateDate(new Date());
        notify.setOrgid(getOrgId());
        if (notify.getIsTop().equals("1")) {
            notify.setIsTop("a");
        } else {
            notify.setIsTop("b");
        }
        String[] orgIdsArray = notify.getOrgIds().split(",");
        if (notifyService.save(notify) > 0) {
            for (String orgid : orgIdsArray) {
                Long org = Long.parseLong(orgid);
                NotifyOrgDO notifyOrgDO = new NotifyOrgDO();
                notifyOrgDO.setId(getUUID());
                notifyOrgDO.setNotifyid(notify.getId());
                notifyOrgDO.setOrgid(org);
                notifyOrgDO.setUpdateDate(new Date());
                notifyOrgService.save(notifyOrgDO);
            }
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("oa:notify:edit")
    public R update(NotifyDO notify) {
        //判断接收机构是否为空
        if (notify.getOrgIds() == null || notify.getOrgIds().length() == 0) {
            return R.error("接收机构不能为空");
        }
        String[] orgIdsArray = notify.getOrgIds().split(",");
        if (notifyService.update(notify) > 0) {
            notifyOrgService.remove(notify.getId());
            for (String orgid : orgIdsArray) {
                Long org = Long.parseLong(orgid);
                NotifyOrgDO notifyOrgDO = new NotifyOrgDO();
                notifyOrgDO.setId(getUUID());
                notifyOrgDO.setNotifyid(notify.getId());
                notifyOrgDO.setOrgid(org);
                notifyOrgDO.setUpdateDate(new Date());
                notifyOrgService.save(notifyOrgDO);
            }
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("oa:notify:remove")
    public R remove(String id) {
        if (notifyService.remove(id) > 0) {
            if (notifyOrgService.remove(id) > 0) {
                return R.ok();
            }
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("oa:notify:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        if (notifyService.batchRemove(ids) > 0) {
            if (notifyOrgService.batchRemove(ids) > 0) {
                return R.ok();
            }
        }
        ;
        return R.error();
    }

    @GetMapping("/preview/{id}")
    String preview(@PathVariable("id") String id, Model model) {
        NotifyDO notify = notifyService.get(id);
        model.addAttribute("notify", notify);
        return "oa/notify/preview";
    }
	/*@ResponseBody
	@GetMapping("/message")
	PageUtils message() {
		Map<String, Object> params = new HashMap<>(16);
		params.put("offset", 0);
		params.put("", 3);
		Query query = new Query(params);
        query.put("userId", getUserId());
        query.put("isRead",Constant.OA_NOTIFY_READ_NO);
		return notifyService.selfList(query);
	}*/

    @GetMapping("/selfNotify")
    String selefNotify() {
        return "oa/notify/selfNotify";
    }

	/*@ResponseBody
	@GetMapping("/selfList")
	PageUtils selfList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		query.put("userId", getUserId());

		return notifyService.selfList(query);
	}*/

    @GetMapping("/read/{id}")
    @RequiresPermissions("oa:notify:edit")
    String read(@PathVariable("id") String id, Model model) {
        NotifyDO notify = notifyService.get(id);
        //更改阅读状态
        NotifyRecordDO notifyRecordDO = new NotifyRecordDO();
        //notifyRecordDO.setNotifyId(id);
        notifyRecordDO.setUserId(getUserId());
        notifyRecordDO.setReadDate(new Date());
        notifyRecordDO.setIsRead(Constant.OA_NOTIFY_READ_YES);
        notifyRecordService.changeRead(notifyRecordDO);
        model.addAttribute("notify", notify);
        return "oa/notify/read";
    }
    @GetMapping("/getTreeOrgIds")
    @ResponseBody
    public List<OrgDO> getTreeOrgIds(@RequestParam("id")String id){
        Map<String,Object> map=new HashedMap();
        map.put("notifyid",id);
        List<NotifyOrgDO> notifyOrgDOList=notifyOrgService.list(map);
        List<OrgDO> orgDOList=new ArrayList<>();
        for(NotifyOrgDO notifyOrgDO:notifyOrgDOList){
            OrgDO orgDO=orgService.get(notifyOrgDO.getOrgid().toString());
            orgDOList.add(orgDO);
        }
        return  orgDOList;
    }
}
