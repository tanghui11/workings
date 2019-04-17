package com.hxy.nzxy.stexam.common.service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.domain.ValueDo;
import com.hxy.nzxy.stexam.system.domain.*;

/**
 * 通用类
 */
public interface CommonService {

	/**
	 * 查询数据字典
	 * @param appid
	 * @param tableName
	 * @param fieldName
	 * @return
	 */
	List<FieldDictDO> listFieldDict(String appid,String tableName,String fieldName);

	/**
	 * 查询数据字典
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<FieldDictDO> listFieldDict(Map<String, Object> map);

	/**
	 * 查询缓存数据字典
	 * @return
	 * @throws Exception
	 */
	List<FieldDictDO> listAllFieldDictCache(Map<String, Object> map);
	/**
	 * 查询缓存数据字典
	 * @return
	 * @throws Exception
	 */
	List<FieldDictDO> listAllFieldDictCache1(Map<String, Object> map);
	/**
	 * 查询缓存数据字典
	 * @return
	 * @throws Exception
	 */
	List<FieldDictDO> listAllFieldDictCache2(Map<String, Object> map);
	/**
	 * 查询缓存数据字典
	 * @return
	 * @throws Exception
	 */
	List<FieldDictDO> listAllFieldDictCache3(Map<String, Object> map);
	/**
	 * 查询缓存数据字典
	 * @return
	 * @throws Exception
	 */
	List<FieldDictDO> listAllFieldDictCache4(Map<String, Object> map);
	/**
	 * 查询缓存数据字典
	 * @return
	 * @throws Exception
	 */
	List<FieldDictDO> listAllFieldDictCache5(Map<String, Object> map);
	/**
	 * 查询缓存数据字典
	 * @return
	 * @throws Exception
	 */
	List<FieldDictDO> listAllFieldDictCache6(Map<String, Object> map);

	/**
	 * 获取用户信息并缓存
	 * @return
	 * @throws Exception
	 */
	List<UserDO> listAllUserCache(Map<String, Object> map);

	/**
	 * 获取所用应用信息
	 * @return
	 * @throws Exception
	 */
	List<AppDO> listAllApp(Map<String, Object> map);

	/**
	 * 获取所有考试科目
	 * @return
	 * @throws Exception
	 */
	List<SubjectDO> listAllSubject(Map<String, Object> map);

	/**
	 * 获取教学大纲列表
	 * @return
	 * @throws Exception
	 */
	List<KnowledgeDO> listAllKnowledge(Map<String, Object> map);

	/**
	 * 根据关键词查询用户信息
	 * @return
	 * @throws Exception
	 */
	List<UserDO> listUserByKey(Map<String, Object> map);

	/**
	 * 获取所有的机构信息
	 * @return
	 * @throws Exception
	 */
	List<OrgDO> listAllOrg(Map<String, Object> map);

	/**
	 * 按照名称查询数据导出表定义
	 * @return
	 * @throws Exception
	 */
	ExportTableDO getExportTableByName(String name);

	/**
	 * 按照数据导出表编号查询导出字段
	 * @return
	 * @throws Exception
	 */
	List<ExportFieldDO> listExportFieldByExportTableid(String exportTableid);

	ValueDo getValueBykeyTable(Map<String, Object> map);
	/**
	 * 是否重复
	 * @return
	 * @throws Exception
	 */
	int searchIfExist(Map<String,Object> map);
	/**
	 * 通用审核
	 * @return
	 * @throws Exception
	 */

	void batchAuditStatus(String tableName, String filedName, String filedValue, String key, Long[] keyValue);


	int selectIDCardStudentid(Map<String,Object> map);
}
