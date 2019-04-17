package com.hxy.nzxy.stexam.common.service;

import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.domain.CollegeDO;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.system.domain.OrgDO;

import java.util.List;
import java.util.Map;

public interface TreeService {

    List<TreeEx<SchoolDO>> collegelTree(Map<String,Object> map);

    List<TreeEx<SchoolDO>> schoolTree(Map<String,Object> map);

    List<TreeEx<RegionDO>> regionTree(Map<String,Object> map);

    List<TreeEx<RegionDO>> onlyRegionTree(Map<String,Object> map);

    List<TreeEx<SchoolDO>> schoolCollegeTree(Map<String,Object> map);

    List<TreeEx<RegionDO>> regionRegionTree(Map<String,Object> map);

    List<TreeEx<SchoolDO>> schoolCollegeTeachSiteTree(Map<String,Object> map);

    List<TreeEx<SchoolDO>> teachSiteTree(Map<String,Object> map);

    List<TreeEx<SchoolDO>> collegelsTree(Map<String,Object> map);

    List<TreeEx<SpecialityRecordDO>> specialityTree();

    List<TreeEx<SpecialityRecordDO>> specialityTree1(String schoolid);

    List<SchoolDO> arrangeTree(Map<String,Object> map);
}
