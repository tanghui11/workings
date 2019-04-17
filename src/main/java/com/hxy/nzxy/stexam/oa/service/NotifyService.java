package com.hxy.nzxy.stexam.oa.service;

import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.oa.domain.NotifyDO;

import java.util.List;
import java.util.Map;

/**
 * 通知通告
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */
public interface NotifyService {

	NotifyDO get(String id);

	List<NotifyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(NotifyDO notify);

	int update(NotifyDO notify);

	int remove(String id);

	int batchRemove(String[] ids);

	List<NotifyDO> listNotifyView(Map<String, Object> map);

	int notifyViewCount(Map<String, Object> map);

//	Map<String, Object> message(Long userId);

/*	PageUtils selfList(Map<String, Object> map);*/
}
