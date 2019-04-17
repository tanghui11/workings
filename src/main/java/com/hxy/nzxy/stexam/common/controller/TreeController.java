package com.hxy.nzxy.stexam.common.controller;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityRecordDao;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.TreeService;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 属性管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-19 09:39:15
 */


@Controller
@RequestMapping("/common/tree")
public class TreeController extends SystemBaseController {
    @Autowired
    private OrgService orgService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private DeptWorkerService deptWorkerService;

    @Autowired
    private UserService userService;
    @Autowired
    private TreeService treeService;
    @Autowired
    private SpecialityRecordDao specialityRecordDao;
    @GetMapping("/treeView/{orgid}")
    public String treeView(Model model, @PathVariable("orgid") String orgid) {
        model.addAttribute("orgid", orgid);
        return "/system/org/treeView";
    }

    @GetMapping("/orgTree")
    @ResponseBody
    public List<TreeEx<OrgDO>> orgTree(@RequestParam Map<String, Object> map) {
        return orgService.getTree(map);
    }

    @GetMapping("/orgTreeAll")
    @ResponseBody
    public Tree<OrgDO> orgTreeAll(@RequestParam Map<String, Object> map) {
        return orgService.getTreeAll(map);
    }

    @GetMapping("/collegeTree")
    @ResponseBody
    public List<TreeEx<SchoolDO>> collegeTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        map.put("schoolid", ShiroUtils.getUser().getWorkerid());
        return treeService.collegelTree(map);
    }

    @GetMapping("/schoolTree")
    @ResponseBody
    public List<TreeEx<SchoolDO>> schoolTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        return treeService.schoolTree(map);
    }

    @GetMapping("/regionTree")
    @ResponseBody
    public List<TreeEx<RegionDO>> regionTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        return treeService.regionTree(map);
    }

    /**
     * 树，助学组织
     * @param map
     * @param request
     * @return
     */
    @GetMapping("/onlyRegionTree")
    @ResponseBody
    public List<TreeEx<RegionDO>> onlyRegionTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        return treeService.onlyRegionTree(map);
    }

    @GetMapping("/schoolCollegeTree")
    @ResponseBody
    public List<TreeEx<SchoolDO>> schoolCollegeTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        if (map.size() > 0) {
            if (map.get("type").equals("school")) {
                List<TreeEx<SchoolDO>> treeExes = treeService.collegelTree(map);
                for (TreeEx<SchoolDO> item : treeExes) {
                    item.setId("c" + item.getId());
                }
                return treeExes;
            }
        }
        List<TreeEx<SchoolDO>> treeExes = treeService.schoolCollegeTree(map);
        for (TreeEx<SchoolDO> item : treeExes) {
            item.setId("s" + item.getId());
        }
        return treeExes;
    }

    @GetMapping("/regionRegionTree")
    @ResponseBody
    public List<TreeEx<RegionDO>> regionRegionTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        return treeService.regionRegionTree(map);
    }

    @GetMapping("/schoolCollegeTeachSiteTree")
    @ResponseBody
    public List<TreeEx<SchoolDO>> schoolCollegeTeachSiteTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        if (map.size() > 0) {
            if (map.get("type").equals("school")) {
                List<TreeEx<SchoolDO>> treeExes = treeService.collegelsTree(map);
                for (TreeEx<SchoolDO> item : treeExes) {
                    item.setId("c" + item.getId());
                }
                return treeExes;
            }
            if (map.get("type").equals("college")) {
                List<TreeEx<SchoolDO>> treeExes = treeService.teachSiteTree(map);
                for (TreeEx<SchoolDO> item : treeExes) {
                    item.setId("t" + item.getId());
                }

                return treeExes;
            }
        }

        List<TreeEx<SchoolDO>> treeExes = treeService.schoolCollegeTree(map);
        for (TreeEx<SchoolDO> item : treeExes) {
            item.setId("s" + item.getId());
        }
        return treeExes;
    }



    @GetMapping("/specialityRecordTree")
    @ResponseBody
    public List specialityRecordTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
            if ("school".equals(map.get("type"))) {
                if (map.get("type").equals("school")) {
                    List<TreeEx<SpecialityRecordDO>> treeExes = treeService.specialityTree1(map.get("schoolid").toString());
                    for (TreeEx<SpecialityRecordDO> item : treeExes) {
                        item.setId(item.getId());
                    }
                    return treeExes;
                }
        }

        List<TreeEx<SpecialityRecordDO>> treeExes = treeService.specialityTree();
        for (TreeEx<SpecialityRecordDO> item : treeExes) {
            item.setId(item.getId());
        }
        return treeExes;

    }
    //考场编排-考区树
    @GetMapping("/arrangeTree")
    @ResponseBody
    public List<SchoolDO> arrangeTree(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        return treeService.arrangeTree(map);
    }

}