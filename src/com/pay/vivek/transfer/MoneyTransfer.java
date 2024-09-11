package com.pay.vivek.transfer;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.pay.vivek.bean.User;
import com.pay.vivek.bean.utils.Utils;
import com.pay.vivek.service.UserService;
import com.pay.vivek.validate.ValidateUser;

public class MoneyTransfer implements UserService {
	User first, second;
	int user, userLoggedIn;
	private String accType, bankName, name, email, mobile, ifsc, pin, accNum;
	private double amount;

	Scanner sc = new Scanner(System.in);
	{
		first = new User();
		second = new User();
	}

	public static void main(String[] args) {

		MoneyTransfer mt = new MoneyTransfer();
		mt.optionMenu();
	}

	private void optionMenu() {

		System.out.println("");

		if (userLoggedIn == 0) {
			System.out.println("1. Login");
			System.out.println("2. Create Account");
		} else {
			System.out.println("1. Logout");
			System.out.println("2. Account Details");
			System.out.println("3. Account Activity");
			System.out.println("4. Money Transfer");
			System.out.println("5. Withdraw Money");
			System.out.println("6. Change Pin");

		}

		System.out.println("");
		int choice;
		try {
		    choice = sc.nextInt();
		} catch (InputMismatchException e) {
		    System.out.println("Invalid input. Please enter a valid number.");
		    sc.nextLine(); 
		    optionMenu(); 
		    return;
		}

		switch (choice) {
		case 1:
			if (userLoggedIn == 1)
				Logout(first);
			else if (userLoggedIn == 2)
				Logout(second);
			else
				this.login();
			break;

		case 2:
			if (userLoggedIn == 1)
				MyAccountDetail(first);
			else if (userLoggedIn == 2)
				MyAccountDetail(second);
			else
				createAccount();
			break;

		case 3:
			if (userLoggedIn == 1)
				first.getHistory();
			else if (userLoggedIn == 2)
				second.getHistory();
			optionMenu();
			break;

		case 4:
			if (userLoggedIn == 1)
				this.verifyAccount(first, second);
			else if (userLoggedIn == 2)
				this.verifyAccount(second, first);
			optionMenu();
			break;

		case 5:
			if (userLoggedIn == 1)
				this.withdraw(first);
			else if (userLoggedIn == 2)
				this.withdraw(second);
			optionMenu();
			break;

		case 6:
			if (userLoggedIn == 1)
				this.changePin(first);
			else if (userLoggedIn == 2)
				this.changePin(second);
			optionMenu();
			break;

		}

	}

	@Override
	public void login() {
		System.out.println("Welcome to Bank ");
		print("Enter your bank Account Number");
		String accNum = sc.next();
		if (accNum.equalsIgnoreCase(first.getAccountNumber())) {
			print("Enter 6 digits Pin ");
			String pin = sc.next();
			int limit = 3;
			while (!ValidateUser.validatePin(pin, first)) {
				--limit;
				if (limit <= 0) {
					print("!! You have reached to maximum limit attempts !! Re-enter all the details");
					login();
				}
				print("[!! Pin is not valid.. Pin must be of 6 digits !!... Please enter again  ( ⚠ " + limit
						+ " chances left only ⚠)");
				pin = sc.next();

			}
			userLoggedIn = 1;
			print("!! Login Successful !!");
			print(first.getUserName() +", Welcome to " + first.getBankName() + " Bank " +  " ! ");
			createLog(first, "Account LoggedIn");
			optionMenu();
		} else if (accNum.equalsIgnoreCase(second.getAccountNumber())) {
			print("Enter 6 digits Pin ");
			String pin = sc.next();
			int limit = 3;
			while (!ValidateUser.validatePin(pin, second)) {
				--limit;
				if (limit <= 0) {
					print("!! You have reached to maximum limit attempts !! Re-enter all the details");
					login();
				}
				print("[!! Pin is not valid.. Pin must be of 6 digits !!... Please enter again  ( ⚠ " + limit
						+ " chances left only ⚠)");
				pin = sc.next();

			}
			userLoggedIn = 2;
			print("!! Login Successful !!");
			print(second.getUserName() + ", Welcome to " + second.getBankName() + " Bank " + " ! ");
			createLog(second, "Account LoggedIn");
			optionMenu();

		} else {
			print("!! Account Number is not valid !!");
			login();
		}

	}

