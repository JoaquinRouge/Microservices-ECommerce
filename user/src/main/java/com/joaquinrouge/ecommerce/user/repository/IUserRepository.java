package com.joaquinrouge.ecommerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaquinrouge.ecommerce.user.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
	
}
