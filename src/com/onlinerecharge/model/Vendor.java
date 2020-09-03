package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Vendor {

	@Column(name = "vendor_id")
	private long vendor_id;

	@Column(name = "vendor_name")
	private String vendor_name;

	public long getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(long vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	@Override
	public String toString() {
		return "Vendor [vendor_id=" + vendor_id + ", vendor_name="
				+ vendor_name + "]";
	}

}
