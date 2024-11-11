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
    private ParkingSpotDAO parkingSpotDAO;

    @InjectMocks
    private ParkingSpotDAO parkingspotDAO;

    @Mock
    private Logger logger;

    @Mock
    DataBaseConfig dataBaseConfigMock;

    @Mock
    ParkingType parkingTypeMock;

    @Mock
    ParkingSpot parkingSpotMock;

    @Mock
    Connection connMock;

    @Mock
    PreparedStatement psMock;

    @Mock
    ResultSet rsMock;

    @BeforeEach
    void setup() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.initMocks(this);
        parkingSpotDAO = new ParkingSpotDAO();
    }


    @Test
    void getNextAvailableSlotTest() throws SQLException, ClassNotFoundException {

        // PREPARATIF : TRADUCTION EN MOCK de chaque ligne préparative
        // On reprend le code de la méthode et on inverse chaque ligne avec when,
        // par ex: quand la connection se fait, on l'associe à connMock

        when(dataBaseConfigMock.getConnection()).thenReturn(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(psMock);
        when(parkingTypeMock.toString()).thenReturn(String.valueOf(CAR));

        when(psMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true);

        // Quand je prends la colonne index 1, renvoie la valeur du parking associé
        when(rsMock.getInt(1)).thenReturn(1);

        // Quand il n'y a rien à faire, simule des choses car il ne fait rien étant donné qu'il est mocké
        doNothing().when(dataBaseConfigMock).closeResultSet(rsMock);
        doNothing().when(dataBaseConfigMock).closePreparedStatement(psMock);
        doNothing().when(dataBaseConfigMock).closeConnection(connMock);

        // Appel de la méthode à tester
        int result = parkingSpotDAO.getNextAvailableSlot(CAR);


        // Vérification du résultat
        assertEquals(1, result);

//        // vérification des méthodes de fermeture
//        verify(dataBaseConfigMock).closeResultSet(rsMock);
//        verify(dataBaseConfigMock).closePreparedStatement(psMock);
//        verify(dataBaseConfigMock).closeConnection(connMock);

    }

    @Test
    void updateParking() throws SQLException, ClassNotFoundException {
        psMock.setBoolean(1, parkingSpotMock.isAvailable());
        psMock.setInt(2, parkingSpotMock.getId());

        int updateRowCount = psMock.executeUpdate();

        boolean result = parkingSpotDAO.updateParking(parkingSpotMock);

        assertTrue(true, String.valueOf(result));
    }

    @Test
    void updateParkingException() {
        // Setup du mock
        doThrow(new RuntimeException("Test Exception")).when(parkingSpotDAO).updateParking(parkingSpotMock);

        // Appel du code qui devrait loguer l'erreur
        parkingSpotDAO.updateParking(parkingSpotMock);

        // Vérification que l'erreur a bien été loguée
        verify(logger).error(eq("Error fetching next available slot"), any(RuntimeException.class));
    }


}