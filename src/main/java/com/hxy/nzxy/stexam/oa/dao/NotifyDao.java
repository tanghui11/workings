package com.hxy.nzxy.stexam.oa.dao;

import com.hxy.nzxy.stexam.oa.domain.NotifyDO;
import com.hxy.nzxy.stexam.oa.domain.NotifyDTO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 通知通告
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */
@Mapper
public interface NotifyDao {

	NotifyDO get(String id);

	List<NotifyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(NotifyDO notify);

	int update(NotifyDO notify);

	int remove(String id);

	int batchRemove(String[] ids);

	List<NotifyDO> listByIds(String[] ids);

	int countDTO(Map<String, Object> map);

	List<NotifyDTO> listDTO(Map<String, Object> map);

	List<NotifyDO> listNotifyView(Map<String, Object> map);

	int notifyViewCount(Map<String, Object> map);
}
