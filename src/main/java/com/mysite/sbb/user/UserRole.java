package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {
	// 로그인 후의 사용자의 권한을 적용하는 Enum 
	// 상수 : 값을 수정 할 수 없다. 
	
	ADMIN("ROLE_ADMIN"), 
	USER("ROLE_USER") ; 
	
	UserRole(String value){
		this.value = value; 
	}
	
	private String value; 

}