package com.onlinerecharge.jsf_bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;



import com.onlinerecharge.dao.SchemeDAO;
import com.onlinerecharge.model.Scheme;

@ManagedBean
@RequestScoped
public class SchemeBean {
	
	private Scheme scheme;

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}
	
	SchemeDAO schemedao;
	
	@PostConstruct
	public void init() {
		scheme = new Scheme();
	}

	/*
	 * Adds a new scheme
	 */
	public void addScheme() {
		boolean flg = false;
		
		try {
			if (scheme != null && StringUtils.isNotBlank(scheme.getScheme_name())) {
				schemedao = new SchemeDAO();
				flg = schemedao.saveScheme(scheme);
				if (flg) {
					scheme = new Scheme();
					Messages.addGlobalInfo("New scheme has been added!");
				} else {
					Messages.addGlobalInfo("Failed to add new scheme! Please try again later.");
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			schemedao = null;
		}
	}


}
