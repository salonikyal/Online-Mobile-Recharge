package com.onlinerecharge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.onlinerecharge.utility.DatabaseConnector;

public class ForgetPasswordDAO {

	/**
	 * 
	 * @param email
	 * @return flagresult:true or false
	 * @throws Exception
	 * Checks whether email exists in the registered user's list or not
	 */
	public boolean checkUserExist(String email) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		try {
			sql = new StringBuilder(
					"Select email_id from user_details where email_id = ?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					if (resultSet.getString(1).equals(email)) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
		}
		return false;
	}

	/**
	 * 
	 * @param email
	 * @param password
	 * @return flagresult:true or false
	 * @throws Exception
	 * Updates the random generated password in the database
	 */
	public boolean updateRandomPassword(String email, String password)
			throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		int count = 0;
		boolean returnFlg = false;
		try {
			sql = new StringBuilder("Update user_details set password = ? ")
					.append("where email_id = ?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setString(1, password);
			statement.setString(2, email);
			count = statement.executeUpdate();
			if (count > 0) {
				returnFlg = true;
				System.out.println(count + " row updated...");
			} else {
				returnFlg = false;
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
		}
		return returnFlg;
	}

}
