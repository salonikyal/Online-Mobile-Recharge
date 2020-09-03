package com.onlinerecharge.jsf_bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.UserDAO;
import com.onlinerecharge.utility.PasswordHashing;

@ManagedBean
@RequestScoped
public class ResetPasswordBean {

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * To reset the password of the user
	 */
	public void editPassword() {
		if (StringUtils.isNotBlank(oldPassword)
				&& StringUtils.isNotBlank(newPassword)
				&& StringUtils.isNotBlank(confirmPassword)) {
			
			//Checks whether the twice entered new password matches
			if (newPassword.equals(confirmPassword)) {
				String oldPasswd = null;
				String newPasswd = null;
				UserDAO userDAO = null;
				boolean resetFlg = false;
				try {
					
					//Old password is encrypted
					oldPasswd = PasswordHashing
							.getEncryptedPassword(oldPassword);
					
					//New password is encrypted
					newPasswd = PasswordHashing
							.getEncryptedPassword(newPassword);
					
					if (loginBean != null
							&& loginBean.getLoggedinUser() != null
							&& StringUtils.isNotBlank(loginBean
									.getLoggedinUser().getPassword())) {
						
						//Checks whether the entered old password matches with the original old passowrd
						if (loginBean.getLoggedinUser().getPassword()
								.equals(oldPasswd)) {
							loginBean.getLoggedinUser().setPassword(newPasswd);
							userDAO = new UserDAO();
							
							//Encrypted new password is updated in the database
							resetFlg = userDAO.updateUserPassword(loginBean
									.getLoggedinUser());
							if (resetFlg) {
								Messages.addGlobalInfo("Your password changed successfully");
							} else {
								Messages.addGlobalInfo("Something went wrong, Please try again later");
							}
						} else {
							Messages.addGlobalInfo("Old password did not matched");
						}
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				} finally {
					oldPasswd = null;
					newPasswd = null;
					userDAO = null;
				}
			} else {
				Messages.addGlobalInfo("Please confirm your new password");
			}
		}
	}

}
