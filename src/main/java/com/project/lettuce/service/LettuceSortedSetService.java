package com.project.lettuce.service;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LettuceSortedSetService {
	private final RedisUtils redisUtils;
	private final String key = "sortedSetKey";
	
	public LettuceSortedSetService(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}
	
	/**** (참고) 레디스의 경우에는 @Transactional을 사용해도, 하나라도 실패할 경우 작업했던 것이 취소되지 않음 ****/
	@Transactional
	public boolean zadd(String value, double score) {
		return redisUtils.zadd(key, value, score);
    }
	

	@Transactional
	public boolean zrem(String value) {
		return redisUtils.zrem(key, value);
    }

	
	@Transactional
	public Set<Object> zrange(long start, long end) {
		return redisUtils.zrange(key, start, end);
    }

	
	@Transactional
	public Set<Object> zrevrange(long start, long end) {
		return redisUtils.zrevrange(key, start, end);
    }
	
	
	@Transactional
	public double zincrby(String value, double incScore) {
		return redisUtils.zincrby(key, value, incScore);
    }

	
	@Transactional
	public long zcard() {
		return redisUtils.zcard(key);
    }

	
	@Transactional
	public long zcount(double min, double max) {
		return redisUtils.zcount(key, min, max);
    }
	

	@Transactional
	public long zrank(String value) {
		return redisUtils.zrank(key, value);
    }

	
	@Transactional
	public long zrevrank(String value) {
		return redisUtils.zrevrank(key, value);
    }

	
	@Transactional
	public Double zscore(String value) {
		return redisUtils.zscore(key, value);
    }

	
	@Transactional
	public Set<Object> zrevrangebyscore(double min, double max) {
		return redisUtils.zrevrangebyscore(key, min, max);
    }

	
	@Transactional
	public Set<Object> zrevrangebyscore(double min, double max, long offset, long count) {
		return redisUtils.zrevrangebyscore(key, min, max, offset, count);
    }

	
	@Transactional
	public Set<TypedTuple<Object>> zrevrangewithscore(long start, long end) {
		return redisUtils.zrevrangewithscore(key, start, end);
    }
}
