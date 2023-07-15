package com.cognizant.training.bloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.training.bloodBank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

//	@Query(value = "SELECT *  FROM user WHERE email = :email", nativeQuery = true)
	public User findByEmailAndPassword(String email, String password);

}
