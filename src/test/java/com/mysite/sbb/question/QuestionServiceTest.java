package com.mysite.sbb.question;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionServiceTest {
	
	@Autowired
	private QuestionService questionService; 

	@Test
	void testGetList() {
		fail("Not yet implemented");
	}

	@Test
	void testGetListInt() {
		fail("Not yet implemented");
	}

	@Test
	void testGetQuestion() {
		
		Question question = 
				questionService.getQuestion(11); 
		
		System.out.println(question.getId());
		System.out.println(question.getSubject());
		System.out.println(question.getContent());
		System.out.println(question.getAuthor().getUsername());
		
		System.out.println("Question 객체 : 출력 성공");
		

	}

	@Test
	void testDelete() {

		// Delete 할 Question 객체 
		Question question = 
				questionService.getQuestion(2); 
		
		questionService.delete(question);
		
		System.out.println("레코드 삭제 성공");
		
	}

	@Test
	void testQuestionService() {
		
		Question question = 
				questionService.getQuestion(1); 
		
		questionService.modify(question, "제목 수정-금요일", "내용수정-금요일");
		
		System.out.println(" 글 수정 성공 됨 ");
	}

}