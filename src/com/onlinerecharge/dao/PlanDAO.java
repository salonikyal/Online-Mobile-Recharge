package com.onlinerecharge.dao;

import java.sql.PreparedStatement;

import com.onlinerecharge.model.Plan;
import com.onlinerecharge.utility.DatabaseConnector;

public class PlanDAO {

	/**
	 * 
	 * @param plan
	 * @return flagresult:true or false
	 * @throws Exception
	 * Inserts a new plan in the database
	 */
	public boolean savePlan(Plan plan) throws Exception {
		PreparedStatement statement = null;
		String sql = null;
		int count = 0;
		boolean returnFlg = false;
		try {

			sql = "insert into plan_details(plan_name,plan_amount,plan_validity) values(?,?,?)";

			statement = DatabaseConnector.getConnection().prepareStatement(sql);
			statement.setString(1, plan.getPlan_name());
			statement.setDouble(2, plan.getPlan_amount());
			statement.setString(3, plan.getPlan_validity());

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
