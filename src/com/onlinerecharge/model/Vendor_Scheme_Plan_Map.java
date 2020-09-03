package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Vendor_Scheme_Plan_Map {

	@Column(name = "vspm_id")
	private long vspm_id;

	@Column(name = "vendor_id")
	private long vendor_id;

	@Column(name = "scheme_id")
	private long scheme_id;

	@Column(name = "plan_id")
	private long plan_id;

	@Column(name = "city_id")
	private long city_id;

	public long getVspm_id() {
		return vspm_id;
	}

	public void setVspm_id(long vspm_id) {
		this.vspm_id = vspm_id;
	}

	public long getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(long vendor_id) {
		this.vendor_id = vendor_id;
	}

	public long getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(long scheme_id) {
		this.scheme_id = scheme_id;
	}

	public long getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(long plan_id) {
		this.plan_id = plan_id;
	}

	public long getCity_id() {
		return city_id;
	}

	public void setCity_id(long city_id) {
		this.city_id = city_id;
	}

	@Override
	public String toString() {
		return "Vendor_Scheme_Plan_Map [vspm_id=" + vspm_id + ", vendor_id="
				+ vendor_id + ", scheme_id=" + scheme_id + ", plan_id="
				+ plan_id + ", city_id=" + city_id + "]";
	}

}
