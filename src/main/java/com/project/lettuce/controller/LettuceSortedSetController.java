package com.project.lettuce.controller;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.lettuce.service.LettuceSortedSetService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sortedset")
public class LettuceSortedSetController {
	private final LettuceSortedSetService lettuceSortedSetService;
	
	public LettuceSortedSetController(LettuceSortedSetService lettuceSortedSetService) {
		this.lettuceSortedSetService = lettuceSortedSetService;
	}
	
	@RequestMapping(value = "/zadd", method=RequestMethod.GET)
	@ResponseBody
	public boolean zadd(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		double score = Double.parseDouble(request.getParameter("score"));
		log.info("Controller Request: {}", request.getClass());
		boolean isAdd = lettuceSortedSetService.zadd(value, score);
		log.info("Controller Result: {}", isAdd);
		return isAdd;
	}
	

	@RequestMapping(value = "/zrem", method=RequestMethod.GET)
	@ResponseBody
	public boolean zrem(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isRm = lettuceSortedSetService.zrem(value); // 1000 * 60 * 60 = 1 hour
		log.info("Controller Result: {}", isRm);
		return isRm;
	}
	

	@RequestMapping(value = "/zrange", method=RequestMethod.GET)
	@ResponseBody
	public Object zrange(HttpServletRequest request) {
		long start = Long.parseLong(request.getParameter("start"));
		long end = Long.parseLong(request.getParameter("end"));
		log.info("Controller Request: {}", request.getClass());
		Set<Object> rangeSet = lettuceSortedSetService.zrange(start, end); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", rangeSet.size());
		return rangeSet;
	}


	@RequestMapping(value = "/zrevrange", method=RequestMethod.GET)
	@ResponseBody
	public Object zrevrange(HttpServletRequest request) {
		long start = Long.parseLong(request.getParameter("start"));
		long end = Long.parseLong(request.getParameter("end"));
		log.info("Controller Request: {}", request.getClass());
		Set<Object> rangeSet = lettuceSortedSetService.zrevrange(start, end); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", rangeSet.size());
		return rangeSet;
	}
	
	
	@RequestMapping(value = "/zincrby", method=RequestMethod.GET)
	public double zincrby(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		double score = Double.parseDouble(request.getParameter("score"));
		log.info("Controller Request: {}", request.getClass());
		double incScore = lettuceSortedSetService.zincrby(value, score); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", incScore);
		return incScore;
	}

	
	@RequestMapping(value = "zcard", method=RequestMethod.GET)
	public long zcard(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		long allCount = lettuceSortedSetService.zcard(); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", allCount);
		return allCount;
	}
	
	
	@RequestMapping(value = "zcount", method=RequestMethod.GET)
	public long zcount(HttpServletRequest request) {
		double min = Double.parseDouble(request.getParameter("min"));
		double max = Double.parseDouble(request.getParameter("max"));
		log.info("Controller Request: {}", request.getClass());
		long count = lettuceSortedSetService.zcount(min, max); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", count);
		return count;
	}

	
	@RequestMapping(value = "zrank", method=RequestMethod.GET)
	public long zrank(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		long rank = lettuceSortedSetService.zrank(value); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", rank);
		return rank;
	}

	
	@RequestMapping(value = "zrevrank", method=RequestMethod.GET)
	public long zrevrank(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		long rank = lettuceSortedSetService.zrevrank(value); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", rank);
		return rank;
	}
	
	
	@RequestMapping(value = "zscore", method=RequestMethod.GET)
	public Double zscore(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		Double score = lettuceSortedSetService.zscore(value); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", score);
		return score;
	}
	
	
	@RequestMapping(value = "zrevrangebyscore", method=RequestMethod.GET)
	public Set<Object> zrevrangebyscore(HttpServletRequest request) {
		double min = Double.parseDouble(request.getParameter("min"));
		double max = Double.parseDouble(request.getParameter("max"));
		log.info("Controller Request: {}", request.getClass());
		Set<Object> rangeSet = lettuceSortedSetService.zrevrangebyscore(min, max); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", rangeSet);
		return rangeSet;
	}
	

	@RequestMapping(value = "zrevrangewithscore", method=RequestMethod.GET)
	public Set<TypedTuple<Object>> zrevrangewithscore(HttpServletRequest request) {
		long start = Long.parseLong(request.getParameter("start"));
		long end = Long.parseLong(request.getParameter("end"));
		log.info("Controller Request: {}", request.getClass());
		Set<TypedTuple<Object>> rangeWithScoreSet = lettuceSortedSetService.zrevrangewithscore(start, end); // key example name : stringKey, stringExpireKey
		/*
		// 아래와 같이 Set으로 가져온 데이터의 value와 score값을 가져올 수 있음 
		for(TypedTuple<Object> obj : rangeWithScoreSet) {
			Double score = obj.getScore();
			String value = obj.getValue();
		}
		*/
		log.info("Controller Result: {}", rangeWithScoreSet);
		return rangeWithScoreSet;
	}
	
	/*
	@RequestMapping(value = "/test", method=RequestMethod.POST)
	@ResponseBody
	public boolean lettuceTest(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean test = lettuceService.setDataExpire(value, 1000 * 60 * 60); // 1000 * 60 * 60 = 1 hour
		log.info("Controller Result: {}", test);
		return test;
	} 
	*/
}
