package com.cate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping
	public String getMethodName() {
		return "This is the Category MicroService";
	}
}
