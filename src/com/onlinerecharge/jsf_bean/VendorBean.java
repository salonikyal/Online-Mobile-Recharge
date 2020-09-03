package com.onlinerecharge.jsf_bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.VendorDAO;
import com.onlinerecharge.model.Vendor;

@ManagedBean
@RequestScoped
public class VendorBean {

	private Vendor vendor;

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	VendorDAO vendordao;

	@PostConstruct
	public void init() {
		vendor = new Vendor();
	}

	/**
	 * Adds a new vendor
	 */
	public void addVendor() {
		boolean flg = false;

		try {
			if (vendor != null
					&& StringUtils.isNotBlank(vendor.getVendor_name())) {
				vendordao = new VendorDAO();
				flg = vendordao.saveVendor(vendor);
				if (flg) {
					vendor = new Vendor();
					Messages.addGlobalInfo("New vendor has been added!");
				} else {
					Messages.addGlobalInfo("Failed to add new vendor! Please try again later.");
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			vendordao = null;
		}
	}

}
