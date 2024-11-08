package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static com.parkit.parkingsystem.constants.ParkingType.CAR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ParkingSpotTest {

    private int number;
    private ParkingType parkingType;
    private boolean isAvailable;

    private ParkingSpot parkingSpot;

    @BeforeEach // Instanciate ParkingSpot class and create parkingSpot object
    void setUp() {
        this.parkingSpot = new ParkingSpot(number, parkingType, isAvailable);
    }

    @Test  // USE OF REFLECTION
    void getIdTest() throws NoSuchFieldException, IllegalAccessException {
        final Field field = parkingSpot.getClass().getDeclaredField("number");
        field.setAccessible(true);
        field.set(parkingSpot,1);

        int result= (int) field.get(parkingSpot);

        assertEquals(1,result);
    }

    @Test // USE OF REFLECTION ie, not proper getter used, but one from libraries using Field class
    void setIdTest() throws NoSuchFieldException, IllegalAccessException {
        parkingSpot.setId(1);
        final Field field = parkingSpot.getClass().getDeclaredField("number");
        field.setAccessible(true);
        int fieldValue = (int) field.get(parkingSpot);
        assertEquals(1, fieldValue);
    }

    @Test  // USE OF REFLECTION
    void getParkingTypeTest() throws NoSuchFieldException, IllegalAccessException {
        final Field field = parkingSpot.getClass().getDeclaredField("parkingType");
        field.setAccessible(true);
        field.set(parkingSpot,CAR);

        Enum result= (Enum) field.get(parkingSpot);

        assertEquals(CAR,result);
    }

    @Test  // USE OF REFLECTION  = not proper getter used, but one from libraries using Field class, focusing on Enum parkingtype
    void setParkingTypeTest() throws NoSuchFieldException, IllegalAccessException {
        parkingSpot.setParkingType(CAR);
        final Field field = parkingSpot.getClass().getDeclaredField("parkingType");
        field.setAccessible(true);
        Enum fieldValue = (Enum) field.get(parkingSpot);
        assertEquals(CAR, fieldValue);
    }

    @Test
    void isAvailableTest() {


    }

    @Test
    void setAvailableTest() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}