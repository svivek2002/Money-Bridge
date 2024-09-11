package com.pay.vivek.bean.utils;

import java.sql.Timestamp;
import java.util.Random;

public class Utils {
//	Generating 11 Digits Account Number
	
	public static String generateAccountNumber() {
		long m = (int) Math.pow(10, 11); // 11 digits
		Random rnd = new Random();
		long acc = m + rnd.nextLong(9 * m);
		return String.valueOf(acc);
	}
	
//	Generating TimeStamp
	
	public static String getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return String.valueOf(timestamp);
	}
}
