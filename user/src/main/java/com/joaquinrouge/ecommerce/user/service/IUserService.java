package com.joaquinrouge.ecommerce.user.service;

import java.util.List;

import com.joaquinrouge.ecommerce.user.model.User;

public interface IUserService {
	List<User> getAllUsers();
	User getUserById(Long id);
	User createUser(User user);
	void deleteUser(Long id);
	User updateUser(User user);
}
