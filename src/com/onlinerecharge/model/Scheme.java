package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Scheme {

	@Column(name = "scheme_id")
	private long scheme_id;

	@Column(name = "scheme_name")
	private String scheme_name;

	public long getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(long scheme_id) {
		this.scheme_id = scheme_id;
	}

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	@Override
	public String toString() {
		return "Scheme [scheme_id=" + scheme_id + ", scheme_name="
				+ scheme_name + "]";
	}

}
