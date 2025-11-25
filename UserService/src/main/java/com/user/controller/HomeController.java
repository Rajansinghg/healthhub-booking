package com.user.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HomeController {
	
	@GetMapping("/")
	public String homecontrollerHandler() {
		return "This is a dr booking user service";
	}

}
