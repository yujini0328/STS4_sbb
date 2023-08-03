package com.mysite.sbb.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController2 {
	
	@GetMapping("/anker")			// http://localhost:9696/anker
	public String ankerTest() {
		
		return "/test/anker_test"; 
	}

}