package com.smarttask.modules.add;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.database.DataStore;
import com.smarttask.core.exception.exceptions.TaskAlreadyExistsException;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.core.models.Task;
import com.smarttask.testutils.TestDataStoreHelper;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para AddServiceImpl")
public class AddServiceImplTest {

    private AddService addService;

    @BeforeEach
    void setUp() {
        TestDataStoreHelper.resetDataStore();
        addService = AddServiceImpl.getInstance();
    }

    @Test
    @DisplayName("Debe retornar la misma instancia singleton")
    void testSingleton() {
        AddService instance1 = AddServiceImpl.getInstance();
        AddService instance2 = AddServiceImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe agregar una tarea correctamente")
    void testAddTask() {
        Task task = new Task("Completar módulo", Priority.ALTA);
        addService.addTask(task);

        assertEquals(1, task.getId());
        Task stored = DataStore.getInstance().getTaskById(1);
        assertNotNull(stored);
        assertEquals("Completar módulo", stored.getName());
        assertEquals(Priority.ALTA, stored.getPriority());
        assertEquals(Status.ACTIVA, stored.getStatus());
    }

    @Test
    @DisplayName("Debe lanzar TaskAlreadyExistsException si se agrega una tarea con el mismo nombre")
    void testAddDuplicateTask() {
        Task task1 = new Task("Reunión diaria", Priority.MEDIA);
        addService.addTask(task1);

        Task task2 = new Task("reunión diaria", Priority.BAJA);
        assertThrows(TaskAlreadyExistsException.class, () -> addService.addTask(task2));
    }
}
