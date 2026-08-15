package com.smarttask.console.output;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.core.models.Task;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para ConsoleOutput")
public class ConsoleOutputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("print(String) debe imprimir texto sin salto de línea")
    void testPrint() {
        ConsoleOutput.print("Hola mundo");
        assertEquals("Hola mundo", outContent.toString());
    }

    @Test
    @DisplayName("println(String) debe imprimir texto con salto de línea")
    void testPrintln() {
        ConsoleOutput.println("Mensaje con salto");
        assertEquals("Mensaje con salto" + System.lineSeparator(), outContent.toString());
    }

    @Test
    @DisplayName("print(String format, Object... values) debe imprimir texto formateado")
    void testPrintFormatted() {
        ConsoleOutput.print("Valor: %d", 42);
        assertEquals("Valor: 42", outContent.toString());
    }

    @Test
    @DisplayName("println(String format, Object... values) debe imprimir texto formateado con salto")
    void testPrintlnFormatted() {
        ConsoleOutput.println("Total: %d", 100);
        assertEquals("Total: 100" + System.lineSeparator(), outContent.toString());
    }

    @Test
    @DisplayName("println(List<Task>) debe formatear e imprimir la lista de tareas")
    void testPrintlnTaskList() {
        Task task = new Task("Test Task", Priority.ALTA);
        task.setId(1);
        task.setStatus(Status.ACTIVA);

        ConsoleOutput.println(Arrays.asList(task));

        String output = outContent.toString();
        assertTrue(output.contains("ID: 1"));
        assertTrue(output.contains("Nombre: Test Task"));
        assertTrue(output.contains("Prioridad: ALTA"));
        assertTrue(output.contains("Estado: ACTIVA"));
    }
}
