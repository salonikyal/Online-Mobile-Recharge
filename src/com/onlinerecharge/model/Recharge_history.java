package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Recharge_history {

	@Column(name = "vendor_name")
	private String vendor_name;
	@Column(name = "city_name")
	private String city_name;
	@Column(name = "scheme_name")
	private String scheme_name;
	@Column(name = "plan_name")
	private String plan_name;

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	@Override
	public String toString() {
		return "Recharge_history [vendor_name=" + vendor_name + ", city_name="
				+ city_name + ", scheme_name=" + scheme_name + ", plan_name="
				+ plan_name + "]";
	}

}
