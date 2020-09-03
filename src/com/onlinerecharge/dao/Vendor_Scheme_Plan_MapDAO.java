package com.onlinerecharge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.onlinerecharge.model.City;
import com.onlinerecharge.model.Plan;
import com.onlinerecharge.model.Scheme;
import com.onlinerecharge.model.Vendor;
import com.onlinerecharge.model.Vendor_Scheme_Plan_Map;
import com.onlinerecharge.utility.DatabaseConnector;
import com.onlinerecharge.utility.ResultSetMapper;

public class Vendor_Scheme_Plan_MapDAO {

	/**
	 * 
	 * @return list of cities
	 * @throws Exception
	 */
	public static List<City> getCityList() throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<City> resultSetMapper = null;
		List<City> cityList = null;
		try {
			sql = new StringBuilder(
					"Select city_id, city_name from city_details");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<City>();
				cityList = resultSetMapper.mapRersultSetToObject(resultSet,
						City.class);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			resultSetMapper = null;
		}
		return cityList;
	}

	/**
	 * 
	 * @return list of vendors
	 * @throws Exception
	 */
	public static List<Vendor> getVendorList() throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<Vendor> resultSetMapper = null;
		List<Vendor> vendorList = null;
		try {
			sql = new StringBuilder(
					"Select vendor_id, vendor_name from vendor_details");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<Vendor>();
				vendorList = resultSetMapper.mapRersultSetToObject(resultSet,
						Vendor.class);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			resultSetMapper = null;
		}
		return vendorList;
	}

	/**
	 * 
	 * @return list of schemes
	 * @throws Exception
	 */
	public static List<Scheme> getSchemeList() throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<Scheme> resultSetMapper = null;
		List<Scheme> schemelist = null;
		try {
			sql = new StringBuilder(
					"Select scheme_id, scheme_name from scheme_details");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<Scheme>();
				schemelist = resultSetMapper.mapRersultSetToObject(resultSet,
						Scheme.class);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			resultSetMapper = null;
		}
		return schemelist;
	}

	/**
	 * 
	 * @return list of schemes
	 * @throws Exception
	 */
	public static List<Plan> getPlanList() throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<Plan> resultSetMapper = null;
		List<Plan> planlist = null;
		try {
			sql = new StringBuilder(
					"Select plan_id, plan_name, plan_amount, plan_validity from plan_details");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<Plan>();
				planlist = resultSetMapper.mapRersultSetToObject(resultSet,
						Plan.class);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			resultSetMapper = null;
		}
		return planlist;
	}

	/**
	 * 
	 * @param vspm
	 * @return flagresult: true or false
	 * @throws Exception
	 * Used to save the city,vendor,scheme and plan id in VSPM map
	 */
	public boolean saveVendor_Scheme_Plan_Map(Vendor_Scheme_Plan_Map vspm)
			throws Exception {

		PreparedStatement statement = null;
		String sql = null;
		int count = 0;
		boolean returnFlg = false;
		try {
			sql = "insert into vendor_scheme_plan_map(vendor_id,scheme_id,plan_id,city_id) value(?,?,?,?)";
			statement = DatabaseConnector.getConnection().prepareStatement(sql);
			statement.setLong(1, vspm.getVendor_id());
			statement.setLong(2, vspm.getScheme_id());
			statement.setLong(3, vspm.getPlan_id());
			statement.setLong(4, vspm.getCity_id());

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
