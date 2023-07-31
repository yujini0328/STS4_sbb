package com.mysite.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND, reason = "Entity Not Found")
public class DataNotFoundException extends RuntimeException {
	// 실행 예외 : 실행시 오류
	// 파일을 찾을 수 없을때 DataNotFoundException 이 발생됨 
	
	private static final long serialVersionUID = 1L; 
	
	public DataNotFoundException(String message) {
		super(message); 
	}
}