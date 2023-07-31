package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class SbbApplicationTests {
	
	//QuestionRepository 객체 주입 : Question Entity( Table ) <== CRUD  
	@Autowired
	private QuestionRepository qr;
	
	
	//AnswerRepository : Answer 테이블의 CRUD 
	@Autowired
	private AnswerRepository ar; 
	
	

//	@Test
	void contextLoads() {
		Question q1 = new Question(); 
		
		q1.setSubject("오늘의 날씨는? ");
		q1.setContent("오늘의 날씨는 어떠한지 궁금합니다.");
		q1.setCreateDate(LocalDateTime.now());
		
		qr.save(q1);
	
		
		Question q2 = new Question(); 
		
		q2.setSubject("스프링 부트 질문 ");
		q2.setContent("스프링 부트가 무엇인지 어떠한지 궁금합니다.");
		q2.setCreateDate(LocalDateTime.now());
		
		qr.save(q2); 
	
	}
	
//	@Test
	void contextLoads1() {
		Question q3 = new Question(); 
		
		q3.setSubject("오늘의 스포츠는? ");
		q3.setContent("오늘의 스포츠는 어떠한지 궁금합니다.");
		q3.setCreateDate(LocalDateTime.now());
		
		qr.save(q3); 
		
		Question q4 = new Question(); 
		
		q4.setSubject("미국의 날씨는  ");
		q4.setContent("미국의 날씨가  어떠한지 궁금합니다.");
		q4.setCreateDate(LocalDateTime.now());
		
		qr.save(q4); 
	
	}
	
	//@Test
	void recodeCount() {
		List<Question> all = qr.findAll();
			//qr : Question 테이블과 연결
			//findAll()  : select * from question;
		
			//assertEquals (기대치, 값) ; 
		assertEquals(4, all.size()); 
		
		
	}
	//@Test
	void subjectTest() {		
		List<Question> all = qr.findAll(); 		
		Question q = all.get(1); 

		assertEquals("스프링 부트 질문 ", q.getSubject()); 		
	}
	
	//@Test
	void idSearch () {
		
		//Optional null을 쉽게 처리해주는 객체 
		// qr.findById(1)  <=====> select * from question where id = 1 ; 
		Optional<Question> o = qr.findById(1); 
		
		// isPresent() : Optional 에 저장된 객체가 null 이아닐때 
		if (o.isPresent()) {
			Question q = o.get() ;     // Optional 에 저장된 객체를 끄집어 내서 q 변수에 할당. 
			
			// assertEquals(1, q.getId()) ; 
			assertEquals("오늘의 날씨는? ", q.getSubject()); 
		}
		
	}
	// 제목으로 검색 Test 
	//@Test	
	void searchSubject() {
		List<Question> all = qr.findBySubject("오늘의 날씨는? "); 
		
		Question q = all.get(0); 
		
		assertEquals("오늘의 날씨는? ", q.getSubject()); 
		assertEquals(1, all.size());
		
	}
	//@Test
	void serchContent() {
		List<Question> all = qr.findByContent("오늘의 날씨는 어떠한지 궁금합니다."); 
		
		Question q = all.get(0); 
		
		assertEquals("오늘의 날씨는 어떠한지 궁금합니다.", q.getContent()); 	
	}
	//@Test
	void searchSubjectLike() {
		//select * from question where subject like '%날씨%' ; 
		List<Question> all = qr.findBySubjectLike("%날씨%"); 
		
		Question q = all.get(1); 
		
		assertEquals(2 , all.size()); 
		assertEquals(4, q.getId()); 
		
	}
	//@Test
	void serchContentLike() {
		List <Question> all = qr.findByContentLike("%궁금%"); 
		
		Question q = all.get(3); 
		
		assertEquals(4, all.size());
		assertEquals(4, q.getId()); 
		
	}
	
	// 제목 과 내용을 동시에 검색 
	//@Test
	void searchSubjectContentLike() {
		//select * from question where subject like '%?%' or content like '%?%'
		List <Question> all = qr.findBySubjectLikeOrContentLike("%날씨%", "%부트%"); 
		
		Question q = all.get(0); 
		
		assertEquals(3, all.size());
		assertEquals("오늘의 날씨는 어떠한지 궁금합니다.", q.getContent()); 
		
	}
	//@Test
	void sortCreatDateAsc () {
		//날짜를 기준으로 오름 차순 정렬 해서 List 저장 
//		List<Question> all = qr.findAllOrderByCreateDateAsc(); 
		
//		assertEquals (4, all.size() ); 
		/*
		System.out.println("========날짜를 오름차순 정렬 출력 : 시작 =========================");
		for ( int i = 0 ; i < all.size() ; i++) {
			
			System.out.println("=======" + i + "=========");
			
			Question q = all.get(i); 
			
			System.out.println(q.getId());
			System.out.println(q.getCreateDate());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
		}
		System.out.println("========날짜를 오름차순 정렬 출력 : 끝 =========================");
	
	*/
	}
	
	// select * from question where subject Like '%?%' order by create_date asc; 
	//@Test
	void searchSubjectAndSort() {
		List<Question> all = qr.findBySubjectLikeOrderByCreateDateAsc("%날씨%"); 
		
		assertEquals(2, all.size()); 
		
		System.out.println("=======콘솔 출력 시작==========");
		for (int i = 0 ; i < all.size(); i++) {
			System.out.println("=====Question : " + i + "============");
			Question q = all.get(i); 
			System.out.println(q.getId());
			System.out.println(q.getCreateDate());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());	
		}
		System.out.println("=======콘솔 출력 끝==========");
		
		
	}
	
	
	  // select * from question where subject Like '%?%' order by create_date Desc; 
		@Test
		void searchSubjectAndSortDesc() {
			List<Question> all = qr.findBySubjectLikeOrderByCreateDateDesc("%날씨%"); 
			
			assertEquals(2, all.size()); 
			
			System.out.println("=======콘솔 출력 시작==========");
			for (int i = 0 ; i < all.size(); i++) {
				System.out.println("=====Question : " + i + "============");
				Question q = all.get(i); 
				System.out.println(q.getId());
				System.out.println(q.getCreateDate());
				System.out.println(q.getSubject());
				System.out.println(q.getContent());	
			}
			System.out.println("=======콘솔 출력 끝==========");
			
			
		}
		
		// 값 넣기 : save() , insert
		// Question 테이블에 값넣기 , Answer 테이블에 값넣기 
		//@Test
		void insertQuestion () {
			Question q = new Question(); 
			
			q.setSubject("제목 - 스프링 부트는 프레임워크 입니까?" );
			q.setContent("내용 - 스프링 부트는 프레임워크 입니다. 아주 쉽습니다. IoC/DI, AOP, PSA 기능이 적용");
			q.setCreateDate(LocalDateTime.now()); 
			
			qr.save(q); 
			
		}
		
		//update : 수정 , save() 
		//@Test
		void updateQuestion () {
			Question q = new Question () ; 
			
			q.setId(5);
			
			q.setContent("수정된 내용2 입니다");
			q.setSubject("수정된 제목 2 입니다.");
			q.setCreateDate(LocalDateTime.now());
			
			qr.save(q); 	
			
		}
		//@Test
		void deleteQuestion () {
			Question q = new Question() ; 
			
			q.setId(5);
			
			qr.delete(q);	
			
		}
		
		//Answer 테이블에 값을 입력 : Question 테이블을 참좀해서 값을 넣어야 한다.  
