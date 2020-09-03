package com.onlinerecharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User_Wallet_map {

	@Column(name = "uwm_id")
	private long uwm_id;

	@Column(name = "user_id")
	private long user_id;

	@Column(name = "wallet_id")
	private long wallet_id;

	public long getUwm_id() {
		return uwm_id;
	}

	public void setUwm_id(long uwm_id) {
		this.uwm_id = uwm_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(long wallet_id) {
		this.wallet_id = wallet_id;
	}

	@Override
	public String toString() {
		return "User_Wallet_map [uwm_id=" + uwm_id + ", user_id=" + user_id
				+ ", wallet_id=" + wallet_id + "]";
	}

}
