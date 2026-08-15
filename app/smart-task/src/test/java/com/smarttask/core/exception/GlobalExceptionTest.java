package com.smarttask.core.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para GlobalException")
public class GlobalExceptionTest {

    @Test
    @DisplayName("Debe imprimir el mensaje de la excepción en la salida estándar sin lanzar error")
    void testHandleException() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outContent));
            GlobalException.handle(new RuntimeException("Error de prueba"));

            String output = outContent.toString();
            assertTrue(output.contains("Error de prueba"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
