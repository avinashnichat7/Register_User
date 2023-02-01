package com.registerUser.service;

import org.springframework.stereotype.Service;

import com.registerUser.entity.User;

@Service
public interface UserService {

public User saveUser(User user);
	


//User createUser(UserRequest userRequest);
			
}
