package com.mysite.sbb.question;


import jakarta.validation.constraints.NotEmpty;   // 유효성 검사 
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {    //DTO , 

	@NotEmpty(message="제목을 필수 항목입니다.")
	@Size(max=200)
	private String subject; 
	
	@NotEmpty(message="내용은 필수 항목 입니다.")
	private String content; 
}