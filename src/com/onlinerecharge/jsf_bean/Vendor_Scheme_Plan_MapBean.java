package com.onlinerecharge.jsf_bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.Vendor_Scheme_Plan_MapDAO;
import com.onlinerecharge.model.City;
import com.onlinerecharge.model.Plan;
import com.onlinerecharge.model.Scheme;
import com.onlinerecharge.model.Vendor;
import com.onlinerecharge.model.Vendor_Scheme_Plan_Map;

@ManagedBean
@ViewScoped
public class Vendor_Scheme_Plan_MapBean {

	private List<Vendor> vendorList;
	private List<Scheme> schemeList;
	private List<City> cityList;
	private List<Plan> planList;

	private Vendor vendor;
	private Scheme scheme;
	private City city;
	private Plan plan;

	private Vendor_Scheme_Plan_Map vspm;

	public Vendor_Scheme_Plan_Map getVspm() {
		return vspm;
	}

	public void setVspm(Vendor_Scheme_Plan_Map vspm) {
		this.vspm = vspm;
	}

	public Vendor_Scheme_Plan_MapDAO getVspmDAO() {
		return vspmDAO;
	}

	public void setVspmDAO(Vendor_Scheme_Plan_MapDAO vspmDAO) {
		this.vspmDAO = vspmDAO;
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

	Vendor_Scheme_Plan_MapDAO vspmDAO = null;

	@PostConstruct
	public void init() {
		try {

			vspmDAO = new Vendor_Scheme_Plan_MapDAO();

			vendorList = Vendor_Scheme_Plan_MapDAO.getVendorList();
			System.out.println("vendorList >>> " + vendorList);
			
			cityList = Vendor_Scheme_Plan_MapDAO.getCityList();
			System.out.println("cityList >>> " + cityList);
			
			planList = Vendor_Scheme_Plan_MapDAO.getPlanList();
			System.out.println("planList >>> " + planList);
			
			schemeList = Vendor_Scheme_Plan_MapDAO.getSchemeList();
			System.out.println("schemeList >>> " + schemeList);
			
			vendor = new Vendor();
			city = new City();
			scheme = new Scheme();
			plan = new Plan();
			
			vspm = new Vendor_Scheme_Plan_Map();
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Adds a new Vendor_Scheme_Plan mapping
	 */
	public void addVendor_Scheme_Plan_Map() {
		boolean flg = false;

		try {

			vspmDAO = new Vendor_Scheme_Plan_MapDAO();
			flg = vspmDAO.saveVendor_Scheme_Plan_Map(vspm);
			if (flg) {
				vspm = new Vendor_Scheme_Plan_Map();
				Messages.addGlobalInfo("New mapping has been added!");
			} else {
				Messages.addGlobalInfo("Failed to add new mapping! Please try again later.");
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			vspmDAO = null;
		}
	}

}
