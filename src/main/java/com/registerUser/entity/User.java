package com.registerUser.entity;

import java.sql.Date;
import java.util.Objects;

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

@SQLDelete(sql = "UPDATE user SET deleted = 0 WHERE id=?")
@Where(clause = "deleted= 1")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, message = "first name must be 2 character")
	private String firstName;

	@NotNull
	@Size(min = 2, message = "first name must be 2 character")
	private String lastName;

	@NotNull
	@Size(min = 6, message = "pincode must be 6 digit")
	private String pincode;

	@DateTimeFormat
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	@DateTimeFormat
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date joiningDate;

	private boolean deleted = Boolean.FALSE;

	

}
