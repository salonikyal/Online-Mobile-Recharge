package com.onlinerecharge.dao;

import java.sql.PreparedStatement;

import com.onlinerecharge.model.Vendor;
import com.onlinerecharge.utility.DatabaseConnector;

public class VendorDAO {

	/**
	 * 
	 * @param vendor
	 * @return flagresult: true or false
	 * @throws Exception
	 * Inserts a new vendor in the database
	 */
	public boolean saveVendor(Vendor vendor) throws Exception {

		PreparedStatement statement = null;
		String sql = null;
		int count = 0;

		boolean returnFlg = false;
		try {
			sql = "insert into vendor_details(vendor_name) value(?)";
			statement = DatabaseConnector.getConnection().prepareStatement(sql);
			statement.setString(1, vendor.getVendor_name());

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
