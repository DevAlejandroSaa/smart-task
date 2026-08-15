package com.smarttask.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.testutils.TestConsoleHelper;
import com.smarttask.testutils.TestDataStoreHelper;

@DisplayName("Pruebas unitarias para DeleteMenu")
public class DeleteMenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        TestDataStoreHelper.resetDataStore();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    @DisplayName("Debe retornar siempre la misma instancia singleton")
    void testSingletonInstance() {
        DeleteMenu instance1 = DeleteMenu.getInstance();
        DeleteMenu instance2 = DeleteMenu.getInstance();
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe solicitar y retornar un ID entero positivo válido")
    void testShowValidId() {
        TestConsoleHelper.setSimulatedInput("7\n");

        DeleteMenu menu = DeleteMenu.getInstance();
        int id = menu.show();

        assertEquals(7, id);

        String output = outContent.toString();
        assertTrue(output.contains("ELIMINAR TAREA"));
        assertTrue(output.contains("Ingrese el ID de la tarea:"));
    }

    @Test
    @DisplayName("Debe reintentar la solicitud si se ingresan valores negativos, cero o no numéricos")
    void testShowWithRetryOnInvalidInput() {
        // Ingresa no numérico "xyz", negativo "-3", cero "0", y finalmente válido "12"
        TestConsoleHelper.setSimulatedInput("xyz\n-3\n0\n12\n");

        DeleteMenu menu = DeleteMenu.getInstance();
        int id = menu.show();

        assertEquals(12, id);
    }
}
