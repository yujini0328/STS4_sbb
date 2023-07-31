package com.mysite.sbb.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest2 {
	
	@Autowired
	private UserService userService; 

	@Test
	void testCreate() {
		
		SiteUser siteuser = new SiteUser(); 
		
		siteuser = 
		userService.create("user13", "user13@aaa.com", "1234"); 
		
		System.out.println("잘 등록 되었습니다. ");
	}
	
	@Test
	void testselectUser() {
		
		userService.selectUser("user12"); 
		
		System.out.println("성공");
	}
	

}