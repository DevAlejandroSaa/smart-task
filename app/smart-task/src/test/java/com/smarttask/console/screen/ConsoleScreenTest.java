package com.smarttask.console.screen;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para ConsoleScreen")
public class ConsoleScreenTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Debe retornar siempre la misma instancia singleton")
    void testSingleton() {
        ConsoleScreen instance1 = ConsoleScreen.getInstance();
        ConsoleScreen instance2 = ConsoleScreen.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("show(header, content) debe imprimir el encabezado formateado y el contenido")
    void testShowHeaderAndContent() {
        ConsoleScreen screen = ConsoleScreen.getInstance();
        screen.show("MENU PRINCIPAL", "1. Opcion 1\n2. Opcion 2");

        String output = outContent.toString();
        assertTrue(output.contains("========================================"));
        assertTrue(output.contains("MENU PRINCIPAL"));
        assertTrue(output.contains("1. Opcion 1"));
    }

    @Test
    @DisplayName("show(header, content, footer) debe imprimir encabezado, contenido y pie de página")
    void testShowHeaderContentFooter() {
        ConsoleScreen screen = ConsoleScreen.getInstance();
        screen.show("TITULO", "Cuerpo", "Ingrese opcion: ");

        String output = outContent.toString();
        assertTrue(output.contains("TITULO"));
        assertTrue(output.contains("Cuerpo"));
        assertTrue(output.contains("Ingrese opcion: "));
    }
}