//		@Test
		void insertAnswer() {
			
			Question q = new Question();
			q.setId(1);
			
			Answer a = new Answer(); 
			
			a.setContent("오늘은 날씨가 매우 맑습니다");
			a.setCreateDate(LocalDateTime.now());
			a.setQuestion(q);
			
			ar.save(a); 	
			
		}
		
//		@Test
		void insertAnswer2() {
			Question q = new Question(); 
			q.setId(2);
			
			Answer a = new Answer(); 
			a.setContent("스프링 부트 질문의 답변 1");
			a.setCreateDate(LocalDateTime.now());
			a.setQuestion(q);
			
			ar.save(a); 
			
			Answer aa = new Answer(); 
			aa.setContent("스프링 부트 질문의 답변 2");
			aa.setCreateDate(LocalDateTime.now());
			aa.setQuestion(q);
			
			ar.save(aa); 
			
		}
		
//		@Test
		void insertAnswer3() {
			Question q = new Question(); 
			q.setId(3);
			
			Answer a = new Answer(); 
			a.setContent("야구");
			a.setCreateDate(LocalDateTime.now());
			a.setQuestion(q);
			
			ar.save(a); 
			
			Answer aa = new Answer(); 
			aa.setContent("축구");
			aa.setCreateDate(LocalDateTime.now());
			aa.setQuestion(q);
			
			ar.save(aa); 
			
			Answer aaa = new Answer(); 
			aaa.setContent("농구");
			aaa.setCreateDate(LocalDateTime.now());
			aaa.setQuestion(q);
			
			ar.save(aaa); 
				
		}
		
		//제목을 기준으로 오름 차순 검색 
		//@Test
		void sortSubject() {
			List<Question> all = qr.findAllByOrderBySubjectAsc(); 
			
			assertEquals(4, all.size());
			
			System.out.println("=== 제목을 기준으로 오름차순 정렬 ====");
			for (int i = 0 ; i < all.size(); i++) {
				System.out.println("== Question : " + i + " 번째 출력 ==");
				Question q = all.get(i); 
				
				System.out.println(q.getId());
				System.out.println(q.getSubject());
				System.out.println(q.getContent());
				System.out.println(q.getCreateDate());		
			}
					
			System.out.println("=== 제목을 기준으로 오름차순 정렬 ====");
		}
		
		//제목을 기준으로 오름 차순 검색 
