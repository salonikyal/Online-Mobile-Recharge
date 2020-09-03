package com.onlinerecharge.jsf_bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.UserDAO;
import com.onlinerecharge.model.User;
import com.onlinerecharge.utility.PasswordHashing;

@ManagedBean
@RequestScoped
public class UserBean {
	private User user;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	UserDAO userDAO;

	@PostConstruct
	public void init() {
		user = new User();
	}

	/**
	 * User Registration
	 */
	public void register() {
		boolean flg = false;
		String hashPassword = null;
		try {
			if (user != null
					&& StringUtils.isNotBlank(user.getUser_firstname())
					&& StringUtils.isNotBlank(user.getEmail_id())
					&& StringUtils.isNotBlank(user.getPassword())) {
				
				//Encrypting the password
				hashPassword = PasswordHashing.getEncryptedPassword(user
						.getPassword());
				user.setPassword(hashPassword);
				userDAO = new UserDAO();
				
				//Saving the user details in database
				flg = userDAO.saveUserDetails(user);
				if (flg) {
					user = new User();
					Messages.addGlobalInfo("Registration Successful!");
				} else {
					Messages.addGlobalInfo("Registration Failed! Please try again later.");
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			userDAO = null;
		}
	}

	
}
