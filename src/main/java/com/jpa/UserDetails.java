package com.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	private String name;
	private String email;
	private String mobileNum;
	private String whatsAppNum;
	private String createdDate;
	private String createdTime;
	private String message;
	private String userGrade;
	private String userName;
	private String password;

	public UserDetails(Long id, String name, String email, String mobileNum, String whatsAppNum, String createdDate,
			String createdTime, String message, String userGrade, String userName, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobileNum = mobileNum;
		this.whatsAppNum = whatsAppNum;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.message = message;
		this.userGrade = userGrade;
		this.userName = userName;
		this.password = password;
	}

	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getWhatsAppNum() {
		return whatsAppNum;
	}

	public void setWhatsAppNum(String whatsAppNum) {
		this.whatsAppNum = whatsAppNum;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
