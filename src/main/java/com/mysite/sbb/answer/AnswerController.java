package com.mysite.sbb.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	// Controller ====> Service 
	
	//@RequestMapping : get, post 요청을 모두 처리,  
	//
	
	private final AnswerService answerService; 
	private final QuestionService questionService; 
	private final UserService userService; 
	
	// 답변글을 저장 :  반드시 로그인후 저장 하도록 설정 
	@PreAuthorize("isAuthenticated()") 
	@PostMapping("/create/{id}")		// /answer/create/{id}
	public String createAnswer(Model model , @PathVariable Integer id, 
		@Valid	AnswerForm answerForm, BindingResult bindingResult, 
		Principal principal
			) {
		
		// Principal : 로그인한 사용자 정보 (사용자 ID) 
		// principal.getName() : Client 시스템에서 현재 로그인한 정보를 가지고옴. 
		//System.out.println("현재 로그인한 사용자 정보 : " + principal.getName());
		
		
		
		
		// id 변수가 잘 넘어오는지 출력 
		// System.out.println("=====id : " + id );
		// System.out.println("=====content : " + content);
		// System.out.println("=====abc  : " + abc);
		 
		//1. 변수 : id 의 값으로 Question 객체를 받아와야 함. 
		Question question = questionService.getQuestion(id); 
		
		// 유효성 검사를 확인후 다음 스탭 진행 
		if (bindingResult.hasErrors()) {
			// 오류가 발생 되었을때 뷰 페이지에서 머물면서 오류 메세지 출력 
			
			model.addAttribute("question", question);
			return "question_detail"; 
			
		}	
		
		//Principal 에서 username 을 인풋받아서 SiteUser 객체를 받아온다. 
		SiteUser siteUser = userService.getUser(principal.getName()); 
		
		//2. Service 에서 변수 2개를 넣어서 값을 Insert 
		answerService.create(question, answerForm.getContent(), siteUser); 
		
		//question_detail 로 리턴 : get 방식으로 URL로 redirect 
		return String.format("redirect:/question/detail/%s",id ) ; 
		
		
	}
	
	// 답변글 수정 뷰페이지로 전달 
	@PreAuthorize("isAuthenticated()")    //인증된 사용자만 접근, 인증되지 않았을때 인증 폼으로 전송
	@GetMapping ("/modify/{id}")
	public String answerModify(
			AnswerForm answerForm, @PathVariable("id") Integer id , 
			Principal principal		
			) {
		// id 값으로 Answer 객체 반환 
		Answer answer = 
				answerService.getAnswer(id); 
		
		// answerForm : DB에서 가져온 값을 저장후 뷰 페이지로 전송
		answerForm.setContent(answer.getContent()); 
		
		//답글을 수정하는 페이지로 던짐 
		return "answer_form"; 
	}
	
	//답글 DB에 수정 
	@PreAuthorize("isAuthenticated()")
	@PostMapping ("/modify/{id}")
	public String answerModify(
			@PathVariable("id") Integer id , 
			@Valid AnswerForm answerForm, BindingResult bindingResult,
			Principal principal			
			) {
		
		// id를 받아서 Answer 객체를 끄집어 온다. 
		Answer answer = answerService.getAnswer(id); 
		
		//answerForm의 content 필드의 값이 넘어오지 않을 경우 
		if (bindingResult.hasErrors()) {
			
			return "answer_form"; 
		}
		
		//서비스 , 
			
		answerService.modify(answer, answerForm.getContent()); 
		
			
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId()); 
	}
	
	//답변 삭제 
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String answerDelete(
			@PathVariable ("id") Integer id			
			) {
		// id 값으로 Answer 객체를 DB에서 가지고 온다.
		Answer answer = 
				answerService.getAnswer(id); 
		
		// delete 메소드 호출 
		answerService.delete(answer);
		
		
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId()); 
	}
	
	

}