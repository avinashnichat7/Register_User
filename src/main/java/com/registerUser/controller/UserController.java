package com.registerUser.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registerUser.entity.User;
import com.registerUser.exception.ResourceNotFoundException;
import com.registerUser.repository.UserRepository;
import com.registerUser.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	//@PreAuthorize("hasRole('ADMIN')")
	 @PostMapping("/save") 
	 public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
	 
	 User save = userService.saveUser(user);
	 
	 return ResponseEntity.ok().body(save); 
	 }
	

	// save user

	// Register new user with so many field along with validation

	/*
	 * @PostMapping("/add") public User createUser(@Valid @RequestBody User user) {
	 * 
	 * return this.userRepository.save(user);
	 * 
	 * }
	 */
	// Edit User base on user id
	 
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:" + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setPincode(user.getPincode());
		existingUser.setDateOfBirth(user.getDateOfBirth());
		existingUser.setJoiningDate(user.getJoiningDate());

		return this.userRepository.save(existingUser);

	}
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<User> getAllUsers() {
		return this.userRepository.findAll();

	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") long userId) {

		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:" + userId));

	}

	@GetMapping("/date")
	public List<User> getAll(@RequestParam String date) {
		return userRepository.findAll(Sort.by(Direction.ASC, date));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:" + userId));

		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/get")
	public List<User> getUserByNameORSurname(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String pincode) {

		return this.userRepository.findByFirstNameAndLastNameAndPincode(firstName, lastName, pincode);
	}

	@GetMapping("/search")
	public List<User> searchUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String pincode) {

		return this.userRepository.findByFirstnameOrLastnameOrPincode(firstName, lastName, pincode);
	}

//	// used for access with password give Autherities without Authentication for All 
//	@GetMapping("/home")
//	public String homePage() {
//		
//		return "this is Home page";
//	}
}
