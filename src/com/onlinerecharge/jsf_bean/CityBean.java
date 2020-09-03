package com.onlinerecharge.jsf_bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.CityDAO;
import com.onlinerecharge.model.City;

@ManagedBean
@RequestScoped
public class CityBean {

	private City city;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	CityDAO citydao;

	@PostConstruct
	public void init() {
		city = new City();
	}

	/**
	 * Adds a new city
	 */
	public void addCity() {
		boolean flg = false;

		try {
			if (city != null && StringUtils.isNotBlank(city.getCity_name())) {
				citydao = new CityDAO();
				flg = citydao.saveCity(city);
				if (flg) {
					city = new City();
					Messages.addGlobalInfo("New city has been added!");
				} else {
					Messages.addGlobalInfo("Failed to add new city! Please try again later.");
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			citydao = null;
		}
	}

}
