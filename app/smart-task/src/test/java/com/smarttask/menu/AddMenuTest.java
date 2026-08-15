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

import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.core.models.Task;
import com.smarttask.testutils.TestConsoleHelper;
import com.smarttask.testutils.TestDataStoreHelper;

@DisplayName("Pruebas unitarias para AddMenu")
public class AddMenuTest {

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
        AddMenu instance1 = AddMenu.getInstance();
        AddMenu instance2 = AddMenu.getInstance();
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe capturar una tarea con prioridad BAJA (opción 1)")
    void testShowWithPriorityBaja() {
        TestConsoleHelper.setSimulatedInput("Comprar suministros\n1\n");

        AddMenu menu = AddMenu.getInstance();
        Task task = menu.show();

        assertNotNull(task);
        assertEquals("Comprar suministros", task.getName());
        assertEquals(Priority.BAJA, task.getPriority());
        assertEquals(Status.ACTIVA, task.getStatus());

        String output = outContent.toString();
        assertTrue(output.contains("AGREGAR TAREA"));
        assertTrue(output.contains("Ingrese el nombre de la tarea:"));
        assertTrue(output.contains("1.- Baja"));
    }

    @Test
    @DisplayName("Debe capturar una tarea con prioridad MEDIA (opción 2)")
    void testShowWithPriorityMedia() {
        TestConsoleHelper.setSimulatedInput("Revisar código fuente\n2\n");

        AddMenu menu = AddMenu.getInstance();
        Task task = menu.show();

        assertNotNull(task);
        assertEquals("Revisar código fuente", task.getName());
        assertEquals(Priority.MEDIA, task.getPriority());
        assertEquals(Status.ACTIVA, task.getStatus());
    }

    @Test
    @DisplayName("Debe capturar una tarea con prioridad ALTA (opción 3)")
    void testShowWithPriorityAlta() {
        TestConsoleHelper.setSimulatedInput("Desplegar a producción\n3\n");

        AddMenu menu = AddMenu.getInstance();
        Task task = menu.show();

        assertNotNull(task);
        assertEquals("Desplegar a producción", task.getName());
        assertEquals(Priority.ALTA, task.getPriority());
        assertEquals(Status.ACTIVA, task.getStatus());
    }

    @Test
    @DisplayName("Debe reintentar la captura de prioridad si se ingresan opciones inválidas previas")
    void testShowWithRetryOnInvalidPriority() {
        // Ingresa nombre, opción inválida "9", opción no numérica "abc", y finalmente
        // "2" (MEDIA)
        TestConsoleHelper.setSimulatedInput("Tarea con reintento\n9\nabc\n2\n");

        AddMenu menu = AddMenu.getInstance();
        Task task = menu.show();

        assertNotNull(task);
        assertEquals("Tarea con reintento", task.getName());
        assertEquals(Priority.MEDIA, task.getPriority());
    }
}
