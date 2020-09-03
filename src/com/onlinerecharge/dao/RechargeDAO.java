package com.onlinerecharge.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.onlinerecharge.model.City;
import com.onlinerecharge.model.Plan;
import com.onlinerecharge.model.Recharge_history;
import com.onlinerecharge.model.Scheme;
import com.onlinerecharge.model.Vendor;
import com.onlinerecharge.model.Wallet;
import com.onlinerecharge.utility.DatabaseConnector;
import com.onlinerecharge.utility.ResultSetMapper;

public class RechargeDAO {

	/**
	 * 
	 * @return list of cities
	 * @throws Exception
	 */
	public List<City> getCityList() throws Exception {
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
	 * @param cityId
	 * @return list of vendors for a given city
	 * @throws Exception
	 */
	public List<Vendor> getVendorList(long cityId) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<Vendor> resultSetMapper = null;
		List<Vendor> vendorList = null;
		try {
			sql = new StringBuilder("Select vendor_id, vendor_name ")
					.append("from vendor_details where vendor_id in ")
					.append("(select distinct vendor_id from vendor_scheme_plan_map ")
					.append("where city_id = ?)");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, cityId);
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
	 * @param vendorId
	 * @return list of schemes for a given vendor
	 * @throws Exception
	 */
	public List<Scheme> getSchemeList(long vendorId) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<Scheme> resultSetMapper = null;
		List<Scheme> schemeList = null;
		try {
			sql = new StringBuilder("Select scheme_id, scheme_name ")
					.append("from scheme_details where scheme_id in ")
					.append("(select distinct scheme_id from vendor_scheme_plan_map ")
					.append("where vendor_id = ?)");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, vendorId);
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<Scheme>();
				schemeList = resultSetMapper.mapRersultSetToObject(resultSet,
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
		return schemeList;
	}

	/**
	 * 
	 * @param schemeId
	 * @return list of plans for a given scheme
	 * @throws Exception
	 */
	public List<Plan> getPlanList(long schemeId) throws Exception {

		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<Plan> resultSetMapper = null;
		List<Plan> planList = null;
		try {
			sql = new StringBuilder(
					"Select plan_id, plan_name, plan_amount, plan_validity  ")
					.append("from plan_details where plan_id in ")
					.append("(select distinct plan_id from vendor_scheme_plan_map ")
					.append("where scheme_id = ?)");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, schemeId);
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<Plan>();
				planList = resultSetMapper.mapRersultSetToObject(resultSet,
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
		return planList;
	}

	/**
	 * 
	 * @param userId
	 * @return wallet deatils
	 * @throws Exception
	 */
	public Wallet getWalletDetails(long userId) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<Wallet> resultSetMapper = null;
		List<Wallet> walletList = null;
		Wallet wallet = null;
		try {
			sql = new StringBuilder("Select wd.wallet_id, wd.wallet_amount ")
					.append("from wallet_details wd, user_wallet_map uwm ")
					.append("where wd.wallet_id = uwm.wallet_id and uwm.user_id = ?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, userId);
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<Wallet>();
				walletList = resultSetMapper.mapRersultSetToObject(resultSet,
						Wallet.class);
				if (walletList != null && walletList.size() > 0) {
					wallet = walletList.get(0);
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			resultSetMapper = null;
			walletList = null;
		}
		return wallet;
	}

	/**
	 * 
	 * @param vendorId
	 * @param schemeId
	 * @param planId
	 * @param cityId
	 * @param userId
	 * @return flagresult: true or false
	 * @throws Exception
	 * Used to pick user and vspm id from table and insert it in recharge history
	 */
	public boolean saveRechargeinRecahrgeHistory(long vendorId, long schemeId, long planId,
			long cityId, long userId) throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		int count = 0;
		boolean returnFlg = false;
		ResultSet resultSet = null;
		long vspmId = 0;
		try {
			sql = new StringBuilder(
					"select vspm_id from vendor_scheme_plan_map ")
					.append("where vendor_id = ? and scheme_id = ? and plan_id = ? and city_id = ?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, vendorId);
			statement.setLong(2, schemeId);
			statement.setLong(3, planId);
			statement.setLong(4, cityId);
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					vspmId = resultSet.getLong(1);
				}
			}
			sql = new StringBuilder("insert into recharge_history ")
					.append("(vspm_id, user_id) values(?,?)");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, vspmId);
			statement.setLong(2, userId);
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

	/**
	 * 
	 * @param userId
	 * @return list of recharge history
	 * @throws Exception
	 */
	public List<Recharge_history> getRechargeHistory(long userId)
			throws Exception {
		PreparedStatement statement = null;
		StringBuilder sql = null;
		ResultSet resultSet = null;
		ResultSetMapper<Recharge_history> resultSetMapper = null;
		List<Recharge_history> rechargeHistoryList = null;
		try {
			sql = new StringBuilder(
					"select vd.vendor_name, sd.scheme_name, cd.city_name,pd.plan_name ")
					.append("from vendor_scheme_plan_map vspm, recharge_history rh, vendor_details vd, ")
					.append("city_details cd, scheme_details sd, plan_details pd ")
					.append("where rh.vspm_id = vspm.vspm_id and vspm.vendor_id = vd.vendor_id ")
					.append("and vspm.city_id = cd.city_id and vspm.scheme_id = sd.scheme_id ")
					.append("and vspm.plan_id = pd.plan_id and rh.user_id = ?");
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql.toString());
			statement.setLong(1, userId);
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				resultSetMapper = new ResultSetMapper<Recharge_history>();
				rechargeHistoryList = resultSetMapper.mapRersultSetToObject(
						resultSet, Recharge_history.class);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			statement = null;
			sql = null;
			resultSet = null;
			resultSetMapper = null;
		}
		return rechargeHistoryList;
	}

}
