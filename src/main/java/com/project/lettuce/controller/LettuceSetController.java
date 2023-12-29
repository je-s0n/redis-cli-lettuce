package com.project.lettuce.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.lettuce.service.LettuceSetService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/set")
public class LettuceSetController {
	private final LettuceSetService lettuceSetService;
	
	public LettuceSetController(LettuceSetService lettuceSetService) {
		this.lettuceSetService = lettuceSetService;
	}
	
	@RequestMapping(value = "/sadd", method=RequestMethod.GET)
	@ResponseBody
	public boolean sadd(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isAdd = lettuceSetService.sadd(value);
		log.info("Controller Result: {}", isAdd);
		return isAdd;
	}
	

	@RequestMapping(value = "/spop", method=RequestMethod.GET)
	@ResponseBody
	public String spop(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		String resultValue = lettuceSetService.spop();
		log.info("Controller Result: {}", resultValue);
		return resultValue;
	}
	

	@RequestMapping(value = "/spop/count", method=RequestMethod.GET)
	@ResponseBody
	public Object spopCount(HttpServletRequest request) {
		long count = Long.parseLong(request.getParameter("count"));
		log.info("Controller Request: {}", request.getClass());
		Object redisValue = lettuceSetService.spopCount(count); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", redisValue);
		return redisValue;
	}

	
	@RequestMapping(value = "/srem", method=RequestMethod.GET)
	public boolean srem(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isDel = lettuceSetService.srem(value); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", isDel);
		return isDel;
	}

	
	@RequestMapping(value = "/sismember", method=RequestMethod.GET)
	public boolean sismember(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isMember = lettuceSetService.sismember(value); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", isMember);
		return isMember;
	}

	
	@RequestMapping(value = "/smembers", method=RequestMethod.GET)
	public Set<Object> smembers(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		Set<Object> members = lettuceSetService.smembers(); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", members.size());
		return members;
	}
	
	
	@RequestMapping(value = "/sinter", method=RequestMethod.GET)
	public Set<Object> sinter(HttpServletRequest request) {
		String diffKey = (String) request.getParameter("key");
		log.info("Controller Request: {}", request.getClass());
		Set<Object> members = lettuceSetService.sinter(diffKey); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", members.size());
		return members;
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
