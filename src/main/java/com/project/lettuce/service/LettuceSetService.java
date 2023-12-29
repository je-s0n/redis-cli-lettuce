package com.project.lettuce.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LettuceSetService {
	private final RedisUtils redisUtils;
	private final String key = "setKey";
	
	public LettuceSetService(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}
	
	/**** (참고) 레디스의 경우에는 @Transactional을 사용해도, 하나라도 실패할 경우 작업했던 것이 취소되지 않음 ****/
	@Transactional
	public boolean sadd(String value) {
		return redisUtils.sadd(key, value);
	}
	
	
	@Transactional
	public String spop() {
		return redisUtils.spop(key);
    }

	
	@Transactional
	public List<Object> spopCount(long count) {
		return redisUtils.spop(key, count);
    }
	

	@Transactional
	public boolean srem(String value) {
		return redisUtils.srem(key, value);
    }
	
	
	@Transactional
	public boolean sismember(String value) {
		return redisUtils.sismember(key, value);
    }
	
	
	@Transactional
	public Set<Object> smembers() {
		return redisUtils.smembers(key);
    }
	

	@Transactional
	public Set<Object> sinter(String diffKey) {
		return redisUtils.sinter(key, diffKey);
    }
}
