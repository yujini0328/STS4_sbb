package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	//AnswerRepository 는 JpaRepository 의 모든 메소드를 상속 
		// 메소드를 사용해서 Answer 테이블을 CRUD 
	// findAll()    : select * from Answer ; 
	// save (answer) : insert into Answer values (값)
	
	//findAll()		: select * from answer
	//findById()	: select * from answer where id = ?
	//save()		insert, update
	//delete()		delete
	
}
