package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.utils.BuildTree;
import com.hxy.nzxy.stexam.common.utils.OrgUtil;
import com.hxy.nzxy.stexam.system.dao.OrgDao;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.system.service.OrgService;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.utils.BuildTree;
import com.hxy.nzxy.stexam.common.utils.OrgUtil;
import com.hxy.nzxy.stexam.system.dao.OrgDao;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.system.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.OrgDao;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.system.service.OrgService;


@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgDao orgDao;
    /**
     * 是否启用缓存
     */
    @Value("${chache.org.enabled}")
    private String enabled;

    @Override
    public OrgDO get(String id) {
        return orgDao.get(id);
    }

    @Override
    public OrgDO getByCode(String code) {
        return orgDao.getByCode(code);
    }

    @Override
    public List<OrgDO> list(Map<String, Object> map) {
        return orgDao.list(map);
    }

    @Override
    public List<OrgDO> listOrgForTree(Map<String, Object> map){
        return orgDao.listOrgForTree(map);
    }

    @Override
    public List<OrgDO> verifyChildrenByOrgid(Map<String, Object> map){
        return orgDao.verifyChildrenByOrgid(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return orgDao.count(map);
    }

    @Override
    public int save(OrgDO org) {
        int ret = orgDao.save(org);
        if (enabled.equals("true")) {
            OrgUtil.setName(org.getId(),org.getName());
            OrgUtil.setCode(org.getId(),org.getCode());
        }
        return ret;
    }

    @Override
    public int update(OrgDO org) {
        if (enabled.equals("true")) {
            OrgUtil.setName(org.getId(),org.getName());
            OrgUtil.setCode(org.getId(),org.getCode());
        }
        return orgDao.update(org);
    }

    @Override
    public int remove(String id) {
        if (enabled.equals("true")) {
            OrgUtil.remove(id);
        }
        return orgDao.remove(id);
    }

    @Override
    public List<TreeEx<OrgDO>> getTree(Map<String, Object> map) {
        List<TreeEx<OrgDO>> trees = new ArrayList<>();
        List<OrgDO> orgDOList = orgDao.listOrgForTree(map);
        for (OrgDO org : orgDOList) {
            TreeEx<OrgDO> tree = new TreeEx<OrgDO>();
            tree.setId(org.getId().toString());
            tree.setText(org.getName() + "["+org.getCode()+"]");
            //设置节点属性
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("type",org.getType());
            tree.setAttributes(attributes);
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("parentid",org.getId());
            if(orgDao.verifyChildrenByOrgid(mapChild).size() > 0){
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
    public Tree<OrgDO> getTreeAll(Map<String, Object> map) {
        List<String> listIds = new ArrayList<>();
        List<String> listParentids = new ArrayList<>();
        listParentids.add(map.get("orgid").toString());
        List<Tree<OrgDO>> trees = new ArrayList<>();
        //添加根节点
        OrgDO root  = orgDao.get(map.get("orgid").toString());
        if(root == null){
            return null;
        }
        Tree<OrgDO> treeRoot = new Tree<OrgDO>();
        treeRoot.setId(root.getId().toString());
        treeRoot.setParentId(root.getParentid().toString());
        treeRoot.setText(root.getName() + "["+root.getCode()+"]");
        //根据parentid查询所有的id
        List<OrgDO> orgDOList = orgDao.listOrgsByParentid(listParentids.toArray(new String[]{}));
        while (orgDOList.size() > 0) {
            listParentids.clear();
            for (OrgDO org : orgDOList) {
                listIds.add(org.getId());
                listParentids.add(org.getId());
            }
            orgDOList = orgDao.listOrgsByParentid(listParentids.toArray(new String[]{}));
        }
        //根据id查询所有的机构
        orgDOList = orgDao.listOrgForTreeByIds(listIds.toArray(new String[]{}));
        for (OrgDO org : orgDOList) {
            if(map.containsKey("schoolType")){
                if(org.getType().equals("d") && !map.get("schoolType").equals(org.getSchoolType())){
                    continue;
                }
            }
            Tree<OrgDO> tree = new Tree<OrgDO>();
            tree.setId(org.getId().toString());
            tree.setParentId(org.getParentid().toString());
            tree.setText(org.getName() + "["+org.getCode()+"]");
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<OrgDO> t = BuildTree.build(trees,treeRoot);
        return t;
    }

}
