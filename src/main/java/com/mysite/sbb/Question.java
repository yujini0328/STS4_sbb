package com.mysite.sbb;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;		//spring boot 3.0 ,
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
//import javax.persistence.Entity;	//spring boot 2.0 

@Getter
@Setter

@Entity
public class Question {
		//질문 테이블 : 부모 테이블 
	
	//@Entity :  클래스 위에 부여 , DataBase의 테이블과 매칭 되는 자바 클래스 
		// Question : 테이블명 
		// 		변수 : 컬럼명 
	//테이블의 컬럼명을 지정
	// JPA를 사용해서 테이블의 컬럼을 정의 할시 반드시 Primary Key 를 정의 
		// @Id 어노테이션 : Primary Key 컬럼을 부여 , 중복된 데어터를  넣을 수 없다. 
		// @GeneratedValue : 자동으로 값을 증가 , 시퀀스 (오라클) , auto_increament (MySQL) 
				// identity (MSSQL) 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column (length = 200)
	private String subject; 
	
	@Column(columnDefinition = "Text")
	private String content; 
	
	@CreatedDate
	private LocalDateTime createDate; 
	
	//실제 테이블에는 적용되지 않음. 
	//해당 질문에 대한 모든 답변을 불러오는 컬럼. 
	//Question 객체의 id 필드에 들어오는 값에대한 Answer 테이블의 모든 값을 List 에 담아서 온다. 
	// getAnswerList() 
		// Question : one , Answer : Many 
		// cascade = CascadeType.REMOVE   <== 해당 질문이 제거 될 경우 , 그 질문에 대한 답변글도 
				//모두 함게 제거 되도록 
	@OneToMany (mappedBy = "question" , cascade = CascadeType.REMOVE)
	private List<Answer> answerList; 
	
	
	
	/*
	@Column( length = 100) 
	private String name; 
	
	@Column( length = 100) 
	private String age; 
	*/ 

}
