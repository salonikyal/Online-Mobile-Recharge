package com.onlinerecharge.dao;

import java.sql.PreparedStatement;

import com.onlinerecharge.model.Scheme;
import com.onlinerecharge.utility.DatabaseConnector;

public class SchemeDAO {

	/**
	 * 
	 * @param scheme
	 * @return flagresult: true or false
	 * @throws Exception
	 * Inserts a new scheme
	 */
	public boolean saveScheme(Scheme scheme) throws Exception {

		PreparedStatement statement = null;
		String sql = null;
		int count = 0;
		boolean returnFlg = false;
		try {
			sql = "insert into scheme_details(scheme_name) value(?)";
			statement = DatabaseConnector.getConnection().prepareStatement(sql);
			statement.setString(1, scheme.getScheme_name());

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

		}
		return returnFlg;
	}

}
