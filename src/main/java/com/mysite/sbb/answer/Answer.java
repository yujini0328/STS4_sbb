package com.mysite.sbb.answer;

import java.time.LocalDateTime;		//그 지역에 맞도록 날짜와 시간을 등록 
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;   


@Getter
@Setter

@Entity
public class Answer {
		//답변글을 저장하는 테이블 : 자식 테이블 
	
	//주의 : Entity  클래스에는 @Getter / @Setter 를 붙이지 않는다. DTO생성후 붙인다. 
		//편의상 
	
	//@Entity : Java의 클래스를 DB의 테이블로 매핑 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	//@Column(columnDefinition = "text")		//Orale DB에 존재하지 않는 자료형 
	@Column(length=4000)
	private String content; 
	
	@CreatedDate
	private LocalDateTime createDate;   //2003-07-18 
		//JPA 에서 필드이름을 : createDate   <=====> CREATE_DATE 
	
	private LocalDateTime modifyDate; 
	
	//Foreign Key ; 
		// question   <======> QUESTION_ID
	@ManyToOne   (fetch = FetchType.LAZY)      //   답변(Answer) : Many   ======>   질문(Question)  : one
	private Question question; 
	
	// Foreign Key :    
	@ManyToOne (fetch = FetchType.LAZY)
	private SiteUser author; 
	
	
	//답변 과 추천인의 관계는 다 : 다   
	// Set 은 중복된 값이 올수 없다. 
	// ANSWER_VOTE 테이블이 생성됨 : ANSWER_ID, VOTER_ID 자동생성 
			// ANSWER_ID 컬럼은 ANSWER 테이블의 ID를 참조 
			// VOTER_ID 컬럼은 SiteUser 테이블의 ID 를 참조 
	@ManyToMany (fetch = FetchType.LAZY)    // 지연 로딩 : 요청이 발생할때 값을 넣어서 작동 
	Set<SiteUser> voter;
	
	
}