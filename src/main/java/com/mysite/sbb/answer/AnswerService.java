package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	//Service  ====> Repository 
	
	private final AnswerRepository answerRepository; 
	
	//답변글 등록 : Question 객체, content 
	public void create (Question question, String content, SiteUser author) {
		Answer answer = new Answer(); 
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		
		// 추가됨 
		answer.setAuthor(author);
		
		answerRepository.save(answer); 
			
	}
	
	// 답변글 수정전에 id값을 받아서 Answer 객체를 리턴 으로 돌려줌 
	public Answer getAnswer (Integer id) {
		
		Optional<Answer> _answer = 
				answerRepository.findById(id);
		
		//_answer 의 객체가 비어있지 않을때 리턴  
		if (_answer.isPresent()) {
			
			return _answer.get();
			
		}else {
			//객체가 비어있을때 : DB의 값이 존재 하지 않을때 예외 발생 
			throw new DataNotFoundException ("answer not found"); 
		}
	}
	
	// 답변 수정 메소드 
	public void modify(Answer answer, String content) {
		
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		
		answerRepository.save(answer); 
	}
	
	// 삭제 메소드 : Answer 객체를 매개변수로 받아서 삭제 
	public void delete(Answer answer) {
		
		
		answerRepository.delete(answer);
	}
	

}