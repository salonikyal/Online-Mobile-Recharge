package com.onlinerecharge.jsf_bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.ForgetPasswordDAO;
import com.onlinerecharge.utility.PasswordHashing;
import com.onlinerecharge.utility.SendMail;

@ManagedBean
@RequestScoped
public class ForgetPasswordBean {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Checks whether the entered email exists or not
	 */
	public void checkEmailExist() {
		if (StringUtils.isNotBlank(email)) {
			boolean flg = false;
			ForgetPasswordDAO forgetPasswordDAO = null;
			String randomPasswd = null;
			StringBuilder sbSubject = null;
			StringBuilder sbBody = null;
			try {
				forgetPasswordDAO = new ForgetPasswordDAO();

				// Checks the email_id
				flg = forgetPasswordDAO.checkUserExist(email);
				if (flg) {

					// Generates a random new password
					randomPasswd = PasswordHashing.generateRandomPassword();

					// Subject and body of the mail send to the user with the
					// new generated password
					sbSubject = new StringBuilder("Online Mobile Recharge: ")
							.append("Generated New Password");
					sbBody = new StringBuilder("Hello, \n\n").append(
							"Your new system generated password is: ").append(
							randomPasswd);
					SendMail.sendMessage(email, sbSubject.toString(),
							sbBody.toString());

					// Encrypting the new randomly generated password
					randomPasswd = PasswordHashing
							.getEncryptedPassword(randomPasswd);

					// Updating the database with the new random password
					flg = forgetPasswordDAO.updateRandomPassword(email,
							randomPasswd);

					if (flg) {
						Messages.addGlobalInfo("New password is sent to your registered mail id");
					} else {
						Messages.addGlobalInfo("Please provide your registered mail id");
					}
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}
	}

}
