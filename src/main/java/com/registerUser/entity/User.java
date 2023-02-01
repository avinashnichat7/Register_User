package com.registerUser.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table

@SQLDelete(sql = "UPDATE user SET deleted = false WHERE id=?")
@Where(clause = "deleted= true")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 2, message = "first name must be 2 character")
	private String firstName;

	@NotNull
	@Size(min = 2, message = "last name must be 2 character")
	private String lastName;

	@NotNull
	@Size(min = 6, message = "pincode must be 6 digit")
	private String pincode;

	@DateTimeFormat
	@JsonFormat(pattern = "yyyy-MM-dd")
	//@NotBlank(message = "Invalid date")
	private Date dateOfBirth;

	@DateTimeFormat
	@JsonFormat(pattern = "yyyy-MM-dd")
	//@NotBlank(message = "Invalid date")
	
	private Date joiningDate;

	private boolean deleted = Boolean.FALSE;

//	private String username;
//	private String password;
//	private String role;
	}
