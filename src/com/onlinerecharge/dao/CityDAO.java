package com.onlinerecharge.dao;

import java.sql.PreparedStatement;






import com.onlinerecharge.model.City;
import com.onlinerecharge.utility.DatabaseConnector;

public class CityDAO {
	
	/**
	 * 
	 * @param city
	 * @return flagresult:true or false
	 * @throws Exception
	 * Inserts a new city in database
	 */
	public boolean saveCity(City city) throws Exception {
		
		PreparedStatement statement = null;
		String sql=null;
		int count = 0;
		
		boolean returnFlg = false;
		try {
			 sql="insert into city_details(city_name) value(?)";
			statement = DatabaseConnector.getConnection().prepareStatement(
					sql);
			statement.setString(1, city.getCity_name());
			
			count = statement.executeUpdate();
			if(count>0){
				returnFlg = true;
				System.out.println(count + " row inserted...");
			}else{
				returnFlg = false;
				
			}
		} catch (Exception exception) {
			throw exception;
		}finally{
			statement = null;
			sql = null;
			
		}		
		return returnFlg;
	}
	

}
