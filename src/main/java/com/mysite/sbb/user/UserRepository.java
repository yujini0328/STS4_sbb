package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long> {
		//JpaRepository 에서 메소드가 상속 
		// 1. findAll() 			: 리턴 : List<SiteUser> 
		// 2. findById(정수) 			: 리턴 : SiteUser
		// 3. save () 	// insert, update 	: 리턴 : void
		// 4. delete() 						: 리턴 : void
		
	//로그인 할 사용자 정보를 DB에서 읽어 와야한다.
		//select * from SITE_USER where username = ?
	Optional<SiteUser> findByusername(String username); 
	
}