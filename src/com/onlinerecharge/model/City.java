package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class City {

	@Column(name = "city_id")
	private long city_id;

	@Column(name = "city_name")
	private String city_name;

	public long getCity_id() {
		return city_id;
	}

	public void setCity_id(long city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	@Override
	public String toString() {
		return "City [city_id=" + city_id + ", city_name=" + city_name + "]";
	}

}
