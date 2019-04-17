package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.system.dao.DeptDao;
import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.DeptDao;
import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.system.service.DeptService;
import com.hxy.nzxy.stexam.common.utils.BuildTree;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public DeptDO get(String id) {
        return deptDao.get(id);
    }

    @Override
    public List<DeptDO> list(Map<String, Object> map) {
        return deptDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return deptDao.count(map);
    }

    @Override
    public int save(DeptDO dept) {
        return deptDao.save(dept);
    }

    @Override
    public int update(DeptDO dept) {
        return deptDao.update(dept);
    }

    @Override
    public int remove(Long id) {
        return deptDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return deptDao.batchRemove(ids);
    }

    @Override
    public List<DeptDO> listDeptForTree(Map<String, Object> map){
        return deptDao.listDeptForTree(map);
    }

    @Override
    public List<DeptDO> verifyChildrenByDeptid(Map<String, Object> map){
        return deptDao.verifyChildrenByDeptid(map);
    }

    @Override
    public List<TreeEx<DeptDO>> getTree(Map<String, Object> map) {
        List<TreeEx<DeptDO>> trees = new ArrayList<TreeEx<DeptDO>>();
        List<DeptDO> deptDOList = deptDao.listDeptForTree(map);
        for (DeptDO dept : deptDOList) {
            TreeEx<DeptDO> tree = new TreeEx<DeptDO>();
            tree.setId(dept.getId().toString());
            tree.setText(dept.getName());
            //检查该节点下是否有子节点
            Map<String,Object> mapChild = new HashMap<String,Object>();
            mapChild.put("parentid",dept.getId());
            if(deptDao.verifyChildrenByDeptid(mapChild).size() > 0){
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
    public boolean checkDeptHasWorker(String deptid) {
        // TODO Auto-generated method stub
        //查询部门以及此部门的下级部门
        int result = deptDao.getDeptWorkerNumber(deptid);
        return result == 0 ? true : false;
    }
}