	@Override
	public void createAccount() {
		if (first.getUserName() == null)
			user = 1;
		else if (second.getUserName() == null)
			user = 2;
		else {
			print("!! Oops Only 2 users can be created !!");
			optionMenu();
		}

		System.out.println("---------- Enter Details to Continue ------------");

		System.out.println("====|   Enter Bank's Name   |====");
		bankName = sc.next();
		sc.nextLine();
		if (!ValidateUser.checkLength(2, bankName, false)) {
			print("Bank's Name is not valid or Empty!!");
			createAccount();
		}

		System.out.println("====|   Enter Your Full Name   |====");
		name = sc.nextLine();
		try {
			if (!ValidateUser.checkLength(2, name, false)) {
				throw new IllegalArgumentException("Username is not valid or Empty !!");
			}
		} catch (IllegalArgumentException e) {
		    System.out.println(e.getMessage());
		    createAccount();
		}

		System.out.println("====|   Enter Your Email   |====");
		email = sc.next();
		try {
		    if (!ValidateUser.validateEmail(email)) {
		        throw new IllegalArgumentException("Invalid email format.");
		    }
		} catch (IllegalArgumentException e) {
		    System.out.println(e.getMessage());
		    createAccount();
		}


		System.out.println("====|   Enter Your 10 digits Mobile Number  |====");
		mobile = sc.next();
		while (!ValidateUser.validMobile(mobile)) {
			print("Mobile number is not valid !! Please Enter Your 10 digits Mobile Number ");
			mobile = sc.next();
		}

		print("====|   Create 11 digtis IFSC Code   |====");
		ifsc = sc.next();
		int limit = 3;
		while (!ValidateUser.checkLength(11, ifsc, true)) {
			--limit;
			if (limit <= 0) {
				print("!! You have reached to maximum limit attempts !! Re-enter all the details");
				createAccount();
			}
			print("[!! IFSC is not valid.. ifsc must be of 11 digits !!... Please enter again  ( ⚠ " + limit
					+ " chances left only ⚠)");
			ifsc = sc.next();
		}

		print("====|   Select Account Type   |====");
		print("1. Saving");
		print("2. Current");
		int accountType = sc.nextInt();
		while (accountType != 1 && accountType != 2) {
			print("!! Please enter valid account type !!");
			accountType = sc.nextInt();
		}
		if (accountType == 1) {
			accType = "Savings";
		} else {
			accType = "Current";
		}
		System.out.println("====|   Enter Amount You Want to Save   |====");
		amount = sc.nextInt();
		while (amount < 1000) {
			print("Opening Amount must be greater than ₹1000");
			amount = sc.nextInt();
		}
		print("====|   Create 6 Digits Pin   |====");
		pin = sc.next();
		limit = 3;
		while (!ValidateUser.checkLength(6, pin, true)) {
			--limit;
			if (limit <= 0) {
				print("!! You have reached to maximum limit attempts !! Re-enter all the details");
				createAccount();
			}
			print("[!! Pin is not valid.. Pin must be of 6 digits !!... Please enter again  ( ⚠ " + limit
					+ " chances left only ⚠)");
			pin = sc.next();
		}
		print("====|   Generating 11 Digits Account Number   |====");
		print("");
		accNum = Utils.generateAccountNumber();
		print("Your 11 Digits Unique Account Number is : " + accNum);
		print("Save it for future reference! ");

		detailsSetter((user == 1) ? first : second);

		this.optionMenu();
	}

	private void detailsSetter(User user) {
		user.setBankName(bankName);
		user.setUserName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setIfscCode(ifsc);
		user.setAccountPin(pin);
		user.setAccountBalance(amount);
		user.setAccountNumber(accNum);
		user.setAccountType(accType);
		user.setHistory(Utils.getTimeStamp());
		this.createLog(user, "Account Created ");
		this.MyAccountDetail(user);
	}

	private void print(String string) {
		System.out.println(string);
	}

	@Override
	public void Logout(User user) {
		userLoggedIn = 0;
		print("!! Logout Successfully !!");
		createLog(user, "Account Logout");
		optionMenu();
	}

	@Override
	public void MyAccountDetail(User user) {
		print("");
		print("-------------------****************--------------------");
		print(" ---------***[ Account Created Succesfully ]***-----------");
		print("!! Account's Detail !!");
		print("!! Account's Holder Name => " + user.getUserName());
		print("!! Account Number => " + user.getAccountNumber());
		print("!! Bank's Name => " + user.getBankName());
		print("!! Email => " + user.getEmail());
		print("!! Mobile Number => " + user.getMobile());
		print("!! Account Type => " + user.getAccountType());
		print("!! Account Balance => ₹" + user.getAccountBalance());
		print("!! IFSC => " + user.getIfscCode());
		print("!! Account Pin => " + user.getAccountPin());
		print(user.getHistory());
		optionMenu();

	}

