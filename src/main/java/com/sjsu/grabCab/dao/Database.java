package com.sjsu.grabCab.dao;

import java.sql.CallableStatement;

import com.datastax.driver.core.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;



@Component
public class Database {
	public List<Map<String, Object>> executeQuery(String sql) throws SQLException {
        System.out.println("Executing SQL query: " + sql);

        Connection connection = DriverManager.getConnection("jdbc:mysql://cmpe226grabcab.cdxflcl8qqin.us-west-1.rds.amazonaws.com:3306/cmpe226","grabCab","cmpe226grabcab");
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<String, Object>(columnCount);
                for (int i = 1; i <= columnCount; i++)
                    row.put(meta.getColumnName(i), rs.getObject(i));

                rows.add(row);
            }

            rs.close();
            statement.close();
            return rows;
        } catch (SQLException e) {
            throw new SQLException("Error executing query: " + sql, e);
        } finally {
            connection.close();
        }
    }
	
	public boolean executeUpdate(String sql) throws SQLException{
		System.out.println("Executing SQL query: " + sql);

        Statement statement = null;
		Connection connection = DriverManager.getConnection("jdbc:mysql://cmpe226grabcab.cdxflcl8qqin.us-west-1.rds.amazonaws.com:3306/cmpe226","grabCab","cmpe226grabcab");
        try {
			statement = connection.createStatement();

			statement.execute(sql);

			System.out.println("Record is updated to DBUSER table!");
			return true;
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}

	}
	
	public void executeStoredProcedure(String sql, int rideId) throws SQLException{
		
        String updateStoredProc ="{call sp_PromoCostCalculation(?,?)}";
        CallableStatement callableStatement = null;
		Connection connection = DriverManager.getConnection("jdbc:mysql://cmpe226grabcab.cdxflcl8qqin.us-west-1.rds.amazonaws.com:3306/cmpe226","grabCab","cmpe226grabcab");
        try {
			callableStatement = connection.prepareCall(updateStoredProc);
			
			System.out.println("IN Database: SP eamil : "+sql);

			callableStatement.setString(1, sql);
			
			callableStatement.setInt(2, rideId);
			
			callableStatement.execute();

			System.out.println("Record is updated to DBUSER table!");
		
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			

		} finally {

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}

	}
	
public void executeStoredProcedureSetCost(int rideid) throws SQLException{
		
        String updateStoredProc ="{call sp_DefaultCostPD(?)}";
        CallableStatement callableStatement = null;
		Connection connection = DriverManager.getConnection("jdbc:mysql://cmpe226grabcab.cdxflcl8qqin.us-west-1.rds.amazonaws.com:3306/cmpe226","grabCab","cmpe226grabcab");
        try {
			callableStatement = connection.prepareCall(updateStoredProc);
			
			System.out.println("IN Database: SP cost : "+rideid);

			callableStatement.setInt(1, rideid);
			
			callableStatement.execute();

			System.out.println("Record is updated to DBUSER table!");
		
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			

		} finally {

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}

	}
	
	
	
	public boolean executeCassandra(int rideId, String email, String pickUpLocation, String dropOffLocation, String carType, String licenseNumber, String rideStatus) throws SQLException{
		System.out.println("Executing Cassandra query: ");

		Cluster cluster=null;
		Session session;
		try{
			cluster = Cluster.builder().addContactPoints("34.216.16.158").build();
			session = cluster.connect("grabcab");
			
			PreparedStatement statement = session.prepare("Insert into Ride (rideid, email, pickUpLocation, dropOffLocation, carType, licenseNumber, rideStatus) values(?,?,?,?,?,?,?)");
			BoundStatement boundStatement = new BoundStatement(statement);
			
			session.execute(boundStatement.bind(rideId, email, pickUpLocation, dropOffLocation,  carType, licenseNumber, rideStatus));
		}catch (Exception e) {

			System.out.println(e.getMessage());
			

		}
		finally {
			cluster.close();
		}
		return true;
		

	}
	
}
