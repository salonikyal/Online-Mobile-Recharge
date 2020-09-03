package com.onlinerecharge.jsf_bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.event.AjaxBehaviorEvent;

import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.RechargeDAO;
import com.onlinerecharge.dao.WalletDAO;
import com.onlinerecharge.model.City;
import com.onlinerecharge.model.Plan;
import com.onlinerecharge.model.Scheme;
import com.onlinerecharge.model.Vendor;
import com.onlinerecharge.model.Wallet;
import com.onlinerecharge.utility.SendMail;

@ManagedBean
@ViewScoped
public class RechargeBean {

	private List<Vendor> vendorList;
	private List<Scheme> schemeList;
	private List<City> cityList;
	private List<Plan> planList;

	private Wallet wallet;
	private Vendor vendor;
	private Scheme scheme;
	private City city;
	private Plan plan;

	private long phno;
	private double wallet_amount;
	private double plan_amount;
	private String plan_validity;
	private String email;

	private UIInput vendor_id;
	private UIInput scheme_id;
	private UIInput city_id;
	private UIInput plan_id;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	public List<Scheme> getSchemeList() {
		return schemeList;
	}

	public void setSchemeList(List<Scheme> schemeList) {
		this.schemeList = schemeList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public List<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public long getPhno() {
		return phno;
	}

	public void setPhno(long phno) {
		this.phno = phno;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public double getPlan_amount() {
		return plan_amount;
	}

	public void setPlan_amount(double plan_amount) {
		this.plan_amount = plan_amount;
	}

	public String getPlan_validity() {
		return plan_validity;
	}

	public void setPlan_validity(String plan_validity) {
		this.plan_validity = plan_validity;
	}

	public double getWallet_amount() {
		return wallet_amount;
	}

	public void setWallet_amount(double wallet_amount) {
		this.wallet_amount = wallet_amount;
	}

	RechargeDAO rechargeDAO = null;
	private WalletDAO walletDAO = null;

	@PostConstruct
	public void init() {
		try {

			long userId = loginBean.getLoggedinUser().getUser_id();

			rechargeDAO = new RechargeDAO();
			wallet = rechargeDAO.getWalletDetails(userId);
			
			//To extract citylist
			cityList = rechargeDAO.getCityList();
			System.out.println("cityList >>> " + cityList);
			
			vendor = new Vendor();
			city = new City();
			scheme = new Scheme();
			plan = new Plan();
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		walletDAO = new WalletDAO();
		try {
			wallet_amount = walletDAO.getWalletAmount(loginBean
					.getLoggedinUser().getUser_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param event
	 * Shows list of vendor for only a selected city
	 */
	public void fetchVendorListForCity(AjaxBehaviorEvent event) {
		try {
			System.out.println("called 1");
			if (event != null) {
				city_id = (UIInput) event.getSource();
				city.setCity_id((Long) city_id.getValue());
				vendorList = rechargeDAO.getVendorList(city.getCity_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param event
	 * Shows list of scheme for only a selected vendor
	 */
	public void fetchSchemeListForVendor(AjaxBehaviorEvent event) {
		try {
			System.out.println("called 2");
			if (event != null) {
				vendor_id = (UIInput) event.getSource();
				vendor.setVendor_id((Long) vendor_id.getValue());
				schemeList = rechargeDAO.getSchemeList(vendor.getVendor_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param event
	 *  Shows list of plans for only a selected scheme
	 */
	public void fetchPlanListForScheme(AjaxBehaviorEvent event) {
		try {
			System.out.println("called 3");
			if (event != null) {
				scheme_id = (UIInput) event.getSource();
				scheme.setScheme_id((Long) scheme_id.getValue());
				planList = rechargeDAO.getPlanList(scheme.getScheme_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To fetch Plan amount and validity
	 * @param event
	 */
	public void fetchRechargeAmountnValidity(AjaxBehaviorEvent event) {
		try {
			System.out.println("call validity and amount");
			if (event != null) {
				plan_id = (UIInput) event.getSource();
				plan.setPlan_id((Long) (plan_id.getValue()));
				for (Plan tmpPlan : planList) {
					if (tmpPlan.getPlan_id() == plan.getPlan_id()) {
						plan = new Plan(tmpPlan);
						
						//Fetching plan amount
						plan_amount = plan.getPlan_amount();
						System.out.println("amount= --> " + plan_amount);
						
						//Fetching plan validity
						plan_validity = plan.getPlan_validity();
						System.out.println("Validity= --> " + plan_validity);
						
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recharge transaction
	 */
	public void recharge() {
		boolean flg = false;
		boolean flg2 = false;
		StringBuilder msgSB = null;
		double tempPlanAmount = 0.0;

		StringBuilder sbSubject = null;
		email = loginBean.getLoggedinUser().getEmail_id();

		try {
			
			//Checks the validation of phone no.
			if(String.valueOf(phno).length()!=10)
			{
				Messages.addGlobalInfo(" Please Enter A Valid Mobile no. ");
			}

			else
			{
			//Checks whether wallet amount is sufficient for recharge
			if (plan_amount > wallet_amount) {
				Messages.addGlobalInfo(" Your Wallet Balance Is Very Low Please Add Money To Wallet Soon . . . ");
			} 
			else {
				rechargeDAO = new RechargeDAO();
				
				//Saves the recharge transaction in recharge history
				flg = rechargeDAO.saveRechargeinRecahrgeHistory(vendor.getVendor_id(), scheme.getScheme_id(),
						plan.getPlan_id(), city.getCity_id(), loginBean.getLoggedinUser().getUser_id());
				
				if (flg) {
					
					//Updates the wallet amount after recharge
					tempPlanAmount = wallet_amount - plan_amount;
					System.out.println(" After Recharge Balance Amount --> "
							+ tempPlanAmount);
					flg2 = walletDAO.updateWalletMoneyAfterRecharge(loginBean
							.getLoggedinUser().getUser_id(), tempPlanAmount);
					if (flg2) {

						wallet_amount = walletDAO.getWalletAmount(loginBean
								.getLoggedinUser().getUser_id());

						//Subject and body of the mail send to the user with the
						// recharge amount and phone no. in which recharge is done
						sbSubject = new StringBuilder(
								"Online Mobile Recharge: ")
								.append("New Recharge");
						msgSB = new StringBuilder("Your Recharge of Rs.")
								.append(String.valueOf(plan_amount))
								.append("for Phone No: +91")
								.append(String.valueOf(phno))
								.append(" is Successful.");
						SendMail.sendMessage(email, sbSubject.toString(),
								msgSB.toString());

						Messages.addGlobalInfo("Recharge successful!");

					} else {

						Messages.addGlobalInfo(" Recharge Failed . . . Please Try Again Later . . . ");
					}
				} else {
					Messages.addGlobalInfo("Something went wrong! Please try again later.");
				}
			}
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
