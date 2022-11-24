package com.registerUser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.registerUser.entity.User;

@Service
public interface UserService {

public User saveUser(User user);
	
}