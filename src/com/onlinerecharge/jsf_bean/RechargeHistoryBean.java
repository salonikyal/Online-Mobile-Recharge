package com.onlinerecharge.jsf_bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.onlinerecharge.dao.RechargeDAO;
import com.onlinerecharge.model.Recharge_history;

@ManagedBean
@RequestScoped
public class RechargeHistoryBean {

	private List<Recharge_history> rechargeHistoryList = null;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public List<Recharge_history> getRechargeHistoryList() {
		return rechargeHistoryList;
	}

	public void setRechargeHistoryList(
			List<Recharge_history> rechargeHistoryList) {
		this.rechargeHistoryList = rechargeHistoryList;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	RechargeDAO rechargeDAO = null;

	@PostConstruct
	public void init() {
		rechargeDAO = new RechargeDAO();
		try {
			rechargeHistoryList = rechargeDAO.getRechargeHistory(loginBean
					.getLoggedinUser().getUser_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
