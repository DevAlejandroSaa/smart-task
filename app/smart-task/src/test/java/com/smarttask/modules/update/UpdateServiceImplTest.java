package com.smarttask.modules.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.exception.exceptions.TaskNotFoundException;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.core.models.Task;
import com.smarttask.core.repository.TaskRepositoryImpl;
import com.smarttask.testutils.TestDataStoreHelper;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para UpdateServiceImpl")
public class UpdateServiceImplTest {

    private UpdateService updateService;

    @BeforeEach
    void setUp() {
        TestDataStoreHelper.resetDataStore();
        updateService = UpdateServiceImpl.getInstance();
    }

    @Test
    @DisplayName("Debe retornar la misma instancia singleton")
    void testSingleton() {
        UpdateService instance1 = UpdateServiceImpl.getInstance();
        UpdateService instance2 = UpdateServiceImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe actualizar la prioridad y el estado de una tarea existente")
    void testUpdateExistingTask() {
        Task task = new Task("Tarea para actualizar", Priority.BAJA);
        TaskRepositoryImpl.getInstance().save(task);

        int taskId = task.getId();
        updateService.updateTask(taskId, Priority.ALTA, Status.COMPLETADA);

        Task updated = TaskRepositoryImpl.getInstance().findById(taskId);
        assertNotNull(updated);
        assertEquals(Priority.ALTA, updated.getPriority());
        assertEquals(Status.COMPLETADA, updated.getStatus());
    }

    @Test
    @DisplayName("Debe lanzar TaskNotFoundException si la tarea a actualizar no existe")
    void testUpdateNonExistentTask() {
        assertThrows(TaskNotFoundException.class,
                () -> updateService.updateTask(999, Priority.MEDIA, Status.COMPLETADA));
    }
}
