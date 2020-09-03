package com.onlinerecharge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.onlinerecharge.model.Wallet;
import com.onlinerecharge.utility.DatabaseConnector;

public class WalletDAO {

	/**
	 * 
	 * @param wallet
	 * @return flagresult:true or false
	 * @throws Exception
	 * Add wallet amount to new wallet account
	 */
	public boolean addWalletMoney(Wallet wallet) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		int count = 0;
		boolean resultFlag = false;

		try {
			sql = new StringBuilder(
					"insert into wallet_details(wallet_amount) values(?)");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setDouble(1, wallet.getWallet_amount());
			count = statement.executeUpdate();
			if (count > 0) {
				resultFlag = true;
				System.out.println(" Wallet Amount Added Successfully . . . ");
			} else {
				resultFlag = false;
				System.out
						.println(" Wallet Amount Is Not Able To Add To Your A/C . . . Please Try Again Later . . . ");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			statement = null;
			sql = null;
		}

		return resultFlag;

	}

	/**
	 * 
	 * @param wallet
	 * @return flagresult:true or false
	 * @throws Exception
	 * Update wallet amount in database when user adds money in wallet
	 */
	public boolean updateWalletMoney(Wallet wallet) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		int count = 0;
		boolean resultFlag = false;

		try {
			sql = new StringBuilder(
					"update wallet_details set wallet_amount=? where wallet_id=?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setDouble(1, wallet.getWallet_amount());
			statement.setLong(2, wallet.getWallet_id());
			count = statement.executeUpdate();
			if (count > 0) {
				resultFlag = true;
				System.out
						.println(" Wallet Amount Updated Successfully . . . ");
			} else {
				resultFlag = false;
				System.out
						.println(" Wallet Amount Is Not Able To Update To Your A/C . . . Please Try Again Later . . . ");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			statement = null;
			sql = null;
		}

		return resultFlag;

	}

	/**
	 * 
	 * @return wallet_id
	 * @throws Exception
	 * To get maximum wallet id of the logged in user
	 */
	public long getWalletId() throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		long temp_wallet_id = 0;

		try {
			sql = new StringBuilder(
					"select max(wallet_id) wallet_id from wallet_details");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				temp_wallet_id = resultSet.getLong("wallet_id");
				System.out.println(" Wallet_Id :: " + temp_wallet_id);
			} else {
				temp_wallet_id = 0;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
		}
		return temp_wallet_id;
	}

	/**
	 * 
	 * @param temp_user_id
	 * @return wallet amount
	 * @throws Exception
	 * Returns the current wallet amount of the user logged in
	 */
	public double getWalletAmount(long temp_user_id) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		double wallet_amount = 0.0;

		try {
			sql = new StringBuilder(
					"select w.wallet_id,w.wallet_amount from wallet_details w,user_wallet_map uwm,user_details u")
					.append(" where u.user_id=uwm.user_id and uwm.wallet_id=w.wallet_id and u.user_id=?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, temp_user_id);
			resultSet = statement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				wallet_amount = resultSet.getDouble("wallet_amount");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			
		}
		return wallet_amount;
	}

	/**
	 * 
	 * @param userid
	 * @param walletamount
	 * @return flagresult:true or false
	 * @throws Exception
	 * Updates wallet amount after the recharge transaction
	 */
	public boolean updateWalletMoneyAfterRecharge(long userid,
			double walletamount) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		int count = 0;
		boolean resultFlag = false;
		long tempWalletId = 0;

		try {
			sql = new StringBuilder(
					"select w.wallet_id from wallet_details w,user_details u,user_wallet_map uwm")
					.append(" where w.wallet_id=uwm.wallet_id and uwm.user_id=u.user_id and u.user_id=?");

			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, userid);
			resultSet = statement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				tempWalletId = resultSet.getLong("wallet_id");
				sql = new StringBuilder(
						"update wallet_details set wallet_amount=? where wallet_id=?");
				statement = DatabaseConnector.getConnection().prepareStatement(
						sql.toString());
				statement.setDouble(1, walletamount);
				statement.setLong(2, tempWalletId);
				count = statement.executeUpdate();
				if (count > 0) {
					resultFlag = true;
					System.out
							.println(" After Recharge User Wallet Amount Updated Successfully . . . ");
				} else {
					resultFlag = false;
					System.out
							.println(" After Recharge User Wallet Amount Is Not Able To Update To Your A/C . . . Please Try Again Later . . . ");
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			count = 0;
			tempWalletId = 0;
		}

		return resultFlag;

	}

}
