package com.onlinerecharge.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.lang3.StringUtils;

public class PasswordHashing {
	/**
	 * 
	 * @param password
	 * @return generated random password in encrypted format
	 * @throws Exception
	 */
	public static String getEncryptedPassword(String password) throws Exception {
		byte[] theTextToDigestAsBytes = null;
		MessageDigest md = null;
		byte[] digest = null;
		StringBuilder generatedPassword = null;
		try {
			theTextToDigestAsBytes = password.getBytes("8859_1");
			md = MessageDigest.getInstance("SHA");
			md.update(theTextToDigestAsBytes);
			digest = md.digest();
			generatedPassword = new StringBuilder("");
			for (byte b : digest) {
				generatedPassword.append(String.format("%02X", b & 0xff));
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			theTextToDigestAsBytes = null;
			md = null;
			digest = null;
		}
		return generatedPassword.toString();
	}

	/**
	 * 
	 * @return random newpassword in decrypted format which the user receives in mail
	 */
	public static String generateRandomPassword() {
		String newPasword = null;
		SecureRandom random = new SecureRandom();
		newPasword = new BigInteger(130, random).toString(32);
		if (StringUtils.isNotBlank(newPasword) && newPasword.length() > 11) {
			newPasword = newPasword.substring(0, 11);
		}
		return newPasword;
	}

}
