package com.smarttask.modules.search;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Task;
import com.smarttask.core.repository.TaskRepositoryImpl;
import com.smarttask.testutils.TestDataStoreHelper;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para SearchServiceImpl")
public class SearchServiceImplTest {

    private SearchService searchService;

    @BeforeEach
    void setUp() {
        TestDataStoreHelper.resetDataStore();
        searchService = SearchServiceImpl.getInstance();
    }

    @Test
    @DisplayName("Debe retornar la misma instancia singleton")
    void testSingleton() {
        SearchService instance1 = SearchServiceImpl.getInstance();
        SearchService instance2 = SearchServiceImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Debe retornar lista vacía cuando no hay tareas registradas")
    void testListTaskEmpty() {
        List<Task> tasks = searchService.listTask();
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("Debe retornar la lista completa de tareas registradas")
    void testListTaskWithElements() {
        Task task1 = new Task("Tarea 1", Priority.BAJA);
        Task task2 = new Task("Tarea 2", Priority.ALTA);
        TaskRepositoryImpl.getInstance().save(task1);
        TaskRepositoryImpl.getInstance().save(task2);

        List<Task> tasks = searchService.listTask();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }
}
