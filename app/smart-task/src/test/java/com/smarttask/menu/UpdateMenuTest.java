package com.smarttask.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.testutils.TestConsoleHelper;
import com.smarttask.testutils.TestDataStoreHelper;

@DisplayName("Pruebas unitarias para UpdateMenu")
public class UpdateMenuTest {

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
        UpdateMenu instance1 = UpdateMenu.getInstance();
        UpdateMenu instance2 = UpdateMenu.getInstance();
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe capturar correctamente ID, Prioridad BAJA y Estado ACTIVADA")
    void testShowWithPriorityBajaAndStatusActiva() {
        // ID: 3, Prioridad: 1 (BAJA), Estado: 1 (ACTIVA)
        TestConsoleHelper.setSimulatedInput("3\n1\n1\n");

        UpdateMenu menu = UpdateMenu.getInstance();
        List<Object> result = menu.show();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(3, result.get(0));
        assertEquals(Priority.BAJA, result.get(1));
        assertEquals(Status.ACTIVA, result.get(2));

        String output = outContent.toString();
        assertTrue(output.contains("ACTUALIZAR TAREA"));
        assertTrue(output.contains("Ingrese el ID de la tarea:"));
        assertTrue(output.contains("Seleccione la prioridad:"));
        assertTrue(output.contains("Seleccione el estado:"));
    }

    @Test
    @DisplayName("Debe capturar correctamente ID, Prioridad MEDIA y Estado COMPLETADA")
    void testShowWithPriorityMediaAndStatusCompletada() {
        // ID: 5, Prioridad: 2 (MEDIA), Estado: 2 (COMPLETADA)
        TestConsoleHelper.setSimulatedInput("5\n2\n2\n");

        UpdateMenu menu = UpdateMenu.getInstance();
        List<Object> result = menu.show();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(5, result.get(0));
        assertEquals(Priority.MEDIA, result.get(1));
        assertEquals(Status.COMPLETADA, result.get(2));
    }

    @Test
    @DisplayName("Debe capturar correctamente ID, Prioridad ALTA")
    void testShowWithPriorityAlta() {
        // ID: 8, Prioridad: 3 (ALTA), Estado: 1 (ACTIVA)
        TestConsoleHelper.setSimulatedInput("8\n3\n1\n");

        UpdateMenu menu = UpdateMenu.getInstance();
        List<Object> result = menu.show();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(8, result.get(0));
        assertEquals(Priority.ALTA, result.get(1));
        assertEquals(Status.ACTIVA, result.get(2));
    }

    @Test
    @DisplayName("Debe reintentar si se introducen entradas no válidas en ID, prioridad o estado")
    void testShowWithRetriesOnInvalidInputs() {
        // ID inválido "0", "abc" -> válido "4"
        // Prioridad inválida "5", "x" -> válida "2" (MEDIA)
        // Estado inválido "4" -> válido "2" (COMPLETADA)
        TestConsoleHelper.setSimulatedInput("0\nabc\n4\n5\nx\n2\n4\n2\n");

        UpdateMenu menu = UpdateMenu.getInstance();
        List<Object> result = menu.show();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(4, result.get(0));
        assertEquals(Priority.MEDIA, result.get(1));
        assertEquals(Status.COMPLETADA, result.get(2));
    }
}
