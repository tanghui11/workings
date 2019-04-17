package com.hxy.nzxy.stexam.center.sys.dao;

import com.hxy.nzxy.stexam.domain.ReportDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.ReportSqlDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import org.apache.ibatis.annotations.*;

/**
 * 报表管理表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-12-11 17:56:42
 */
@Mapper
public interface ReportDao {

	ReportDO get(Integer id);
	
	List<ReportDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReportDO report);
	
	int update(ReportDO report);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int keepInDB(Map<String, Object> map);

	int isInDB(Map<String, Object> map);

	@Insert("insert into sys_report_sql(report_id, type, `sql`, operator, update_date)values(#{id},#{type},#{sql},#{operator},now())")
	int keepSQL(@Param("id") int id,@Param("type")String type,@Param("sql")String sql, @Param("operator")String operator);

	ReportDO getReportInfo(@Param("code") String code);

	List<ReportSqlDO> getReportSql(int id);

	List<Map<String,Object>> runSql(Map<String, Object> map);
	List<ReportDO> menuSelected(@Param("menuType") String menuType);

    List<SpecialityRecordDO> listKcszyxf(Map<String,Object> map);

	int countKcszyxf(Map<String,Object> map);
}
