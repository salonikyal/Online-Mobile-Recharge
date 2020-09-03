package com.onlinerecharge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.onlinerecharge.model.Wallet;
import com.onlinerecharge.utility.DatabaseConnector;

public class User_Wallet_MapDAO {

	/**
	 * 
	 * @param temp_user_id
	 * @param temp_wallet_id
	 * @return flagresut : true or false
	 * @throws Exception
	 * Inserts user_id and wallet_id in user_wallet_map
	 */
	public boolean addUser_Wallet_Map(long temp_user_id, long temp_wallet_id)
			throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		int count = 0;
		boolean resultFlag = false;

		try {
			sql = new StringBuilder(
					"insert into user_wallet_map(user_id,wallet_id) values(?,?)");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, temp_user_id);
			statement.setLong(2, temp_wallet_id);
			count = statement.executeUpdate();
			if (count > 0) {
				resultFlag = true;
				System.out.println(" User_Wallet_Mapped Successfully . . . ");
			} else {
				resultFlag = false;
				System.out
						.println(" User_Wallet_Mapped Failed . . . Please Try Again Later . . . ");
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
	 * @param temp_user_id
	 * @return wallet
	 * @throws Exception
	 * Checks whether user wallet exists or not
	 */
	public Wallet searchUserWallet(long temp_user_id) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		Wallet wallet = new Wallet();

		try {
			sql = new StringBuilder(
					"select uwm.user_id,uwm.wallet_id,wd.wallet_amount from user_wallet_map uwm inner join wallet_details wd on uwm.wallet_id=wd.wallet_id  where uwm.user_id=? ");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, temp_user_id);
			resultSet = statement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				wallet.setWallet_id(resultSet.getInt("wallet_id"));
				wallet.setWallet_amount(resultSet.getInt("wallet_amount"));

				System.out.println(temp_user_id);
			} else {
				wallet.setWallet_id(0);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
		}
		return wallet;

	}

}
