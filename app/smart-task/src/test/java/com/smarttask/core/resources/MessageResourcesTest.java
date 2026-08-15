package com.smarttask.core.resources;

import java.util.MissingResourceException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para MessageResources")
public class MessageResourcesTest {

    @Test
    @DisplayName("Debe retornar la misma instancia singleton")
    void testSingleton() {
        MessageResources instance1 = MessageResources.getInstance();
        MessageResources instance2 = MessageResources.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe recuperar los mensajes definidos en messages.properties")
    void testGetMessageSuccess() {
        MessageResources resources = MessageResources.getInstance();

        assertEquals("La tarea no existe.", resources.getMessage("error.task.not.found"));
        assertEquals("La tarea ya existe.", resources.getMessage("error.task.already.exists"));
        assertEquals("La opción seleccionada no es válida.", resources.getMessage("valid.menu.option.invalid"));
        assertEquals("Debe ingresar un número entero positivo mayor a cero.",
                resources.getMessage("valid.number.positive.invalid"));
    }

    @Test
    @DisplayName("Debe lanzar MissingResourceException si la clave no existe")
    void testGetMessageMissingKey() {
        MessageResources resources = MessageResources.getInstance();
        assertThrows(MissingResourceException.class, () -> resources.getMessage("clave.inexistente"));
    }
}
