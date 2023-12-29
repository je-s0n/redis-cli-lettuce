package com.project.lettuce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.lettuce.service.LettuceStringService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/string")
public class LettuceStringController {
	private final LettuceStringService lettuceStringService;
	
	public LettuceStringController(LettuceStringService lettuceStringService) {
		this.lettuceStringService = lettuceStringService;
	}
	
	@RequestMapping(value = "/setdata", method=RequestMethod.GET)
	@ResponseBody
	public boolean setData(HttpServletRequest request) {
		// http://localhost:8080/setdata?value=text
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isAdd = lettuceStringService.setData(value);
		log.info("Controller Result: {}", isAdd);
		return isAdd;
	}
	

	@RequestMapping(value = "/setdata/expire", method=RequestMethod.GET)
	@ResponseBody
	public boolean setDataExpire(HttpServletRequest request) {
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isAdd = lettuceStringService.setDataExpire(value, 1000 * 60 * 60); // 1000 * 60 * 60 = 1 hour
		log.info("Controller Result: {}", isAdd);
		return isAdd;
	}
	

	@RequestMapping(value = "/getdata", method=RequestMethod.GET)
	@ResponseBody
	public Object getData(HttpServletRequest request) {
		String key = (String) request.getParameter("key");
		log.info("Controller Request: {}", request.getClass());
		Object redisValue = lettuceStringService.getData(key); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", redisValue);
		return redisValue;
	}

	
	@RequestMapping(value = "/deldata", method=RequestMethod.GET)
	public boolean delData(HttpServletRequest request) {
		String key = (String) request.getParameter("key");
		log.info("Controller Request: {}", request.getClass());
		boolean isDel = lettuceStringService.delData(key); // key example name : stringKey, stringExpireKey
		log.info("Controller Result: {}", isDel);
		return isDel;
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
