//package com.parkit.parkingsystem.service;
//
//import com.parkit.parkingsystem.constants.ParkingType;
//import com.parkit.parkingsystem.dao.ParkingSpotDAO;
//import com.parkit.parkingsystem.dao.TicketDAO;
//import com.parkit.parkingsystem.model.ParkingSpot;
//import com.parkit.parkingsystem.model.Ticket;
//import com.parkit.parkingsystem.util.InputReaderUtil;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.io.ByteArrayInputStream;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//
//class ParkingServiceTest {
//
//    private static ParkingService parkingService;
//
//    @Mock
//    private static InputReaderUtil inputReaderUtil;
//    @Mock
//    private static ParkingSpotDAO parkingSpotDAO;
//    @Mock
//    private static TicketDAO ticketDAO;
//
//    @BeforeEach
//    // Permits simulation of providing a data input
//    void provideInput(String data) {
//        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
//        System.setIn(testIn);
//    }
//
//    void setUp() {
//        try {
//            when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
//
//            ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
//            Ticket ticket = new Ticket();
//            ticket.setInTime(new Date(System.currentTimeMillis() - (60*60*1000)));
//            ticket.setParkingSpot(parkingSpot);
//            ticket.setVehicleRegNumber("ABCDEF");
//            when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
//            when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);
//
//            when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);
//
//            parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw  new RuntimeException("Failed to set up test mock objects");
//        }
//    }
//
//    @Test
//    void processIncomingVehicle() {
//    }
//
//    @Test
//    void getNextParkingNumberIfAvailable() {
//    }
//
//    @Test
//    void processExitingVehicle() {
//    }
//}