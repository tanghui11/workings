package com.hxy.nzxy.stexam.common.dao;

import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.domain.ValueDo;
import com.hxy.nzxy.stexam.system.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 公用Dao
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface CommonDao {

    List<FieldDictDO> listFieldDictByTableFieldName(Map<String, Object> map);

    List<FieldDictDO> listAllFieldDictCache(Map<String, Object> map);
    List<FieldDictDO> listAllFieldDictCache1(Map<String, Object> map);
    List<FieldDictDO> listAllFieldDictCache2(Map<String, Object> map);
    List<FieldDictDO> listAllFieldDictCache3(Map<String, Object> map);
    List<FieldDictDO> listAllFieldDictCache4(Map<String, Object> map);
    List<FieldDictDO> listAllFieldDictCache5(Map<String, Object> map);
    List<FieldDictDO> listAllFieldDictCache6(Map<String, Object> map);

    List<UserDO> listAllUserCache(Map<String, Object> map);

    List<AppDO> listAllApp(Map<String, Object> map);

    List<GradeDO> listAllGrade(Map<String, Object> map);

    List<SubjectDO> listAllSubject(Map<String, Object> map);

    List<KnowledgeDO> listAllKnowledge(Map<String, Object> map);

    List<UserDO> listUserByKey(Map<String, Object> map);

    List<OrgDO> listAllOrg(Map<String, Object> map);

    ExportTableDO getExportTableByName(String name);

    List<ExportFieldDO> listExportFieldByExportTableid(String exportTableid);

    ValueDo getValueBykeyTable(Map<String,Object> map);

    int searchIfExist(Map<String,Object> map);

    void batchAuditStatus(Map<String,Object> map);

    int selectIDCardStudentid(Map<String,Object> map);
}
