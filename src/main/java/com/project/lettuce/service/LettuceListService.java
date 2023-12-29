package com.project.lettuce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LettuceListService {
	private final RedisUtils redisUtils;
	private final String key = "listKey";
	
	public LettuceListService(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}
	
	/**** (참고) 레디스의 경우에는 @Transactional을 사용해도, 하나라도 실패할 경우 작업했던 것이 취소되지 않음 ****/
	@Transactional
	public boolean lpush(String value) {
		return redisUtils.lpush(key, value);
    }
	
	
	@Transactional
	public boolean lpop() {
		return redisUtils.lpop(key); 
    }


	@Transactional
	public boolean rpush(String value) {
		return redisUtils.rpush(key, value);
    }
	
	
	@Transactional
	public boolean rpop() {
		return redisUtils.rpop(key);
    }


	@Transactional
	public List<Object> range(long start, long end) {
		return redisUtils.range(key, start, end);
    }

	
	@Transactional
	public List<Object> trim(long start, long end) {
		return redisUtils.trim(key, start, end);
    }
	
	
	@Transactional
	public long lindex(String value) {
		return redisUtils.lindex(key, value);
    }

	
	@Transactional
	public long lsize() {
		return redisUtils.lsize(key);
    }
	
	
}
