package com.revature.grademanagementsystemfacultyms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.revature.grademanagementsystemfacultyms.model.User;


@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("from User u where email=:email and password=:password")
	User login(@Param("email")String email,@Param("password") String password);
}