//		@Test
		void sortSubjectDesc() {
			List<Question> all = qr.findAllByOrderBySubjectDesc(); 
			
			assertEquals(4, all.size());
			
			System.out.println("=== 제목을 기준으로 내림차순 정렬 ====");
			for (int i = 0 ; i < all.size(); i++) {
				System.out.println("== Question : " + i + " 번째 출력 ==");
				Question q = all.get(i); 
				
				System.out.println(q.getId());
				System.out.println(q.getSubject());
				System.out.println(q.getContent());
				System.out.println(q.getCreateDate());		
			}
					
			System.out.println("=== 제목을 기준으로 내림차순 정렬 ====");
		}
//		@Test
		void searchDateAsc() {
			List<Question> all = qr.findAllByOrderByCreateDateAsc();
			
			// List의 Question 객체를 끄집어 내서 콘솔에 출력
			System.out.println("==== CreateDate 컬럼을 오름 차순 정렬 후 출력<시작> ======");
			for ( int i = 0 ; i < all.size() ; i++) {
				System.out.println("======== Question : " + i + " =============");
				
				Question q = all.get(i);
				
				System.out.println(q.getId());
				System.out.println(q.getCreateDate());
				System.out.println(q.getSubject());
				System.out.println(q.getContent());
			}
			System.out.println("==== CreateDate 컬럼을 오름 차순 정렬 후 출력<끝> ======");
			
			
		}
//		@Test
		void searchDateDesc() {
			List<Question> all = qr.findAllByOrderByCreateDateDesc();
			
			// List의 Question 객체를 끄집어 내서 콘솔에 출력
			System.out.println("==== CreateDate 컬럼을 오름 차순 정렬 후 출력<시작> ======");
			for ( int i = 0 ; i < all.size() ; i++) {
				System.out.println("======== Question : " + i + " =============");
				
				Question q = all.get(i);
				
				System.out.println(q.getId());
				System.out.println(q.getCreateDate());
				System.out.println(q.getSubject());
				System.out.println(q.getContent());
			}
			System.out.println("==== CreateDate 컬럼을 오름 차순 정렬 후 출력<끝> ======");
			
			
		}
		
		//데이터 입력(save()) : Question 테이블의 값 입력
		// Entity 클래스의 Primary Key 컬럼의 값이 비어 있으면 : insert
//		@Test
		void insertQuestion4 () {
			// Question Entity 클래스에 setter 주입으로 필드에 값을 할당. id 값을 넣지 않음.
			Question q = new Question();
			q.setSubject("제목 = 스프링 부트에 대한 질문입니다.333");
			q.setContent("내용 - 스프링 부트에 대한 내용입니다.333");
			q.setCreateDate(LocalDateTime.now());
			
			qr.save(q);
		}
		
		//데이터 수정(save()) : Question 테이블의 특정 필드 수정
		// Entity 클래스의 Primary Key 컬럼의 값이 비어 있으면 : update
//		@Test
		void modifyQuestion3 () {
			// 기존의 Question 테이블의 레코드를 불러와서, Setter로 수정후 save();
			Optional<Question> oq = qr.findById(6);
			
			if(oq.isPresent()) {
				Question q = oq.get();
				
				q.setSubject("제목만 수정됨 ---");
				
				qr.save(q);
			}
		}
		
		//삭제 : delete(), question Entity 클래스의 id 필드의 값을 setter 주입후 삭제
//		@Test
		void deleteQuestion33() {
			Question q = new Question ();
			q.setId(4);
			
			qr.delete(q);
		
		}
		
		//Answer의 값 insert  : 자식 테이블, 부모 테이블의 값을 참조해서 값을 넣어야 함.
