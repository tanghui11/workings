package com.hxy.nzxy.stexam.common.service;

import java.util.List;
import java.util.Set;

/**
 * redis服务
 * @author dragon
 * @desc redis service
 */
public interface RedisService {
	
	public boolean set(String key, String value);
	
	public String get(String key);
	
	public boolean expire(String key, long expire);
	
	public <T> boolean setList(String key, List<T> list);
	
	public <T> List<T> getList(String key, Class<T> clz);
	
	public long lpush(String key, Object obj);
	
	public long rpush(String key, Object obj);
	
	public String lpop(String key);

	public Set<String> getKeys(String key);

	public boolean delete(final String key);
}