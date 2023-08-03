package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;        // 주의 해서 IMPORT 

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository; 
	
	//Question 테이블의 모든 레코드를 읽어와서 List<Question> 으로 값을 리턴하는 메소드 
	// 페이징 처리되지 않는 모든 레코드를 리턴 (사용중지) 
	public List<Question> getList() {
			
		List<Question> questionList = questionRepository.findAll(); 
		//return questionRepository.findAll(); 
		return questionList; 
	}
	
	// 페지징 처리해서 리턴으로 돌려줌 <사용> 
	public Page<Question> getList(int page, String kw){
		
		// Pageable 객체에 특정 컬럼을 정렬할 객체를 생성해서 인자로 넣어줌 
		// Sort Import시 주의 : org.springframework.data.domain.Sort; 
		List<Sort.Order> sorts = new ArrayList(); 
		sorts.add(Sort.Order.desc("createDate")); 
			
		//page : 클라이언트에서 파라메터로 요청한 페이지 번호
		// 10  : 한 페이지에서 출력 할 레코드 갯수 
		// createDate 컬럼을 desc 
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); 
		
		//Page<Question> pageQuestion = questionRepository.findAll(pageable); 
		Page<Question> pageQuestion = questionRepository.findAllByKeyword(kw, pageable);
		
		
		return pageQuestion; 
	}
	
	
	//글 상페 페이지 
	public Question getQuestion(Integer id) {
		// findById(?)
		// select * from question where id = ?; 
		
		Optional<Question> question = questionRepository.findById(id); 
		
		if (question.isPresent()) {
			//optional 내부의 객체가 null 이 아닐때  
			//Question 객체를 리턴으로 돌려줌 
			return question.get(); 
			
		}else {
			//optional 내부의 객체가 null 일때 : 예외를 강제로 발생 시킴 : DataNotFoundException 
			//throw : 예외를 강제로 발생 시킴 
			//throws : 메소드에서 메소드를 호출 하는 곳에서 예외를 처리하도로 예외를 전가 
			throw new DataNotFoundException("question not found"); 
			
		}
		
	}
	
	// 질문 제목 + 질문 내용을 DB에 저장 : insert , update, delete  <=== void 
	public void create(String subject, String content, SiteUser siteUser) {
		Question question = new Question(); 
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setAuthor(siteUser);
		
		questionRepository.save(question); 
		
	}
	
	// 글수정 : save()
	public void modify ( Question question, String subject , String content) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		
		questionRepository.save(question); 
		
	}
	
	//글 삭제 : delete()  :  delete 할 question 객체를 가지고 와서 인풋 
	public void delete(Question question) {
			
		questionRepository.delete(question); 
		
	}
	
	// 글 추천 등록 메소드 
	public void vote(Question question, SiteUser siteuser) {
		
		//question.getVoter()   <=== Set 
		question.getVoter().add(siteuser); 
		
		//주의 : 
		//question.setVoter(siteuser);
		
		
		questionRepository.save(question); 
		
	}

	
	

}