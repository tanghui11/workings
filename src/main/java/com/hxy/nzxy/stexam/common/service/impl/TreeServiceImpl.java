package com.hxy.nzxy.stexam.common.service.impl;

import com.hxy.nzxy.stexam.common.dao.TreeDao;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.service.TreeService;
import com.hxy.nzxy.stexam.domain.CollegeDO;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.system.dao.OrgDao;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TreeDao treeDao;
 

    @Override
    public List<TreeEx<SchoolDO>> collegelTree(Map<String, Object> map) {
        List<TreeEx<SchoolDO>> trees = new ArrayList<>();
        List<SchoolDO> orgDOList = treeDao.listCollegeForTree(map);
        for (SchoolDO org : orgDOList) {
            TreeEx<SchoolDO> tree = new TreeEx<SchoolDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
          //  attributes.put("type",org.getType());
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("parentid",org.getId());
                tree.setChildren(false);
                tree.setIcon("jstree-file");
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<TreeEx<SchoolDO>> schoolTree(Map<String, Object> map) {
        List<TreeEx<SchoolDO>> trees = new ArrayList<>();
        List<SchoolDO> orgDOList = treeDao.listSchoolForTree(map);
        for (SchoolDO org : orgDOList) {
            TreeEx<SchoolDO> tree = new TreeEx<SchoolDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getCode()+" "+org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
            //  attributes.put("type",org.getType());
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("parentid",org.getId());
            tree.setChildren(false);
            tree.setIcon("jstree-file");
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<TreeEx<RegionDO>> regionTree(Map<String, Object> map) {
        List<TreeEx<RegionDO>> trees = new ArrayList<>();
        if(map.get("parentid").equals("-1")){
            TreeEx<RegionDO> tree1=new TreeEx<RegionDO>();
            tree1.setId("0");
            tree1.setText("考区列表");
            tree1.setChildren(true);
            tree1.setIcon("jstree-folder");
            trees.add(tree1);
        }
        List<RegionDO> orgDOList = treeDao.listRegionDOForTree(map);
        for (RegionDO org : orgDOList) {
            TreeEx<RegionDO> tree = new TreeEx<RegionDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getCode()+" "+org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
              attributes.put("type",org.getType());
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("parentid",org.getId());
            if(treeDao.verifyChildrenByRegionid(mapChild).size() > 0){
                tree.setChildren(true);
                tree.setIcon("jstree-folder");
            }else{
                tree.setChildren(false);
                tree.setIcon("jstree-file");
            }
            trees.add(tree);
        }
        return trees;
    }

 @Override
    public List<TreeEx<RegionDO>> onlyRegionTree(Map<String, Object> map) {
        List<TreeEx<RegionDO>> trees = new ArrayList<>();
        if(map.get("parentid").equals("-1")){
            TreeEx<RegionDO> tree1=new TreeEx<RegionDO>();
            tree1.setId("0");
            tree1.setText("考区列表");
            tree1.setChildren(true);
            tree1.setIcon("jstree-folder");
            trees.add(tree1);
        }
        List<RegionDO> orgDOList = treeDao.listRegionDOForTree(map);
        for (RegionDO org : orgDOList) {
            TreeEx<RegionDO> tree = new TreeEx<RegionDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getCode()+" "+org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
              attributes.put("type",org.getType());
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("parentid",org.getId());

                tree.setChildren(false);
                tree.setIcon("jstree-file");
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<TreeEx<SchoolDO>> schoolCollegeTree(Map<String, Object> map) {
        List<TreeEx<SchoolDO>> trees = new ArrayList<>();
        List<SchoolDO> orgDOList = treeDao.listSchoolForTree(map);
        for (SchoolDO org : orgDOList) {
            TreeEx<SchoolDO> tree = new TreeEx<SchoolDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getCode()+" "+org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
             attributes.put("type","school");
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("schoolid",org.getId());
            if(treeDao.ChildrenBySchoolid(mapChild).size() > 0){
                tree.setChildren(true);
                tree.setIcon("jstree-folder");
            }else{
                tree.setChildren(false);
                tree.setIcon("jstree-file");
            }
            trees.add(tree);
        }

        return trees;


    }

    @Override
    public List<TreeEx<RegionDO>> regionRegionTree(Map<String, Object> map) {
        List<TreeEx<RegionDO>> trees = new ArrayList<>();
        List<RegionDO> orgDOList = treeDao.listRegionDOForTree(map);
        for (RegionDO org : orgDOList) {
            TreeEx<RegionDO> tree = new TreeEx<RegionDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getCode()+" "+org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("type",org.getType());
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("parentid",org.getId());
            if(treeDao.verifyChildrenByRegionid(mapChild).size() > 0){
                tree.setChildren(true);
                tree.setIcon("jstree-folder");
            }else{
                tree.setChildren(false);
                tree.setIcon("jstree-file");
            }
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<TreeEx<SchoolDO>> schoolCollegeTeachSiteTree(Map<String, Object> map) {

        List<TreeEx<SchoolDO>> trees = new ArrayList<>();
        List<SchoolDO> orgDOList = treeDao.listSchoolCollegeTeachSiteTree(map);
        for (SchoolDO org : orgDOList) {
            TreeEx<SchoolDO> tree = new TreeEx<SchoolDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getCode()+" "+org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
            //  attributes.put("type",org.getType());
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("schoolid",org.getId());
            if(treeDao.ChildrenSchoolCollegeTeachSite(mapChild).size() > 0){
                tree.setChildren(true);
                tree.setIcon("jstree-folder");
            }else{
                tree.setChildren(false);
                tree.setIcon("jstree-file");
            }
        }
        return trees;
    }

    @Override
    public List<TreeEx<SchoolDO>> teachSiteTree(Map<String, Object> map) {
        List<TreeEx<SchoolDO>> trees = new ArrayList<>();
        List<SchoolDO> orgDOList = treeDao.listTeachSitForTree(map);
        for (SchoolDO org : orgDOList) {
            TreeEx<SchoolDO> tree = new TreeEx<SchoolDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
             attributes.put("type","teachSite");
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
                tree.setChildren(false);
                tree.setIcon("jstree-file");
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<TreeEx<SchoolDO>> collegelsTree(Map<String, Object> map) {
        List<TreeEx<SchoolDO>> trees = new ArrayList<>();
        List<SchoolDO> orgDOList = treeDao.listCollegeForTree(map);
        for (SchoolDO org : orgDOList) {
            TreeEx<SchoolDO> tree = new TreeEx<SchoolDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
             attributes.put("type","college");
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("collegeid",org.getId());
            if(treeDao.verifyChildrenTeachid(mapChild).size() > 0){
                tree.setChildren(true);
                tree.setIcon("jstree-folder");
            }else{
                tree.setChildren(false);
                tree.setIcon("jstree-file");
            }
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<TreeEx<SpecialityRecordDO>> specialityTree() {
        List<TreeEx<SpecialityRecordDO>> trees = new ArrayList<>();
        List<SpecialityRecordDO> orgDOList = treeDao.specialityTree();
        for (SpecialityRecordDO org : orgDOList) {
            TreeEx<SpecialityRecordDO> tree = new TreeEx<SpecialityRecordDO>();
            tree.setId(org.getSchoolid().toString());
            tree.setText(org.getSchoolName() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
             attributes.put("type","college");
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点

                tree.setChildren(true);
                tree.setIcon("jstree-folder");

            trees.add(tree);
        }
        return trees;
    }
    @Override
    public List<TreeEx<SpecialityRecordDO>> specialityTree1(String schoolid) {
        List<TreeEx<SpecialityRecordDO>> trees = new ArrayList<>();
        List<SpecialityRecordDO> orgDOList = treeDao.specialityTree1(schoolid);
        for (SpecialityRecordDO org : orgDOList) {
            TreeEx<SpecialityRecordDO> tree = new TreeEx<SpecialityRecordDO>();
            tree.setId(org.getSpecialityid().toString());
            tree.setText(org.getSpecialityid()+" "+org.getNewSpecialityName()+" "+org.getDirection() );
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
             attributes.put("type","childSchool");
             tree.setAttributes(attributes);
             tree.setChildren(false);
             tree.setIcon("jstree-file");

            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<SchoolDO> arrangeTree(Map<String, Object> map) {
        return treeDao.arrangeTree(map);
    }
}
