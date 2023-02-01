package com.registerUser.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registerUser.entity.User;
import com.registerUser.repository.UserRepository;
import com.registerUser.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	

	
	
	
	
	/*
	 * public List<User> listAll(String keywords) { if (keywords != null) { return
	 * userRepository.search(keywords); } return userRepository.findAll(); }
	 */
    }

	
	
	
	