//		@Test
		void insertAnswer33() {
			// Question 객체를 가지고 온다.
			Optional <Question> oq = qr.findById(6);
			
			if(oq.isPresent()) {
				Question q = oq.get();
				
				//Answer 객체 생성
				Answer a = new Answer();
				
				a.setContent("6번 질문에 대한 답변 글입니다.");
				a.setCreateDate(LocalDateTime.now());
				a.setQuestion(q);
				
				ar.save(a);
				
			}
		}
		//Answer의 값을 수정 : save(), 기존의 레코드를 가져와서 수정
//		@Test
		void updateAnswer33() {
			Optional<Answer> oa = ar.findById(3);
			
			if(oa.isPresent()) {
				Answer a = oa.get();
				
				a.setContent("수정된 답변글입니다. - !!!");
				
				ar.save(a);
			}
			
		}
		
		//Answer의 값 삭제 : delete()
//		@Test
		void deletAnswer33() {
			Answer a = new Answer();
			a.setId(7);
			
			ar.delete(a);
		}
		
		//질문에 대한 모든 답변글 읽어오기, Question Entity 클래스의
		// 주의 : Test로 적용할때 Transaction이 분리 되어서 동일한 트랜잭션에서 처리되도록 설정
		//@Transactional : 메소드 위에 할당. 메소드 내부의 여러 SQL 쿼리를 하나의 트랜잭션으로 처리하도록 함.
		
//		@Transactional
//		@Test
		void allAnswerList() {
			// Question 객체의 id = 2을 읽어 온다.
			Optional<Question> oq = qr.findById(2);
			
			if(oq.isPresent()) {
				Question q = oq.get();
				
				//Question answerList의 값을 getter를 사용해서 가지고 온다.
				//all에는 question id 2에 대한 답변 레코드(Answer)가 들어가 있음.
				List<Answer> all = q.getAnswerList();
				
				assertEquals (2, all.size());
				
				System.out.println("==== 3번 글에대한 답변을 모두 출력 <시작>=======");
				
				for(int i = 0 ; i < all.size() ; i++) {
					System.out.println("== Answer : " + i + "======");
					Answer a = all.get(i);
					System.out.println(a.getId());
					System.out.println(a.getQuestion());
					System.out.println(a.getContent());
					System.out.println(a.getCreateDate());
				}
				
				System.out.println("==== 3번 글에대한 답변을 모두 출력 <끝>=======");
			}
		}
		// 질문에 대한 답변글 리스트 출력 : question id 2번 글에 대한 답변 List를 출력
		@Transactional
		@Test
		void allAnswerList2() {
			Optional <Question> oq = qr.findById(2);
			
			if(oq.isPresent()) {
				Question q = oq.get();
				
				List<Answer> all = q.getAnswerList();
				
				assertEquals(2, all.size());
				
				System.out.println("====== Answer 객체 출력 <시작> ========");
				for(int i = 0 ; i < all.size() ; i++) {
					System.out.println("=== Answer : " + i + "=======");
					Answer a = all.get(i);
					
					System.out.println(a.getId());
					System.out.println(a.getContent());
					System.out.println(a.getCreateDate());
					
				}
				
				System.out.println("====== Answer 객체 출력 <시작> ========");
				
				
				
			}
			
		}
		// Question 테이블에 값 넣기 / Answer 테이블 답변글 3개 넣기
		@Test
		void insertQuestion11() {
			
			Question q = new Question();
			q.setSubject("질문 - 제목 : Framework이 무엇인가요?");
			q.setContent("질문 - 내용 : Framework이란 구체적으로 무엇인가요?");
			q.setCreateDate(LocalDateTime.now());
			
			qr.save(q);
			
		}
		
		@Test
		void insertAnswer11() {
			Optional<Question> oq = qr.findById(8);
			if(oq.isPresent()) {
				Question q = oq.get();
				
				Answer a = new Answer();
				a.setContent("구현된 기초(템플릿) - AOP, Ioc/DI, PSA");
				a.setCreateDate(LocalDateTime.now());
				a.setQuestion(q);
				
				ar.save(a);
				
				Answer aa = new Answer();
				aa.setContent("22. 구현된 기초(템플릿) - AOP, Ioc/DI, PSA");
				aa.setCreateDate(LocalDateTime.now());
				aa.setQuestion(q);
				
				ar.save(aa);
				
				Answer aaa = new Answer();
				aaa.setContent("구현된 기초(템플릿) - AOP, Ioc/DI, PSA");
				aaa.setCreateDate(LocalDateTime.now());
				aaa.setQuestion(q);
				
				ar.save(aaa);
			}
			
		}
}