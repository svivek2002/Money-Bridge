package com.pay.vivek.bean;

import java.io.Serializable;

// user class is a normal java bean class which doesn't extend any class, but implements the Serializable interface
public class User implements Serializable
{

	/**
	 * serialVersionUID is added when file or data has to travel over network
	 */
	private static final long serialVersionUID = 1L;
	
//	Encapsulation -> creating private data members and using getters and setters to access them
	private String userName, email, ifscCode, bankName, mobile, accountNumber, history;
	private String accountPin, accountType;
	private double accountBalance;


	public User() {
		super();  
	}
	
	public User(String userName, String email, String ifscCode, String bankName, String mobile, String accountNumber,
		String history, String accountPin, double accountBalance, String accountType) {
	super();
	this.userName = userName;
	this.email = email;
	this.ifscCode = ifscCode;
	this.bankName = bankName;
	this.mobile = mobile;
	this.accountNumber = accountNumber;
	this.history = history;
	this.accountPin = accountPin;
	this.accountBalance = accountBalance;
	this.accountType = accountType;
}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getAccountPin() {
		return accountPin;
	}
	public void setAccountPin(String pin) {
		this.accountPin = pin;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountType() {
		return this.accountType;
	}
	public void setAccountType(String accType) {
		this.accountType = accType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", ifscCode=" + ifscCode + ", bankName=" + bankName
				+ ", mobile=" + mobile + ", accountNumber=" + accountNumber + ", history=" + history + ", accountPin="
				+ accountPin + ", accountBalance=" + accountBalance + "]";
	}

	
}
