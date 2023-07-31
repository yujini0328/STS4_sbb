package com.mysite.sbb.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	private UserService userService; 
	
	

	@Test
	void testCreate() {
		SiteUser siteUser = new SiteUser(); 
		
		siteUser = userService.create("user1", "user1@aaa.com", "1234"); 
		
		System.out.println("DataBase Insert 성공 !!!");
		
	}

}