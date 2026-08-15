package com.smarttask.core.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.core.models.Task;
import com.smarttask.testutils.TestDataStoreHelper;

@DisplayName("Pruebas unitarias para DataStore")
public class DataStoreTest {

    private DataStore dataStore;

    @BeforeEach
    void setUp() {
        TestDataStoreHelper.resetDataStore();
        dataStore = DataStore.getInstance();
    }

    @Test
    @DisplayName("Debe retornar siempre la misma instancia singleton")
    void testSingletonInstance() {
        DataStore instance1 = DataStore.getInstance();
        DataStore instance2 = DataStore.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("getNextId debe iniciar en 1 con el almacén vacío")
    void testGetNextIdInitial() {
        assertEquals(1, dataStore.getNextId());
    }

    @Test
    @DisplayName("Debe agregar y recuperar tareas por su ID")
    void testAddAndGetTaskById() {
        Task task1 = new Task("Aprender JUnit 5", Priority.ALTA);
        task1.setId(dataStore.getNextId());
        dataStore.addTask(task1);

        Task task2 = new Task("Revisar Javadoc", Priority.MEDIA);
        task2.setId(dataStore.getNextId());
        dataStore.addTask(task2);

        assertEquals(2, dataStore.getTasks().size());
        assertEquals(task1, dataStore.getTaskById(1));
        assertEquals(task2, dataStore.getTaskById(2));
    }

    @Test
    @DisplayName("getTaskById debe retornar null para IDs inexistentes o fuera de rango")
    void testGetTaskByIdNotFound() {
        assertNull(dataStore.getTaskById(1));
        assertNull(dataStore.getTaskById(99));
    }

    @Test
    @DisplayName("Debe actualizar correctamente los datos de una tarea")
    void testUpdateTask() {
        Task task = new Task("Original", Priority.BAJA);
        task.setId(1);
        dataStore.addTask(task);

        Task updated = new Task("Modificada", Priority.ALTA);
        updated.setId(1);
        updated.setStatus(Status.COMPLETADA);
        dataStore.updateTask(updated);

        Task retrieved = dataStore.getTaskById(1);
        assertEquals("Modificada", retrieved.getName());
        assertEquals(Priority.ALTA, retrieved.getPriority());
        assertEquals(Status.COMPLETADA, retrieved.getStatus());
    }

    @Test
    @DisplayName("Debe reciclar identificadores liberados tras eliminar una tarea intermedia")
    void testDeleteAndRecycleId() {
        Task t1 = new Task("T1", Priority.BAJA);
        t1.setId(dataStore.getNextId()); // 1
        dataStore.addTask(t1);

        Task t2 = new Task("T2", Priority.MEDIA);
        t2.setId(dataStore.getNextId()); // 2
        dataStore.addTask(t2);

        Task t3 = new Task("T3", Priority.ALTA);
        t3.setId(dataStore.getNextId()); // 3
        dataStore.addTask(t3);

        // Eliminar tarea intermedia (ID 2)
        dataStore.delete(2);
        assertNull(dataStore.getTaskById(2));

        // El siguiente ID reciclado debe ser 2
        int nextRecycledId = dataStore.getNextId();
        assertEquals(2, nextRecycledId);

        // Agregamos una nueva tarea que reutiliza el ID 2
        Task t4 = new Task("T4 Reutilizada", Priority.ALTA);
        t4.setId(nextRecycledId);
        dataStore.addTask(t4);

        assertEquals(t4, dataStore.getTaskById(2));
        assertEquals(4, dataStore.getNextId());
    }

    @Test
    @DisplayName("Debe compactar la lista eliminando nulos finales cuando se borra el último elemento")
    void testDeleteTrailingNulls() {
        Task t1 = new Task("T1", Priority.BAJA);
        t1.setId(dataStore.getNextId()); // 1
        dataStore.addTask(t1);

        Task t2 = new Task("T2", Priority.BAJA);
        t2.setId(dataStore.getNextId()); // 2
        dataStore.addTask(t2);

        // Eliminar el último elemento (ID 2)
        dataStore.delete(2);

        // La lista debe haber sido compactada a tamaño 1
        assertEquals(1, dataStore.getTasks().size());
        assertEquals(2, dataStore.getNextId());
    }

    @Test
    @DisplayName("existsTaskByName debe ser insensible a mayúsculas y omitir nulos")
    void testExistsTaskByName() {
        Task t1 = new Task("Hacer Ejercicio", Priority.MEDIA);
        t1.setId(1);
        dataStore.addTask(t1);

        Task t2 = new Task("Comprar Leche", Priority.BAJA);
        t2.setId(2);
        dataStore.addTask(t2);

        assertTrue(dataStore.existsTaskByName("Hacer Ejercicio"));
        assertTrue(dataStore.existsTaskByName("hacer ejercicio"));
        assertTrue(dataStore.existsTaskByName("HACER EJERCICIO"));
        assertFalse(dataStore.existsTaskByName("No Existe"));

        // Eliminar t1 dejando posición en null o compactada
        dataStore.delete(1);
        assertFalse(dataStore.existsTaskByName("Hacer Ejercicio"));
    }
}
