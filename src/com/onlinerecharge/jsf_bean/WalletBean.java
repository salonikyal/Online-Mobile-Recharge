package com.onlinerecharge.jsf_bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.User_Wallet_MapDAO;
import com.onlinerecharge.dao.WalletDAO;
import com.onlinerecharge.model.User_Wallet_map;
import com.onlinerecharge.model.Wallet;

@ManagedBean
@RequestScoped
@ViewScoped
public class WalletBean {

	private Wallet wallet;

	private User_Wallet_map user_Wallet_Map;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean jsfLoginBean;

	@PostConstruct
	public void init() {
		wallet = new Wallet();
		user_Wallet_Map = new User_Wallet_map();
	}

	public LoginBean getJsfLoginBean() {
		return jsfLoginBean;
	}

	public void setJsfLoginBean(LoginBean jsfLoginBean) {
		this.jsfLoginBean = jsfLoginBean;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public User_Wallet_map getUser_Wallet_Map() {
		return user_Wallet_Map;
	}

	public void setUser_Wallet_Map(User_Wallet_map user_Wallet_Map) {
		this.user_Wallet_Map = user_Wallet_Map;
	}

	private User_Wallet_MapDAO user_Wallet_MapDAO = null;
	private WalletDAO walletDAO = null;

	/**
	 * Manage Wallet i.e., add/update wallet money
	 * @return null
	 */
	public String manageWallet() {
		Wallet wallet_result;
		long temp_user_id = 0;
		if (jsfLoginBean.getLoggedinUser() != null) {
			user_Wallet_MapDAO = new User_Wallet_MapDAO();
			walletDAO = new WalletDAO();

			temp_user_id = jsfLoginBean.getLoggedinUser().getUser_id();
			try {
				
				//Checks Whether user wallet account exists or not
				wallet_result = user_Wallet_MapDAO
						.searchUserWallet(temp_user_id);
				if (wallet_result.getWallet_id() > 0) {
					
					//If Yes, then Updates Wallet Money
					wallet.setWallet_id(wallet_result.getWallet_id());
					wallet.setWallet_amount(wallet_result.getWallet_amount()
							+ wallet.getWallet_amount());
					boolean resultFlag1 = walletDAO.updateWalletMoney(wallet);
					if (resultFlag1) {
						Messages.addGlobalInfo(" Amount Added To Your Wallet A/C Successfully . . . ");
						System.out
								.println(" Amount Added To Your Wallet A/C Successfully . . . ");
					} else {
						Messages.addGlobalInfo(" Failed . . . Amount Is Not Added To Your Wallet A/C . . . ");
						System.out.println(" Failed . . . ");
					}
				} else {
					
					// Create New Wallet Account
					boolean resultFlag2 = walletDAO.addWalletMoney(wallet);

					// Find Last Inserted Wallet ID
					if (resultFlag2) {
						long temp_wallet_id2 = walletDAO.getWalletId();
						
						// Insert into User_wallet_map
						boolean resultFlag3 = user_Wallet_MapDAO
								.addUser_Wallet_Map(temp_user_id,
										temp_wallet_id2);
						if (resultFlag3) {
							Messages.addGlobalInfo(" Wallet A/C Opened Successfully . . . ");
							System.out
									.println(" User_Wallet Mapped Successfully . . . ");
						} else {
							Messages.addGlobalInfo(" Wallet A/C Opened Failed . . . Please Try Again Later . . . ");
							System.out.println(" Mapped Failed . . . ");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Messages.addGlobalInfo(" Login Required . . . ");
			return "login.xhtml?faces-redirect=true";
		}
		return null;
	}

}
