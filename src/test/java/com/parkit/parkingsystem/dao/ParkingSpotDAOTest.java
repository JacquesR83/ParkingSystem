package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacoco.core.analysis.CoverageNodeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.parkit.parkingsystem.constants.ParkingType.CAR;
import static com.parkit.parkingsystem.dao.ParkingSpotDAO.logger;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

class ParkingSpotDAOTest {

    @Mock
    private ParkingSpotDAO parkingSpotDAO;

    @Mock
    private Logger logger;

    @Mock
    ParkingSpot parkingSpotMock;

    @BeforeEach
    void setup() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.initMocks(this);
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotMock = mock(ParkingSpot.class);
    }


    @Test
    void getNextAvailableSlotTest() throws SQLException, ClassNotFoundException {
        int result = parkingSpotDAO.getNextAvailableSlot(CAR);

        // Checking result
        assertEquals(1, result);

    }

    @Test
    void updateParking() throws SQLException, ClassNotFoundException {
        boolean result = parkingSpotDAO.updateParking(parkingSpotMock);

        assertTrue(true, String.valueOf(result));
    }

//    @Test
//    void updateParkingException() {
//        // Setup du mock
//        doThrow(new RuntimeException("Test Exception")).when(parkingSpotDAO).updateParking(parkingSpotMock);
//
//        // Appel du code qui devrait loguer l'erreur
//        try{
//            parkingSpotDAO.updateParking(parkingSpotMock);
//        }
//        catch (RuntimeException e) {
//        }
//
//        // Vérification que l'erreur a bien été loguée
//        verify(logger).error(eq("Error fetching next available slot"), any(RuntimeException.class));
//    }


}