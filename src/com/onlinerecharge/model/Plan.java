package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Plan {

	@Column(name = "plan_id")
	private long plan_id;

	@Column(name = "plan_name")
	private String plan_name;

	@Column(name = "plan_amount")
	private double plan_amount;

	@Column(name = "plan_validity")
	private String plan_validity;

	/**
	 * Parameterized constructor
	 * 
	 * @param plan
	 */
	public Plan(Plan plan) {
		this.plan_id = plan.plan_id;
		this.plan_name = plan.plan_name;
		this.plan_amount = plan.plan_amount;
		this.plan_validity = plan.plan_validity;
	}

	/**
	 * Default constructor
	 */
	public Plan() {
	}

	public long getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(long plan_id) {
		this.plan_id = plan_id;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public double getPlan_amount() {
		return plan_amount;
	}

	public void setPlan_amount(double plan_amount) {
		this.plan_amount = plan_amount;
	}

	public String getPlan_validity() {
		return plan_validity;
	}

	public void setPlan_validity(String plan_validity) {
		this.plan_validity = plan_validity;
	}

	@Override
	public String toString() {
		return "Plan [plan_id=" + plan_id + ", plan_name=" + plan_name
				+ ", plan_amount=" + plan_amount + ", plan_validity="
				+ plan_validity + "]";
	}

}
