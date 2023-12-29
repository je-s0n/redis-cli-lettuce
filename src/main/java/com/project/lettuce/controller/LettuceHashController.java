package com.project.lettuce.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.lettuce.service.LettuceHashService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/hash")
public class LettuceHashController {
	private final LettuceHashService lettuceHashService;
	
	public LettuceHashController(LettuceHashService lettuceHashService) {
		this.lettuceHashService = lettuceHashService;
	}
	
	// 커밋을 위한 주석
	
	@RequestMapping(value = "/hget", method=RequestMethod.GET)
	@ResponseBody
	public Object setData(HttpServletRequest request) {
		String hashKey = (String) request.getParameter("hashkey");
		log.info("Controller Request: {}", request.getClass());
		Object value = lettuceHashService.hget(hashKey);
		log.info("Controller Result: {}", value);
		return value;
	}
	

	@RequestMapping(value = "/hgetAll", method=RequestMethod.GET)
	@ResponseBody
	public List<Object> hgetAll(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		List<Object> hashKeyList = lettuceHashService.hgetAll();
		log.info("Controller Result: {}", hashKeyList.size());
		return hashKeyList;
	}
	

	// hset 생략
	@RequestMapping(value = "/hsetnx", method=RequestMethod.GET)
	@ResponseBody
	public boolean hsetnx(HttpServletRequest request) {
		String hashKey = (String) request.getParameter("hashkey");
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isAdd = lettuceHashService.hsetnx(hashKey, value);
		log.info("Controller Result: {}", isAdd);
		return isAdd;
	}
	
	
	@RequestMapping(value = "/hdel", method=RequestMethod.GET)
	public boolean hdel(HttpServletRequest request) {
		String hashKey = (String) request.getParameter("hashkey");
		log.info("Controller Request: {}", request.getClass());
		boolean isDel = lettuceHashService.hdel(hashKey);
		log.info("Controller Result: {}", isDel);
		return isDel;
	}
	

	@RequestMapping(value = "/hexists", method=RequestMethod.GET)
	public boolean hexists(HttpServletRequest request) {
		String hashKey = (String) request.getParameter("hashkey");
		log.info("Controller Request: {}", request.getClass());
		boolean isExist = lettuceHashService.hexists(hashKey);
		log.info("Controller Result: {}", isExist);
		return isExist;
	}


	@RequestMapping(value = "/hincrby", method=RequestMethod.GET)
	public long hincrby(HttpServletRequest request) {
		String hashKey = (String) request.getParameter("hashkey");
		long incValue = Long.parseLong(request.getParameter("incvalue"));
		log.info("Controller Request: {}", request.getClass());
		long value = lettuceHashService.hincrby(hashKey, incValue);
		log.info("Controller Result: {}", value);
		return value;
	}
	

	@RequestMapping(value = "/hkeys", method=RequestMethod.GET)
	public Set<Object> hkeys(HttpServletRequest request) {
		long incValue = Long.parseLong(request.getParameter("incvalue"));
		log.info("Controller Request: {}", request.getClass());
		Set<Object> hashKeySet = lettuceHashService.hkeys();
		log.info("Controller Result: {}", hashKeySet);
		return hashKeySet;
	}


	@RequestMapping(value = "/hlen", method=RequestMethod.GET)
	public long hlen(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		long length = lettuceHashService.hlen();
		log.info("Controller Result: {}", length);
		return length;
	}


	@RequestMapping(value = "/hstrlen", method=RequestMethod.GET)
	public long hstrlen(HttpServletRequest request) {
		String hashKey = (String) request.getParameter("hashkey");
		log.info("Controller Request: {}", request.getClass());
		long strLength = lettuceHashService.hstrlen(hashKey);
		log.info("Controller Result: {}", strLength);
		return strLength;
	}

	
	@RequestMapping(value = "/hrandfield", method=RequestMethod.GET)
	public String hrandfield(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		String randHashKey = lettuceHashService.hrandfield();
		log.info("Controller Result: {}", randHashKey);
		return randHashKey;
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
