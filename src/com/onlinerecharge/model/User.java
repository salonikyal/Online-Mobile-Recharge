package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User {

	@Column(name = "user_id")
	private long user_id;

	@Column(name = "user_firstname")
	private String user_firstname;

	@Column(name = "user_lastname")
	private String user_lastname;

	@Column(name = "email_id")
	private String email_id;

	@Column(name = "password")
	private String password;

	@Column(name = "gender")
	private String gender;

	@Column(name = "phone_no")
	private long phone_no;

	/**
	 * For birthday
	 */
	private String year;
	private String month;
	private String day;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_firstname() {
		return user_firstname;
	}

	public void setUser_firstname(String user_firstname) {
		this.user_firstname = user_firstname;
	}

	public String getUser_lastname() {
		return user_lastname;
	}

	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(long phoneNo) {
		this.phone_no = phoneNo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_firstname="
				+ user_firstname + ", user_lastname=" + user_lastname
				+ ", email_id=" + email_id + ", password=" + password
				+ ", gender=" + gender + ", phoneNo=" + phone_no + ", year="
				+ year + ", month=" + month + ", day=" + day + "]";
	}

	public void show() {
		System.out.println(user_firstname);
		System.out.println(user_lastname);
		System.out.println(email_id);
		System.out.println(password);
		System.out.println(gender);
		System.out.println(phone_no);

		System.out.println(year);
		System.out.println(month);
		System.out.println(day);

	}

}
