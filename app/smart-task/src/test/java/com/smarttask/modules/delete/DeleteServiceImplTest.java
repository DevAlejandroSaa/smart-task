package com.smarttask.modules.delete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.exception.exceptions.TaskNotFoundException;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Task;
import com.smarttask.core.repository.TaskRepositoryImpl;
import com.smarttask.testutils.TestDataStoreHelper;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para DeleteServiceImpl")
public class DeleteServiceImplTest {

    private DeleteService deleteService;

    @BeforeEach
    void setUp() {
        TestDataStoreHelper.resetDataStore();
        deleteService = DeleteServiceImpl.getInstance();
    }

    @Test
    @DisplayName("Debe retornar la misma instancia singleton")
    void testSingleton() {
        DeleteService instance1 = DeleteServiceImpl.getInstance();
        DeleteService instance2 = DeleteServiceImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe eliminar una tarea existente sin errores")
    void testDeleteExistingTask() {
        Task task = new Task("Tarea para borrar", Priority.BAJA);
        TaskRepositoryImpl.getInstance().save(task);

        int taskId = task.getId();
        assertNotNull(TaskRepositoryImpl.getInstance().findById(taskId));

        deleteService.deleteTask(taskId);
        assertNull(TaskRepositoryImpl.getInstance().findById(taskId));
    }

    @Test
    @DisplayName("Debe lanzar TaskNotFoundException al intentar eliminar una tarea inexistente")
    void testDeleteNonExistentTask() {
        assertThrows(TaskNotFoundException.class, () -> deleteService.deleteTask(999));
    }
}
