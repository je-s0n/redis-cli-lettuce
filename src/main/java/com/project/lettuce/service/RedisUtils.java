package com.project.lettuce.service;

import java.security.KeyManagementException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

@Service
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    
    
    /**************** using opsForValue() ****************/
    
    /**
     * String 자료형의 데이터를 저장하는 method
     * 
     * @param key String
     * @param value String
     * 
     * @return boolean
     */
    public boolean setData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        if(getData(key).equals(value))
        	return true;
        else return false;
    }
    
    /**
     * String 자료형의 데이터 만료시간과 함께 저장하는 method
     * 
     * @param key String
     * @param value String
     * @param expiredTime Long (ms단위)
     * 
     * @return boolean
     */
    public boolean setData(String key, String value, Long expiredTime) {
        redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
        if(getData(key).equals(value))
        	return true;
        else return false;
    }

    /**
     * String 자료형의 데이터를 저장하는 method
     * 
     * @param key String
     * @param value long
     * 
     * @return boolean
     */
    public boolean setData(String key, long value) {
        redisTemplate.opsForValue().set(key, value);
        if(getData(key).equals(value))
        	return true;
        else return false;
    }
    
    /**
     * String 자료형의 데이터 만료시간과 함께 저장하는 method
     * 
     * @param key String
     * @param value long
     * @param expiredTime Long (ms단위)
     * 
     * @return booleans
     */
    public boolean setData(String key, long value, Long expiredTime) {
        redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
        if(getData(key).equals(value))
        	return true;
        else return false;
    }

    /**
     * String 자료형의 key에 대한 value 값을 가져오는 method
     * 
     * @param key String
     * @return String
     */
    public String getData(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * String 자료형의 key에 대한 값을 삭제하는 method
     * 
     * @param key String
     * 
     * @return boolean
     */
    public boolean delData(String key) {
    	if(getData(key) == null)
    		return false;
        redisTemplate.delete(key);
        if(getData(key) == null)
            return true;
        else
        	return false;
    }
    
    
    
    /**************** using opsForList() ****************/
    // 레디스의 List는 문자열을 저장하는 Linked-List 자료구조이며, 지원되는 명령어들을 사용하여 Stack이나 Dequeue로 사용 가능 

    /**
     * List 자료형의 데이터를 왼쪽으로 문자열 원소를 삽입하는 method
     * 
     * @param key String
     * @param value String
     * 
     * @return boolean
     */
    public boolean lpush(String key, String value) {
    	redisTemplate.opsForList().leftPush(key, value);
        if(lindex(key, value) >= 0)
            return true;
        else
        	return false;
	}

    /**
     * List 자료형의 데이터를 왼쪽으로 정수형 원소를 삽입하는 method
     * 
     * @param key String
     * @param value long
     * 
     * @return boolean
     */
    public boolean lpush(String key, long value) {
    	redisTemplate.opsForList().leftPush(key, value);
        if(lindex(key, value) >= 0)
            return true;
        else
        	return false;
	}

    /**
     * List 자료형의 데이터를 가장 왼쪽에 있는 원소를 꺼내어 제거하는 method
     * 
     * @param key String
     * 
     * @return boolean
     */
    public boolean lpop(String key) {
    	List<Object> slist = range(key, 0, 0);
    	long size = lsize(key);
    	if(slist.size() <= 0)
    		return false;
    	redisTemplate.opsForList().leftPop(key);
		if(size - 1 == lsize(key))
			return true;
		else return false;
	}

    /**
     * List 자료형의 데이터를 오른쪽으로 문자열 원소를 삽입하는 method
     * 
     * @param key String
     * @param value String
     * 
     * @return boolean
     */
    public boolean rpush(String key, String value) {
    	redisTemplate.opsForList().rightPush(key, value);
        if(lindex(key, value) >= lsize(key)-1)
            return true;
        else
        	return false;
	}

    /**
     * List 자료형의 데이터를 오른쪽으로 정수형 원소를 삽입하는 method
     * 
     * @param key String
     * @param value long
     * 
     * @return boolean
     */
    public boolean rpush(String key, long value) {
    	redisTemplate.opsForList().rightPush(key, value);
        if(lindex(key, value) >= lsize(key)-1)
            return true;
        else
        	return false;
	}

    /**
     * List 자료형의 데이터를 가장 오른쪽에 있는 원소를 꺼내어 제거하는 method
     * 
     * @param key String
     * 
     * @return boolean
     */
    public boolean rpop(String key) {
    	List<Object> slist = range(key, 0, 0);
    	long size = lsize(key);
    	if(slist.size() <= 0)
    		return false;
    	redisTemplate.opsForList().rightPop(key);
		if(size - 1 == lsize(key))
			return true;
		else return false;
	}

    /**
     * List 자료형의 데이터를 가장 왼쪽에 있는 원소를 start부터 end까지 꺼내어 List 자료형으로 리턴하는 method
     * 인덱스 0부터 시작, start=0, end=-1일 경우 리스트 내 전체 데이터 리턴
     * 
     * @param key String
     * @param start long
     * @param end long
     * 
     * @return List<Object>
     */
    public List<Object> range(String key, long start, long end) {
    	return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * List 자료형의 데이터를 가장 왼쪽에 있는 원소를 start부터 end까지 보존 후 나머지 제거하여 List 자료형으로 저장하는 method
     * 
     * @param key String
     * @param start long
     * @param end long
     * 
     * @return
     */
    public List<Object> trim(String key, long start, long end) {
    	redisTemplate.opsForList().trim(key, start, end);
    	return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * List 자료형의 key와 value에 대한 index값 리턴하는 method
     * 
     * @param key
     * @param value
     * 
     * @return long
     */
    public long lindex(String key, String value) {
    	Long index = redisTemplate.opsForList().indexOf(key, value);
    	if(index == null)
    		return -1;
    	return index;
    }

    /**
     * List 자료형의 key와 value에 대한 index값 리턴하는 method
     * 
     * @param key
     * @param value
     * 
     * @return long
     */
    public long lindex(String key, long value) {
    	Long index = redisTemplate.opsForList().indexOf(key, value);
    	if(index == null)
    		return -1;
    	return index;
    }

    /**
     * List 자료형에 들어있는 원소들의 개수 리턴하는 method
     * 
     * @param key String
     * 
     * return long
     */
    public long lsize(String key) {
    	Long size = redisTemplate.opsForList().size(key);
    	if(size == null)
    		return -1;
		return size;
	}
    
    
    
    /**************** using opsForSet() ****************/

    /**
     * Set 자료형의 데이터를 저장하는 method
     * 
     * @param key String 
     * @param value String 
     * 
     * @return boolean
     */
    public boolean sadd(String key, String value) {
    	redisTemplate.opsForSet().add(key, value);
    	return sismember(key, value);
	}

    /**
     * Set 자료형의 데이터를 저장하는 method
     * 
     * @param key String
     * @param value long 
     * 
     * @return boolean
     */
    public boolean sadd(String key, long value) {
    	redisTemplate.opsForSet().add(key, value);
    	return sismember(key, value);
	}

    /**
     * Set 자료형의 랜덤으로 하나 데이터를 꺼내는 method
     * 
     * @param key String
     * 
     * @return String
     */
    public String spop(String key) {
     	return (String) redisTemplate.opsForSet().pop(key);
	}

    /**
     * Set 자료형의 랜덤으로 하나 데이터를 꺼내는 method
     * 
     * @param key String
     * 
     * @return long
     */
    public long spopTolong(String key) {
     	return (long) redisTemplate.opsForSet().pop(key);
	}
    
    /**
     * Set 자료형의 데이터를 count만큼 랜덤으로 꺼내는 method
     * 
     * @param key String
     * @param long count
     * 
     * @return List<Object>
     */
    public List<Object> spop(String key, long count) {
     	return redisTemplate.opsForSet().pop(key, count);
	}

    /**
     * key-value에 대한 Set 자료형의 데이터를 삭제하는 method 
     * 
     * @param key
     * @param value
     * 
     * @return boolean true면 삭제, false라면 삭제 대상 X
     */
    public boolean srem(String key, String value) {
     	return redisTemplate.opsForSet().remove(key, value) == 1 ? true : false;
	}

    /**
     * key-value에 대한 Set 자료형의 데이터를 삭제하는 method
     * 
     * @param key String
     * @param value long
     * 
     * @return long
     */
    public long srem(String key, long value) {
     	return redisTemplate.opsForSet().remove(key, value);
	}

    /**
     * Set 자료형의 데이터 중 key-value에 해당하는 데이터가 있는지 확인하는 method
     * 
     * @param key
     * @param value
     * 
     * @return boolean
     */
    public boolean sismember(String key, String value) {
    	return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * Set 자료형의 데이터 중 key-value에 해당하는 데이터가 있는지 확인하는 method
     * 
     * @param key
     * @param value
     * 
     * @return boolean
     */
    public boolean sismember(String key, long value) {
    	return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * Set 자료형의 데이터의 key에 있는 데이터 리턴하는 method
     * 
     * @param key
     * 
     * @return Set<Object>
     */
    public Set<Object> smembers(String key) {
    	return redisTemplate.opsForSet().members(key);
    }

    /**
     * Set 자료형의 데이터 중 랜덤으로 하나의 데이터를 리턴하는 method
     * 
     * @param key
     * 
     * @return String
     */
    public String srandmember(String key) {
    	return (String) redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * Set 자료형의 데이터 중 랜덤으로 하나의 데이터를 리턴하는 method
     * 
     * @param key
     * 
     * @return long
     */
    public long srandmemberTolong(String key) {
    	return (long) redisTemplate.opsForSet().randomMember(key);
    }
   
    /**
     * 두 개의 Set 자료형의 데이터 중 교집합으로 존재하는 데이터들을 리턴하는 method
     * 
     * @param key1
     * @param key2
     * 
     * @return
     */
    public Set<Object> sinter(String key1, String key2) {
    	return redisTemplate.opsForSet().intersect(key1, key2);
    }
    
    
    
    /**************** using opsForZSet() ****************/

    /**
     * Sorted set 자료형의 데이터(키-값-점수) 추가하는 method
     * 
     * @param key String
     * @param value String
     * @param score Double
     * 
     * @return boolean
     */
    public boolean zadd(String key, String value, double score) {
    	redisTemplate.opsForZSet().add(key, value, score);
    	return zscore(key, value) != null ? true : false;
    }

    /**
     * Sorted set 자료형의 데이터(키-값-점수) 추가하는 method
     * 
     * @param key String
     * @param value String
     * @param score Double
     * 
     * @return boolean
     */
    public boolean zadd(String key, long value, double score) {
    	redisTemplate.opsForZSet().add(key, value, score);
    	return zscore(key, value) != null ? true : false;
    }

    /**
     * Sorted set 자료형의 데이터(키-값) 제거하는 method
     * 
     * @param key String
     * @param value String
     * 
     * @return boolean
     */
    public boolean zrem(String key, String value) {
    	redisTemplate.opsForZSet().remove(key, value);
    	return zscore(key, value) == null ? true : false;
    }

    /**
     * Sorted set 자료형의 데이터(키-값) 제거하는 method
     * 
     * @param key String
     * @param value long
     * 
     * @return boolean
     */
    public boolean zrem(String key, long value) {
    	redisTemplate.opsForZSet().remove(key, value);
    	return zscore(key, value) == null ? true : false;
    }
    
    /**
     * Sorted set 자료형의 키 범위에 대한 순방향 데이터 검색하는 method
     * 
     * @param key String
     * @param min long
     * @param max long
     * 
     * @return Set<Object>
     */
    public Set<Object> zrange(String key, long start, long end) {
    	// start = 0 , end = -1 일 경우 전범위 리턴
    	return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * Sorted set 자료형의 키 범위에 대한 역방향 데이터 검색하는 method
     * 
     * @param key String
     * @param min long
     * @param max long
     * 
     * @return Set<Object>
     */
    public Set<Object> zrevrange(String key, long start, long end) {
    	// start = 0 , end = -1 일 경우 전범위 리턴
    	return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }
    
    /**
     * Sorted set 자료형의 데이터(키-값)에 대한 점수값 증가 및 감소하는 method
     * 
     * @param key String
     * @param value String
     * @param incScore double
     * 
     * @return double
     */
    public double zincrby(String key, String value, double incScore) {
    	redisTemplate.opsForZSet().incrementScore(key, value, incScore);
    	return zscore(key, value);
    }

    /**
     * Sorted set 자료형의 데이터(키-값)에 대한 점수값 증가 및 감소하는 method
     * 
     * @param key String
     * @param value long
     * @param incScore double
     * 
     * @return double
     */
    public double zincrby(String key, long value, double incScore) {
    	redisTemplate.opsForZSet().incrementScore(key, value, incScore);
    	return zscore(key, value);
    }
    
    /**
     * Sorted set 자료형의 키 값에 대한 저장된 value의 개수 리턴하는 method
     * @param key String
     * 
     * @return
     */
    public long zcard(String key) {
    	return redisTemplate.opsForZSet().zCard(key);
    }
    
   /**
    * Sorted set 자료형에서 score값이 min과 max 사이에 있는 value의 개수 리턴하는 method
    * 
    * @param key String
    * @param min double
    * @param max double
    * 
    * @return
    */
   public long zcount(String key, double min, double max) {
	   return redisTemplate.opsForZSet().count(key, min, max);
   }
   
   /**
    * Sorted set 자료형에서 score가 저장된 value의 오름차순 순위를 리턴하는 method
    * 
    * @param key String
    * @param value String
    * 
    * @return long
    */
   public long zrank(String key, String value) {
	   Long rank = redisTemplate.opsForZSet().rank(key, value);
	   return rank == null ? -1 : rank;
   }

   /**
    * Sorted set 자료형에서 score가 저장된 value의 오름차순 순위를 리턴하는 method
    * 
    * @param key String
    * @param value long
    * 
    * @return long
    */
   public long zrank(String key, long value) {
	   Long rank = redisTemplate.opsForZSet().rank(key, value);
	   return rank == null ? -1 : rank;
   }
   
   
   /**
    * Sorted set 자료형에서 score가 저장된 value의 내림차순 순위를 리턴하는 method
    * 
    * @param key String
    * @param value String
    * 
    * @return long
    */
   public long zrevrank(String key, String value) {
	   Long rank = redisTemplate.opsForZSet().reverseRank(key, value);
	   return rank == null ? -1 : rank;
   }

   /**
    * Sorted set 자료형에서 score가 저장된 value의 내림차순 순위를 리턴하는 method
    * 
    * @param key String
    * @param value long
    * 
    * @return long
    */
   public long zrevrank(String key, long value) {
	   Long rank = redisTemplate.opsForZSet().reverseRank(key, value);
	   return rank == null ? -1 : rank;
   }
   
   /**
    * Sorted set 자료형에서 key-value에 대한 score값 리턴하는 method(null 허용)
    * 
    * @param key String
    * @param value String
    * 
    * @return Double
    */
   public Double zscore(String key, String value) {
	   return redisTemplate.opsForZSet().score(key, value);
   }
   
   /**
    * Sorted set 자료형에서 key-value에 대한 score값 리턴하는 method(null 허용)
    * 
    * @param key String
    * @param value long
    * 
    * @return double
    */
   public Double zscore(String key, long value) {
	   return redisTemplate.opsForZSet().score(key, value);
   }
   
   /**
    * Sorted set 자료형에서 내림차순으로 min <= score <= max인 value값 Set형식으로 리턴하는 method
    * 
    * @param key String
    * @param min double
    * @param max double
    * 
    * @return Set<Object>
    */
   public Set<Object> zrevrangebyscore(String key, double min, double max) {
	   return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
   }
   
   /**
    * Sorted set 자료형에서 내림차순으로 min <= score <= max인 value값을 offset, count에 따라 Set형식으로 리턴하는 method
    * 
    * @param key String
    * @param min double
    * @param max double
    * @param offset long 시작점(offset >= 0)
    * @param count long 개수(count >= 1)
    * 
    * @return Set<Object>
    */
   public Set<Object> zrevrangebyscore(String key, double min, double max, long offset, long count) {
	   return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
   }
   
   /**
    * Sorted set 자료형에서 내림차순으로 value-score값을 start<=value<=end 범위에 맞는 value-score 데이터 리턴하는 method
    * 
    * @param key
    * @param start
    * @param end
    * 
    * @return Set<TypedTuple<Object>>
    */
   public Set<TypedTuple<Object>> zrevrangewithscore(String key, long start, long end) {
	   return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
   }
   
   
   
   /**************** using opsForHash() ****************/    
  
   /**
    * Hash 자료형에서 key와 hashKey에 대한 값 리턴하는 method
    * 
    * @param key String 
    * @param hashKey String
    * 
    * @return Object
    */
   public Object hget(String key, String hashKey) {
	   return redisTemplate.opsForHash().get(key, hashKey);
   }
   
   /**
    * Hash 자료형에서 key에 대한 hashKey와 그에 대한 값들 리턴하는 method
    * 
    * @param key String
    * 
    * @return List<Object>
    */
   public List<Object> hgetAll(String key) {
	   return redisTemplate.opsForHash().values(key);
   }
   
   /**
    * Hash 자료형에서 key, hashKey, value 값을 저장하는 method
    * hsetnx method가 있어 따로 return 안함
    *  
    * @param key String
    * @param hashKey String
    * @param value long
    * 
    * @return
    */
   public void hset(String key, String hashKey, String value) {
	   redisTemplate.opsForHash().put(key, hashKey, value);
   }

   /**
    * Hash 자료형에서 key, hashKey, value 값을 저장하는 method
    * hsetnx method가 있어 따로 return 안함
    * 
    * @param key String
    * @param hashKey String
    * @param value long
    * 
    * @return
    */
   public void hset(String key, String hashKey, long value) {
	   redisTemplate.opsForHash().put(key, hashKey, value);
   }

   /**
    * Hash 자료형에서 key, hashKey, value 값을 저장 성공 시 true, 저장 실패 시(=이미 value값이 있을 때) false를 리턴하는 method
    * 
    * @param key String
    * @param hashKey String
    * @param value String
    * 
    * @return boolean
    */
   public boolean hsetnx(String key, String hashKey, String value) {
	   return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
   }

   /**
    * Hash 자료형에서 key, hashKey, value 값을 저장 성공 시 true, 저장 실패 시(=이미 value값이 있을 때) false를 리턴하는 method
    * 
    * @param key String
    * @param hashKey String
    * @param value long
    * 
    * @return boolean
    */
   public boolean hsetnx(String key, String hashKey, long value) {
	   return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
   }
   
   /**
    * Hash 자료형에서 key, hashKey, value 값을 삭제 성공 시 true, 삭제 실패 시(=이미 value값이 없을 때) false를 리턴하는 method
    * 
    * @param key String
    * @param hashKey String
    * 
    * @return boolean
    */
   public boolean hdel(String key, String hashKey) {
	   return redisTemplate.opsForHash().delete(key, hashKey) == 1 ? true : false;
   }
   
   /**
    * Hash 자료형에서 key, hashKey에 대한 value값 존재 여부 확인하는 method
    * 
    * @param key String
    * @param hashKey String
    * 
    * @return boolean
    */
   public boolean hexists(String key, String hashKey) {
	   return redisTemplate.opsForHash().hasKey(key, hashKey);
   }
   
   /**
    * Hash 자료형에서 key, hashKey에 대한 value값에 incValue값만큼 증가시킨 값을 리턴하는 method
    * 
    * @param key String
    * @param hashKey String 
    * @param incValue long
    *
    * @return long
    */
   public long hincrby(String key, String hashKey, long incValue) {
	   return redisTemplate.opsForHash().increment(key, hashKey, incValue);
   }

   /**
    * Hash 자료형에서 key, hashKey에 대한 value값에 incValue값만큼 증가시킨 값을 리턴하는 method
    * 
    * @param key String
    * @param hashKey String 
    * @param incValue double
    *
    * @return double
    */
   public double hincrbyfloat(String key, String hashKey, double incValue) {
	   return redisTemplate.opsForHash().increment(key, hashKey, incValue);
   }
   
   /**
    * Hash 자료형에서 key에 대한 hashKey를 가져와서 리턴하는 method
    * 
    * @param key String
    * 
    * @return Set<Object>
    */
   public Set<Object> hkeys(String key) {
	   return redisTemplate.opsForHash().keys(key);   
   }
   
   /**
    * Hash 자료형에서 key에 저장된 hashKey의 개수를 가져와서 리턴하는 method
    * 
    * @param key String
    * 
    * @return long
    */
   public long hlen(String key) {
	   return redisTemplate.opsForHash().size(key);
   }
   

   /**
    * Hash 자료형에서 key의 hashKey에 저장 데이터의 길이 리턴하는 method
    * 
    * @param key String
    * @param hashKey String
    * 
    * @return long
    */
   public long hstrlen(String key, String hashKey) {
	   return redisTemplate.opsForHash().lengthOfValue(key, hashKey);
   }
   
   
   /**
    * Hash 자료형에서 key에 저장된 hashKey 중에서 랜덤으로 한개 가져와서 리턴하는 method
    * 
    * @param key String
    * 
    * @return String
    */
   public String hrandfield(String key) {
	   return (String) redisTemplate.opsForHash().randomKey(key);
   }
}