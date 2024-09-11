package com.pay.vivek.validate;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pay.vivek.bean.User;

public class ValidateUser {

	private static Matcher matcher;
	private static Pattern pattern;

	private static final String EMAIL_PATTERN = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";

//	Check Length Method
	
	public static boolean checkLength(int length, String text, boolean lengthEquals) {
		if (lengthEquals) {
			if (text != null && text.length() == length)
				return true;
			else
				return false;
		} else {
			if (text != null && text.length() > length)
				return true;

		}
		return false;
	}
	
//  Verify Email
	
	public static boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

//	Verify Pin 
	
	public static boolean validatePin(String pin, User user) {
		return pin.equals(user.getAccountPin());
	}

//	Checking if passed string is null
	
	public static boolean isNotNull(String text) {
		return text != null && text.strip().length() > 0 ? true : false;
	}
	
//	Verify Pin
	
	public static boolean validatePassword(String pass) {
		return pass != null && pass.length() > 3 ? true : false;
	}
	
//	Check if a string have space
	
	public static boolean haveSpace(String userName) {
		return userName.contains(" ");
	}
	
//	verify mobile number length
	
	public static boolean validMobile(String mobile) {
		return mobile != null && mobile.length() == 10 ? true : false;
	}
}
