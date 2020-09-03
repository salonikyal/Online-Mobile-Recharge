package com.onlinerecharge.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

	/**
	 * Checks whether the password length is greater than 8 & other validations
	 */
	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object object) throws ValidatorException {
		String passwd = object.toString();
		if (passwd.length() < 8) {
			FacesMessage msg = new FacesMessage("Password validation failed.",
					"Password length should be 8 or greater");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		} else {
			boolean alphaFlg = isMinAlphanumeric(passwd, 1);
			boolean digitFlg = isMinNumeric(passwd, 1);

			if (!alphaFlg) {
				FacesMessage msg = new FacesMessage(
						"Password validation failed.",
						"Password should contain 1 Uppercase letter");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
			if (!digitFlg) {
				FacesMessage msg = new FacesMessage(
						"Password validation failed.",
						"Password should contain 1 numeric value");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}
	}

	/**
	 * 
	 * @param strToTest
	 * @param minLen
	 * @return True OR False
	 * Checks whether the password contains a numeric digit
	 */
	private static boolean isMinNumeric(String strToTest, int minLen) {
		int countDigit = 0;
		int strLen;
		char currStr;
		if (StringUtils.isNotBlank(strToTest)) {
			strLen = strToTest.length();

			for (int i = 0; i < strLen; i++) {
				currStr = strToTest.charAt(i);
				if (Character.isDigit(currStr)) {
					countDigit++;
				}
			}
		}
		return countDigit >= minLen ? true : false;
	}

	/**
	 * 
	 * @param strToTest
	 * @param minLen
	 * @return True or False
	 * Checks whether the password contains a capital letter alphabet
	 */
	private static boolean isMinAlphanumeric(String strToTest, int minLen) {
		int countAlpha = 0;
		int strLen;
		char currStr;
		if (StringUtils.isNotBlank(strToTest)) {
			strLen = strToTest.length();

			for (int i = 0; i < strLen; i++) {
				currStr = strToTest.charAt(i);
				if (Character.isUpperCase(currStr)) {
					countAlpha++;
				}
			}
		}
		return countAlpha >= minLen ? true : false;
	}
}
