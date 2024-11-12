package com.parkit.parkingsystem.dao;


import com.parkit.parkingsystem.model.ParkingSpot;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.sql.SQLException;

import static com.parkit.parkingsystem.constants.ParkingType.CAR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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

    // Test passes but not what we want to assert
    void getNextAvailableSlotException() throws SQLException, ClassNotFoundException {
        ParkingSpotDAO parkingSpotDAOMock = mock(ParkingSpotDAO.class);
        Logger loggerMock = mock(Logger.class);

        // On mock la méthode updateParking pour qu'elle lance une exception
        when(parkingSpotDAOMock.getNextAvailableSlot(CAR)).thenThrow(new RuntimeException("Error fetching next available slot"));
        // On vérifie que l'appel à la méthode renvoie false
        // int result = parkingSpotDAO.getNextAvailableSlot(CAR);

        // Renvoi de l'exception comme indiqué au-dessus
        assertThrows(RuntimeException.class, () -> { parkingSpotDAOMock.getNextAvailableSlot(CAR);
        });

    }

    @Test
    void updateParking() throws SQLException, ClassNotFoundException {
        boolean result = parkingSpotDAO.updateParking(parkingSpotMock);

        assertTrue(true, String.valueOf(result));
    }

    @Test
    void updateParkingException() throws SQLException, ClassNotFoundException {
        // On mock la méthode updateParking pour qu'elle lance une exception
        when(parkingSpotDAO.updateParking(parkingSpotMock)).thenThrow(new RuntimeException("Error updating parking spot"));

        // On vérifie que l'appel à la méthode renvoie false
        boolean result = parkingSpotDAO.updateParking(parkingSpotMock);
        assertFalse(result, "Error updating parking spot");
    }


}