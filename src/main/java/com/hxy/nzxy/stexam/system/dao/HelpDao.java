package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.HelpDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 帮助文件
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-13 16:44:38
 */
@Mapper
public interface HelpDao {

    HelpDO get(Map<String, Object> map);

    int save(HelpDO help);

    int update(HelpDO help);

    int remove(String menuid);
}
