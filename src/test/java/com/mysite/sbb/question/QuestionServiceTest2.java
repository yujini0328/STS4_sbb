package com.mysite.sbb.question;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

@SpringBootTest
class QuestionServiceTest2 {

	@Autowired
	QuestionService questionService; 
	
	@Autowired
	UserService userService; 
	
	@Test
	void testCreate() {
		
		SiteUser siteUser = 
				userService.getUser("user12");
		
		for (int i = 1 ; i <= 100 ; i++) {
			String subj = "제목 스프링" + i ;
			String cont = "내용 스프링" + i ; 
			
			questionService.create(subj ,  cont , siteUser);

		}
	}

}