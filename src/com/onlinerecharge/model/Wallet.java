package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Wallet {

	@Column(name = "wallet_id")
	private long wallet_id;

	@Column(name = "wallet_amount")
	private double wallet_amount;

	public long getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(long wallet_id) {
		this.wallet_id = wallet_id;
	}

	public double getWallet_amount() {
		return wallet_amount;
	}

	public void setWallet_amount(double wallet_amount) {
		this.wallet_amount = wallet_amount;
	}

	@Override
	public String toString() {
		return "Wallet_details [wallet_id=" + wallet_id + ", wallet_amount="
				+ wallet_amount + "]";
	}

}
