package com.project.lettuce.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LettuceHashService {
	private final RedisUtils redisUtils;
	private final String key = "hashKey";

	public LettuceHashService(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}
	
	/**** (참고) 레디스의 경우에는 @Transactional을 사용해도, 하나라도 실패할 경우 작업했던 것이 취소되지 않음 ****/
	@Transactional
	public Object hget(String hashKey) {
		return redisUtils.hget(key, hashKey);
    }
	
	
	@Transactional
	public List<Object> hgetAll() {
		return redisUtils.hgetAll(key);
    }
	

	@Transactional
	public void hset(String hashKey, String value) {
		redisUtils.hset(key, hashKey, value);
    }


	@Transactional
	public boolean hsetnx(String hashKey, String value) {
		return redisUtils.hsetnx(key, hashKey, value);
    }
	

	@Transactional
	public boolean hdel(String hashKey) {
		return redisUtils.hdel(key, hashKey);
    }

	
	@Transactional
	public boolean hexists(String hashKey) {
		return redisUtils.hexists(key, hashKey);
    }

	
	@Transactional
	public long hincrby(String hashKey, long incValue) {
		return redisUtils.hincrby(key, hashKey, incValue);
    }


	@Transactional
	public Set<Object> hkeys() {
		return redisUtils.hkeys(key);
    }

	
	@Transactional
	public long hlen() {
		return redisUtils.hlen(key);
    }


	
	@Transactional
	public long hstrlen(String hashKey) {
		return redisUtils.hstrlen(key, hashKey);
    }	
	
	
	@Transactional
	public String hrandfield() {
		return redisUtils.hrandfield(key);
    }
}
