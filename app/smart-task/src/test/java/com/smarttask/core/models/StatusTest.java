package com.smarttask.core.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para el enum Status")
public class StatusTest {

    @Test
    @DisplayName("Debe contener los dos estados de ciclo de vida de tarea")
    void testStatusValues() {
        Status[] values = Status.values();

        assertEquals(2, values.length);
        assertEquals(Status.ACTIVA, Status.valueOf("ACTIVA"));
        assertEquals(Status.COMPLETADA, Status.valueOf("COMPLETADA"));
    }
}
