package com.parkit.parkingsystem.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputReaderUtilTest {

    @Mock
    Scanner scanMock = mock(Scanner.class);

    @Mock
    InputReaderUtil inputReaderUtilMock = mock(InputReaderUtil.class);

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readSelectionTest() throws NoSuchFieldException, IllegalAccessException {
        // Configurer le comportement du mock pour nextLine() qui retourne "1"
        when(scanMock.nextLine()).thenReturn("1");

        // Utiliser la réflexion pour injecter le mock Scanner dans le champ statique 'scan' de la classe InputReaderUtil
        Field scanField = InputReaderUtil.class.getDeclaredField("scan");
        scanField.setAccessible(true);  // Rendre le champ accessible
        scanField.set(null, scanMock);  // Modifier le champ statique avec notre mock Scanner

        // Créer une instance de InputReaderUtil et appeler la méthode à tester
        InputReaderUtil inputReaderUtil = new InputReaderUtil();

        // Vérifier que le résultat est 1
        assertEquals(1, inputReaderUtil.readSelection());
    }

    @Test
    void readVehicleRegistrationNumber() {
    }
}