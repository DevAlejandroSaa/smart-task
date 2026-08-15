package com.smarttask.core.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para el enum Priority")
public class PriorityTest {

    @Test
    @DisplayName("Debe contener los tres niveles de prioridad esperados")
    void testPriorityValues() {
        Priority[] values = Priority.values();

        assertEquals(3, values.length);
        assertEquals(Priority.BAJA, Priority.valueOf("BAJA"));
        assertEquals(Priority.MEDIA, Priority.valueOf("MEDIA"));
        assertEquals(Priority.ALTA, Priority.valueOf("ALTA"));
    }
}
