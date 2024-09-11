package com.pay.vivek.service;

import com.pay.vivek.bean.User;
import com.pay.vivek.transfer.InsufficientFundsException;

public interface UserService {

	public void login();

	public void createAccount();
	
	public void verifyAccount(User sender, User recipient);

	public void Logout(User user );

	public void MyAccountDetail(User user);

	public void AccountActivity();

	public void FundTransfer(int amount, User sender, User recipient) throws InsufficientFundsException;

	public void withdraw(User user );

	public void changePin(User user);
	
	public void createLog(User user, String message);
	
}
