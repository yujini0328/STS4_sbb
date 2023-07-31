package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")		// http://localhost:9696
	public String index() {
		
		return "rediredc:/question/list";
	}

}
