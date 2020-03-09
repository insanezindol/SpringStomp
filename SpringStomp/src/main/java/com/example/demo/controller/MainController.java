package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {

	@GetMapping("/")
	public String index() {
		log.info("index");
		return "/index";
	}
	
	@GetMapping("/app")
	public String app() {
		log.info("app");
		return "/app";
	}


}
