package com.onlinerecharge.dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;

import com.onlinerecharge.model.User;
import com.onlinerecharge.utility.DatabaseConnector;

public class UserDAO {

	/**
	 * 
	 * @param user
	 * @return flagresult:true or false
	 * @throws Exception
	 * Saves user deatails of the user currently registered
	 */
	public boolean saveUserDetails(User user) throws Exception {
		PreparedStatement statement = null;
		String sql = null;
		Timestamp timestamp = null;
		int count = 0;
		Calendar date = null;
		boolean returnFlg = false;
		try {
			date = Calendar.getInstance();
			date.set(Integer.parseInt(user.getYear()),
					Integer.parseInt(user.getMonth()) - 1,
					Integer.parseInt(user.getDay()));
			timestamp = new Timestamp(date.getTimeInMillis());
			System.out.println("calculated timestamp " + timestamp.toString());

			sql = "insert into user_details(user_firstname,user_lastname, email_id, password,gender, phone_no, modification_date, birthday) values(?,?,?,?,?,?,SYSDATE(),?)";

			statement = DatabaseConnector.getConnection().prepareStatement(sql);
			statement.setString(1, user.getUser_firstname());
			statement.setString(2, user.getUser_lastname());
			statement.setString(3, user.getEmail_id());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getGender());
			statement.setString(6, String.valueOf(user.getPhone_no()));
			statement.setTimestamp(7, timestamp);
			count = statement.executeUpdate();
			if (count > 0) {
				returnFlg = true;
				System.out.println(count + " row inserted...");
			} else {
				returnFlg = false;
			}
		} catch (Exception exception) {
			throw exception;
			
		} finally {
			statement = null;
			sql = null;
			timestamp = null;
			date = null;
		}
		return returnFlg;
	}

	/**
	 * 
	 * @param user
	 * @return flagresult:true or false
	 * @throws Exception
	 * Update the new password of the user
	 */
	public boolean updateUserPassword(User user) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		int count = 0;
		boolean returnFlg = false;
		try {
			sql = new StringBuilder("Update user_details set password = ? ")
					.append("where user_id = ?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setString(1, user.getPassword());
			statement.setLong(2, user.getUser_id());
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
