package com.smarttask.core.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.exception.exceptions.TaskAlreadyExistsException;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.core.models.Task;
import com.smarttask.testutils.TestDataStoreHelper;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para TaskRepositoryImpl")
public class TaskRepositoryImplTest {

    private TaskRepository repository;

    @BeforeEach
    void setUp() {
        TestDataStoreHelper.resetDataStore();
        repository = TaskRepositoryImpl.getInstance();
    }

    @Test
    @DisplayName("Debe retornar la misma instancia singleton")
    void testSingleton() {
        TaskRepository instance1 = TaskRepositoryImpl.getInstance();
        TaskRepository instance2 = TaskRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe guardar una tarea asignándole un ID autogenerado")
    void testSave() {
        Task task = new Task("Organizar reunión", Priority.ALTA);
        repository.save(task);

        assertEquals(1, task.getId());
        Task retrieved = repository.findById(1);
        assertNotNull(retrieved);
        assertEquals("Organizar reunión", retrieved.getName());
    }

    @Test
    @DisplayName("Debe lanzar TaskAlreadyExistsException si se intenta guardar una tarea con nombre duplicado")
    void testSaveDuplicateThrowsException() {
        Task task1 = new Task("Duplicada", Priority.BAJA);
        repository.save(task1);

        Task task2 = new Task("duplicada", Priority.ALTA);
        assertThrows(TaskAlreadyExistsException.class, () -> repository.save(task2));
    }

    @Test
    @DisplayName("Debe listar todas las tareas persistidas")
    void testFindAll() {
        assertEquals(0, repository.findAll().size());

        Task task1 = new Task("T1", Priority.BAJA);
        Task task2 = new Task("T2", Priority.MEDIA);
        repository.save(task1);
        repository.save(task2);

        List<Task> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(task1));
        assertTrue(all.contains(task2));
    }

    @Test
    @DisplayName("Debe retornar null cuando findById no encuentra la tarea")
    void testFindByIdNotFound() {
        assertNull(repository.findById(999));
    }

    @Test
    @DisplayName("Debe actualizar los datos de la tarea en el repositorio")
    void testUpdate() {
        Task task = new Task("Original", Priority.BAJA);
        repository.save(task);

        task.setName("Actualizado");
        task.setPriority(Priority.ALTA);
        task.setStatus(Status.COMPLETADA);
        repository.update(task);

        Task retrieved = repository.findById(task.getId());
        assertEquals("Actualizado", retrieved.getName());
        assertEquals(Priority.ALTA, retrieved.getPriority());
        assertEquals(Status.COMPLETADA, retrieved.getStatus());
    }

    @Test
    @DisplayName("Debe eliminar una tarea por su ID")
    void testDelete() {
        Task task = new Task("Eliminar", Priority.MEDIA);
        repository.save(task);

        repository.delete(task.getId());
        assertNull(repository.findById(task.getId()));
    }
}
