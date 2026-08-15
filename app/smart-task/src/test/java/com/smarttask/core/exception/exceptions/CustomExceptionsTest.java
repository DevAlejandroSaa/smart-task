package com.smarttask.core.exception.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para excepciones personalizadas del dominio")
public class CustomExceptionsTest {

    @Test
    @DisplayName("InvalidTaskException debe almacenar y propagar el mensaje de error")
    void testInvalidTaskException() {
        InvalidTaskException ex = new InvalidTaskException("Error de tarea inválida");
        assertEquals("Error de tarea inválida", ex.getMessage());
        assertTrue(ex instanceof RuntimeException);
    }

    @Test
    @DisplayName("InvalidTaskIdException debe almacenar y propagar el mensaje de error")
    void testInvalidTaskIdException() {
        InvalidTaskIdException ex = new InvalidTaskIdException("ID no válido");
        assertEquals("ID no válido", ex.getMessage());
        assertTrue(ex instanceof RuntimeException);
    }

    @Test
    @DisplayName("TaskAlreadyExistsException debe almacenar y propagar el mensaje de error")
    void testTaskAlreadyExistsException() {
        TaskAlreadyExistsException ex = new TaskAlreadyExistsException("Tarea ya existe");
        assertEquals("Tarea ya existe", ex.getMessage());
        assertTrue(ex instanceof RuntimeException);
    }

    @Test
    @DisplayName("TaskNotFoundException debe almacenar y propagar el mensaje de error")
    void testTaskNotFoundException() {
        TaskNotFoundException ex = new TaskNotFoundException("Tarea no encontrada");
        assertEquals("Tarea no encontrada", ex.getMessage());
        assertTrue(ex instanceof RuntimeException);
    }
}
