package com.onlinerecharge.jsf_bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import com.onlinerecharge.dao.PlanDAO;
import com.onlinerecharge.model.Plan;

@ManagedBean
@RequestScoped
public class PlanBean {

	private Plan plan;

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	PlanDAO plandao;

	@PostConstruct
	public void init() {
		plan = new Plan();
	}

	/**
	 * Adds a new plan
	 */
	public void addPlan() {
		boolean flg = false;

		try {
			if (plan != null && StringUtils.isNotBlank(plan.getPlan_name())) {

				plandao = new PlanDAO();
				flg = plandao.savePlan(plan);
				if (flg) {
					plan = new Plan();
					Messages.addGlobalInfo("New plan has been added!");
				} else {
					Messages.addGlobalInfo("Failed to add new plan! Please try again later.");
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			plandao = null;
		}
	}

}
