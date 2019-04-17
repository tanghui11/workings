package com.hxy.nzxy.stexam.common.service.impl;

import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisTemplate<String, ?> redisTemplate;
	
	@Override
	public boolean set(final String key, final String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
	    });
		return result;
	}

	public String get(final String key){
		String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
	    });
		return result;
	}

	@Override
	public boolean expire(final String key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public <T> boolean setList(String key, List<T> list) {
		String value = JSONUtils.beanToJson(list);
		return set(key,value);
	}

	@Override
	public <T> List<T> getList(String key,Class<T> clz) {
		String json = get(key);
		if(json!=null){
			List<T> list = JSONUtils.jsonToList(json, clz);
			return list;
		}
		return null;
	}

	@Override
	public long lpush(final String key, Object obj) {
		final String value = JSONUtils.beanToJson(obj);
		long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
	    });
		return result;
	}

	@Override
	public long rpush(final String key, Object obj) {
		final String value = JSONUtils.beanToJson(obj);
		long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
	    });
		return result;
	}

	@Override
	public String lpop(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res =  connection.lPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
	    });
		return result;
	}

	/**
	 * 按照键值匹配
	 * @param key
	 * @return
	 */
	public Set<String> getKeys(String key) {
		return redisTemplate.keys(key+"*");
	}
	/**
	 * 按照键值删除
	 */
	public boolean delete(final String key) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] jkey = serializer.serialize(key);
				if (connection.exists(jkey)) {
					connection.del(jkey);
					return true;
				} else {
					return false;
				}
			}
		});
		return result;
	}
}