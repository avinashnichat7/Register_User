package com.registerUser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.registerUser.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/*
	 * @Query("SELECT p FROM User p WHERE p.firstName LIKE %?1%" +
	 * " OR p.lastName LIKE %?1%" + " OR p.pincode LIKE %?1%")
	 */
	/*
	 * @Query("SELECT p FROM User p  WHERE" +
	 * " CONCAT P.firstName, p.LastName, p.pincode)"+ "LIKE %?1%" )
	 */
	public List<User> findByFirstNameAndLastNameAndPincode(String firstName,String surname,String pincode);
	

	@Modifying
	@Query("select u from User u where u.firstName =?1 or u.lastName =?2 or u.pincode=?3")
    public List<User> findByFirstnameOrLastnameOrPincode(String firstName, String lastName ,String pincode);
	}
	
	
	
	
	
	
	
	
	
	
	
	
