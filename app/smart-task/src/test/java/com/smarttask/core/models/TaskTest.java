package com.smarttask.core.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para la entidad Task")
public class TaskTest {

    @Test
    @DisplayName("Debe instanciar una tarea con estado inicial ACTIVA e id 0")
    void testTaskConstructor() {
        Task task = new Task("Estudiar Java", Priority.ALTA);

        assertEquals(0, task.getId());
        assertEquals("Estudiar Java", task.getName());
        assertEquals(Priority.ALTA, task.getPriority());
        assertEquals(Status.ACTIVA, task.getStatus());
    }

    @Test
    @DisplayName("Debe permitir modificar y obtener todas las propiedades de la tarea")
    void testSettersAndGetters() {
        Task task = new Task("Tarea inicial", Priority.BAJA);

        task.setId(5);
        task.setName("Tarea modificada");
        task.setPriority(Priority.MEDIA);
        task.setStatus(Status.COMPLETADA);

        assertEquals(5, task.getId());
        assertEquals("Tarea modificada", task.getName());
        assertEquals(Priority.MEDIA, task.getPriority());
        assertEquals(Status.COMPLETADA, task.getStatus());
    }

    @Test
    @DisplayName("El método toString debe incluir los atributos de la tarea")
    void testToString() {
        Task task = new Task("Comprar pan", Priority.MEDIA);
        task.setId(1);

        String result = task.toString();

        assertNotNull(result);
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("name=Comprar pan"));
        assertTrue(result.contains("priority=MEDIA"));
        assertTrue(result.contains("status=ACTIVA"));
    }
}