	@Override
	public void AccountActivity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FundTransfer(int amount, User sender, User recipient) throws InsufficientFundsException{
	
		    try {
		        if (amount <= sender.getAccountBalance()) {
		            recipient.setAccountBalance(recipient.getAccountBalance() + amount);
		            sender.setAccountBalance(sender.getAccountBalance() - amount);
		            print("---------****** [ Money Transferred Successfully ] ******----------");
		            print("!! ~ Available Balance => ₹" + sender.getAccountBalance());
		            createLog(sender, amount + " transferred to " + recipient.getUserName());
		            createLog(recipient, amount + " received from " + sender.getUserName());
		        } else {
		            throw new InsufficientFundsException("Insufficient Balance.");
		        }
		    } catch (InsufficientFundsException e) {
		        print("!! " + e.getMessage() + " !!");
		    } finally {
		        optionMenu();
		    }
	}


	@Override
	public void withdraw(User user) {
	    System.out.print("!! Enter Amount to withdraw - ");
	    int amount = sc.nextInt();
	    print("!! Enter 6 digits Pin");
	    String pin = sc.next();
	    int limit = 3;

	    // Validate the pin with a limit of 3 attempts
	    while (!ValidateUser.validatePin(pin, user)) {
	        --limit;
	        if (limit <= 0) {
	            print("!! You have reached the maximum limit of attempts !! Re-enter all the details");
	            optionMenu();
	            return; 
	        }
	        print("[!! Pin is not valid.. Pin must be of 6 digits !!... Please enter again  ( ⚠ " + limit
	                + " chances left only ⚠)]");
	        pin = sc.next();
	    }

	    // Try block to handle withdrawal and insufficient balance
	    try {
	        if (amount <= user.getAccountBalance()) {
	            user.setAccountBalance(user.getAccountBalance() - amount);
	            print("---------****** [ Amount Withdrawn Successfully ] ******----------");
	            print("!! ~ Available Balance => " + user.getAccountBalance());
	            createLog(user, amount + " withdrawn from " + user.getUserName());
	        } else {
	            throw new InsufficientFundsException("Insufficient Balance.");
	        }
	    } catch (InsufficientFundsException e) {
	        print("!! " + e.getMessage() + " !!");
	    } finally {
	        optionMenu();
	    }
	}


	@Override
	public void changePin(User user) {
		print("!! Enter your existing 6 digits Pin - ");
		String oldPin = sc.next();
		while (!ValidateUser.validatePin(oldPin, user)) {
			print("!! Incorrect Pin !!");
			changePin(user);
		}

		print("Enter new 6 digits Pin - ");
		String newPin1 = sc.next();
		while (!ValidateUser.checkLength(6, newPin1, true)) {
			print("!! Pin must be of 6 digits... !! Enter Again - ");
			newPin1 = sc.next();
		}
		print("Enter Pin again - ");
		String newPin2 = sc.next();
		while (!newPin1.equals(newPin2)) {
			print("!! Pin doesn't matches !! Enter Again - ");
			newPin2 = sc.next();
		}
		user.setAccountPin(newPin2);
		createLog(user, "!! Pin Changed !!");
		print(user.getHistory());
		optionMenu();
	}

	@Override
	public void createLog(User user, String message) {
		String history = "";
		if (user.getHistory() != null) {
			history = user.getHistory();
		}
		user.setHistory(message + " on " + Utils.getTimeStamp());
	}

	@Override
	public void verifyAccount(User sender, User recipient) {
	    print("Enter recipient's Account no. ");
	    String accNum = sc.next();

	    // Check if the sender tries to send money to their own account
	    if (accNum.equalsIgnoreCase(sender.getAccountNumber())) {
	        print("!! You cannot send money into your own account !!");
	        optionMenu();
	        return; // Exit the method to prevent further execution
	    } 
	    else if (accNum.equalsIgnoreCase(recipient.getAccountNumber())) {
	        print("!! You are sending money to " + recipient.getUserName());
	        System.out.print("!! Enter Amount - ");
	        int amount = sc.nextInt();
	        print("!! Enter 6 digits Pin");
	        String pin = sc.next();
	        int limit = 3;

	        // PIN validation loop
	        while (!ValidateUser.validatePin(pin, sender)) {
	            --limit;
	            if (limit <= 0) {
	                print("!! You have reached the maximum limit of attempts !! Re-enter all the details");
	                optionMenu();
	                return; // Exit the method after reaching max attempts
	            }
	            print("[!! Pin is not valid.. Pin must be of 6 digits !!... Please enter again  ( ⚠ " + limit
	                    + " chances left only ⚠)]");
	            pin = sc.next();
	        }

	        // Exception handling for fund transfer
	        try {
	            FundTransfer(amount, sender, recipient);
	        } catch (InsufficientFundsException e) {
	            print("!! " + e.getMessage() + " !!");
	        } catch (Exception e) {
	            print("!! An unexpected error occurred: " + e.getMessage() + " !!");
	        } finally {
	            optionMenu();
	        }
	    } else {
	        print("Account doesn't exist with this account number !! ");
	        optionMenu();
	    }
	}

}
