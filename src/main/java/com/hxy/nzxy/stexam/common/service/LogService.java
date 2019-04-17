package com.hxy.nzxy.stexam.common.service;

import java.util.List;

import com.hxy.nzxy.stexam.common.domain.PageDO;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.domain.PageDO;
import com.hxy.nzxy.stexam.common.utils.Query;
import org.springframework.stereotype.Service;

import com.hxy.nzxy.stexam.common.domain.LogDO;
import com.hxy.nzxy.stexam.common.domain.PageDO;
import com.hxy.nzxy.stexam.common.utils.Query;
@Service
public interface LogService {
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
