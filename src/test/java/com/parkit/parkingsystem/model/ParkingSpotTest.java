package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static com.parkit.parkingsystem.constants.ParkingType.CAR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
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

    @Test // Test for a return : check if the value exists ( = should not),
        // verify it then set it to true and assert
    void returnIsAvailableTest() {

        // Instanciation de parkingspot dans le before
        // Verifie l'existence de parkingspot en renvoyant un booleen qui doit être false

        boolean result= parkingSpot.isAvailable();
        // Make sure it's not existing  / false
        assertFalse(result);

        // Make the isAvailable boolean available
        parkingSpot.setAvailable(true);

        // pass the change to result that should be true
        result = parkingSpot.isAvailable();
        // Check if result is true
        assertTrue(result);
    }


    @Test
    void setAvailableTrueTest() throws NoSuchFieldException, IllegalAccessException {
        parkingSpot.setAvailable(true);
        final Field field = parkingSpot.getClass().getDeclaredField("isAvailable");
        field.setAccessible(true);
        boolean fieldValue = (boolean) field.get(parkingSpot);
        assertEquals(true, fieldValue);
    }

    // Optionnal test to make sure false passes too
    @Test
    void setAvailableFalseTest() throws NoSuchFieldException, IllegalAccessException {
        parkingSpot.setAvailable(false);
        final Field field = parkingSpot.getClass().getDeclaredField("isAvailable");
        field.setAccessible(true);
        boolean fieldValue = (boolean) field.get(parkingSpot);
        assertEquals(false, fieldValue);
    }

// Not used in the code

//    @Test
//    void testEquals() {
//
//    }
//
//    @Test
//    void testHashCode() {
//    }

}