package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ParkingSpotDAO {
    private static final Logger logger = LogManager.getLogger("ParkingSpotDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public int getNextAvailableSlot(ParkingType parkingType){
        Connection con = null;
        int result=-1;
        try {
            // Connection to database
            con = dataBaseConfig.getConnection();

            // Seeks SQL request to get next parking spot
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_NEXT_PARKING_SPOT);

            // Insert the request in String
            ps.setString(1, parkingType.toString());

            // Executes request in database
            ResultSet rs = ps.executeQuery();

            //
            if(rs.next()){
                result = rs.getInt(1);;
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error fetching next available slot",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return result;
    }

    public boolean updateParking(ParkingSpot parkingSpot){
        //update the availability fo that parking slot
        // Called when car enters the parking lot
        // When leaving with car => error => Parking updated, but ticket out time not written
        // Parking type doesn't change but when testing only 1 parking type works at a time
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PARKING_SPOT);
            // Set parameter index 1 of UPDATE_PARKING_SPOT
            ps.setBoolean(1, parkingSpot.isAvailable());
            // Set parameter index 2 of UPDATE_PARKING_SPOT
            ps.setInt(2, parkingSpot.getId());



            // Updates RowCount, executeUpdate SQL request in the dataBase, returns 1 or 2
            int updateRowCount = ps.executeUpdate();

            // Close = ends the statement
            dataBaseConfig.closePreparedStatement(ps);

            // Compare RowCount to 1 and makes it a True or False value
            return (updateRowCount == 1);
        }catch (Exception ex){
            logger.error("Error updating parking info",ex);
            return false;
        }finally {
            dataBaseConfig.closeConnection(con);
        }
    }

}
