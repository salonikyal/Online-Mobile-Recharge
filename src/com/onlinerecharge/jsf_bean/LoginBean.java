package com.onlinerecharge.jsf_bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.LoginDAO;
import com.onlinerecharge.model.Login;
import com.onlinerecharge.model.User;
import com.onlinerecharge.utility.PasswordHashing;

@ManagedBean
@SessionScoped
public class LoginBean {
	private Login login;
	private User loggedinUser = null;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public User getLoggedinUser() {
		return loggedinUser;
	}

	public void setLoggedinUser(User loggedinUser) {
		this.loggedinUser = loggedinUser;
	}

	@PostConstruct
	public void init() {
		login = new Login();
	}

	/**
	 * 
	 * @return null
	 */
	public String userLogin() {
		if (login != null && StringUtils.isNotBlank(login.getUserId())
				&& StringUtils.isNotBlank(login.getPassword())) {
			String hashPassword = null;
			LoginDAO loginDAO = null;
			try {
				hashPassword = PasswordHashing.getEncryptedPassword(login
						.getPassword());
				login.setPassword(hashPassword);
				loginDAO = new LoginDAO();
				loggedinUser = loginDAO.checkUserExist(login);
				if (loggedinUser != null) {
					System.out
							.println("User log in successful " + loggedinUser);
					Faces.redirect(Faces.getRequestContextPath()
							+ "/dashboard.xhtml");
				} else {
					System.out.println("User log in failed");
					Messages.addGlobalInfo("Invalid user id/password.");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * User logout
	 */
	public void userLogout() {
		try {
			Messages.addFlashGlobalInfo("User logout successful");
			Faces.redirect(Faces.getRequestContextPath() + "/Login.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
