package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	// client 에서 넘어오는 모든 필드를 한꺼 번에 주입 : 
	// 필드의 값에 대한 유효성 체크 
	
	@NotEmpty(message="사용자 ID는 필수 입력 사항입니다.")
	@Size(min=3 , max=25)
	private String username; 
	
	@NotEmpty(message="패스워드는 필수 입력 사항입니다.")
	private String password1; 
	
	@NotEmpty(message="패스워드 확인은 필수 입력 사항입니다.")
	private String password2; 
	
	@NotEmpty(message="메일주소는 필수 입력 사항입니다. ")
	@Email								// aaaa@aaaa.com
	private String email; 
	
}