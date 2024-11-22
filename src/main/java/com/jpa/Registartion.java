package com.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Registartion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reg_id;

	private long user_id;
	private long cource_id;

	private String createdDate;
	private String createdTime;

	public Registartion(int reg_id, long user_id, long cource_id, String createdDate, String createdTime) {
		super();
		this.reg_id = reg_id;
		this.user_id = user_id;
		this.cource_id = cource_id;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
	}

	public Registartion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReg_id() {
		return reg_id;
	}

	public void setReg_id(int reg_id) {
		this.reg_id = reg_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getCource_id() {
		return cource_id;
	}

	public void setCource_id(long cource_id) {
		this.cource_id = cource_id;
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
}
