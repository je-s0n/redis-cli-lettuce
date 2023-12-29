package com.project.lettuce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LettuceStringService {
	private final RedisUtils redisUtils;
	private final String key = "stringKey";
	private final String expireKey = "stringExpireKey";
	
	public LettuceStringService(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}
	
	/**** (참고) 레디스의 경우에는 @Transactional을 사용해도, 하나라도 실패할 경우 작업했던 것이 취소되지 않음 ****/
	@Transactional
	public boolean setData(String text) {
		return redisUtils.setData(key, text);
    }
	
	
	@Transactional
	public boolean setDataExpire(String text, long expire) {
		return redisUtils.setData(expireKey, text, expire);
    }

	
	@Transactional
	public Object getData(String paramKey) {
		return redisUtils.getData(paramKey);
    }
	

	@Transactional
	public boolean delData(String paramKey) {
		return redisUtils.delData(paramKey);
    }
}
