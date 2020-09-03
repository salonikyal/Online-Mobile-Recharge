package com.onlinerecharge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.onlinerecharge.model.Login;
import com.onlinerecharge.model.User;
import com.onlinerecharge.utility.DatabaseConnector;
import com.onlinerecharge.utility.ResultSetMapper;

public class LoginDAO {

	/**
	 * 
	 * @param login
	 * @return flagresult:true or false
	 * @throws Exception
	 * Checks whether the user entered email exits or not
	 */
	public User checkUserExist(Login login) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<User> resultSetMapper = null;
		List<User> userList = null;
		User loggedinUser = null;
		try {
			sql = new StringBuilder(
					"Select user_id, user_firstname, user_lastname, ")
					.append("creation_date, email_id, password, gender, phone_no, birthday ")
					.append("from user_details where email_id = ? and password = ?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setString(1, login.getUserId());
			statement.setString(2, login.getPassword());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<User>();
				userList = resultSetMapper.mapRersultSetToObject(resultSet,
						User.class);
				if (userList != null && userList.size() > 0) {
					loggedinUser = userList.get(0);
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			resultSetMapper = null;
			userList = null;
		}
		return loggedinUser;
	}

}
