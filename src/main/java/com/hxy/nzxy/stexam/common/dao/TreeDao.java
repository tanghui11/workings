package com.hxy.nzxy.stexam.common.dao;

import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.domain.CollegeDO;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import org.apache.ibatis.annotations.Param;

import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TreeDao {
    List<SchoolDO> listSchoolForTree(Map<String,Object> map);

    Collection<Object> verifyChildrenBySchoolid(Map<String,Object> mapChild);

    List<SchoolDO> listCollegeForTree(Map<String,Object> map);

    List<RegionDO> listRegionDOForTree(Map<String,Object> map);

    Collection<Object> verifyChildrenByRegionid(Map<String,Object> mapChild);

    Collection<Object> ChildrenBySchoolid(Map<String,Object> mapChild);

    List<SchoolDO> listSchoolCollegeTeachSiteTree(Map<String,Object> map);

    Collection<Object> ChildrenSchoolCollegeTeachSite(Map<String,Object> mapChild);

    List<SchoolDO> listTeachSitForTree(Map<String,Object> map);

    Collection<Object> verifyChildrenTeachid(Map<String,Object> mapChild);

    List<SpecialityRecordDO> specialityTree();

    List<SpecialityRecordDO> specialityTree1(@Param("schoolid") String schoolid);

    List<SchoolDO> arrangeTree(Map<String,Object> map);
}
