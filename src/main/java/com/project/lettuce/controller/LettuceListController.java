package com.project.lettuce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.lettuce.service.LettuceListService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/list")
public class LettuceListController {
	private final LettuceListService lettuceListService;
	
	public LettuceListController(LettuceListService lettuceListService) {
		this.lettuceListService = lettuceListService;
	}
	
	
	@RequestMapping(value = "/lpush", method=RequestMethod.GET)
	@ResponseBody
	public boolean lpush(HttpServletRequest request) {
		// http://localhost:8080/setdata?value=text
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isPush = lettuceListService.lpush(value);
		log.info("Controller Result: {}", isPush);
		return isPush;
	}
	

	@RequestMapping(value = "/lpop", method=RequestMethod.GET)
	@ResponseBody
	public boolean lpop(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		boolean isPop = lettuceListService.lpop();
		log.info("Controller Result: {}", isPop);
		return isPop;
	}
	

	@RequestMapping(value = "/rpush", method=RequestMethod.GET)
	@ResponseBody
	public boolean rpush(HttpServletRequest request) {
		// http://localhost:8080/setdata?value=text
		String value = (String) request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		boolean isPush = lettuceListService.rpush(value);
		log.info("Controller Result: {}", isPush);
		return isPush;
	}
	

	@RequestMapping(value = "/rpop", method=RequestMethod.GET)
	@ResponseBody
	public boolean rpop(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		boolean isPop = lettuceListService.rpop();
		log.info("Controller Result: {}", isPop);
		return isPop;
	}


	@RequestMapping(value = "/range", method=RequestMethod.GET)
	@ResponseBody
	public List<Object> range(HttpServletRequest request) {
		long start = Long.parseLong(request.getParameter("start"));
		long end = Long.parseLong(request.getParameter("end"));
		log.info("Controller Request: {}", request.getClass());
		List<Object> rangeList = lettuceListService.range(start, end);
		log.info("Controller Result: {}", rangeList.size());
		return rangeList;
	}

	
	@RequestMapping(value = "/trim", method=RequestMethod.GET)
	@ResponseBody
	public List<Object> trim(HttpServletRequest request) {
		long start = Long.parseLong(request.getParameter("start"));
		long end = Long.parseLong(request.getParameter("end"));
		log.info("Controller Request: {}", request.getClass());
		List<Object> trimList = lettuceListService.trim(start, end);
		log.info("Controller Result: {}", trimList.size());
		return trimList;
	}
	
	
	@RequestMapping(value = "/lindex", method=RequestMethod.GET)
	@ResponseBody
	public long lindex(HttpServletRequest request) {
		String value = request.getParameter("value");
		log.info("Controller Request: {}", request.getClass());
		long index = lettuceListService.lindex(value);
		log.info("Controller Result: {}", index);
		return index;
	}

	
	@RequestMapping(value = "/lszie", method=RequestMethod.GET)
	@ResponseBody
	public long lsize(HttpServletRequest request) {
		log.info("Controller Request: {}", request.getClass());
		long size = lettuceListService.lsize();
		log.info("Controller Result: {}", size);
		return size;
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
