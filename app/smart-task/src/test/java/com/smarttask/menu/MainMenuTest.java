package com.smarttask.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.core.models.Task;
import com.smarttask.core.repository.TaskRepository;
import com.smarttask.core.repository.TaskRepositoryImpl;
import com.smarttask.testutils.TestConsoleHelper;
import com.smarttask.testutils.TestDataStoreHelper;

@DisplayName("Pruebas unitarias para MainMenu")
public class MainMenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private final TaskRepository taskRepository = TaskRepositoryImpl.getInstance();

    @BeforeEach
    void setUp() {
        TestDataStoreHelper.resetDataStore();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    @DisplayName("Debe retornar siempre la misma instancia singleton")
    void testSingletonInstance() {
        MainMenu instance1 = MainMenu.getInstance();
        MainMenu instance2 = MainMenu.getInstance();
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Opción 0: Debe imprimir mensaje de salida y terminar el bucle")
    void testOption0Exit() {
        TestConsoleHelper.setSimulatedInput("0\n");

        MainMenu menu = MainMenu.getInstance();
        menu.show();

        String output = outContent.toString();
        assertTrue(output.contains("SMART TASK"));
        assertTrue(output.contains("0.- Salir"));
        assertTrue(output.contains("saliendo"));
    }

    @Test
    @DisplayName("Opción 2: Debe listar tareas vacías y mostrar 'Sin datos que mostrar.'")
    void testOption2ListEmpty() {
        TestConsoleHelper.setSimulatedInput("2\n0\n");

        MainMenu menu = MainMenu.getInstance();
        menu.show();

        String output = outContent.toString();
        assertTrue(output.contains("Sin datos que mostrar."));
        assertTrue(output.contains("saliendo"));
    }

    @Test
    @DisplayName("Opción 2: Debe listar las tareas existentes")
    void testOption2ListWithTasks() {
        taskRepository.save(new Task("Tarea Existente", Priority.ALTA));

        TestConsoleHelper.setSimulatedInput("2\n0\n");

        MainMenu menu = MainMenu.getInstance();
        menu.show();

        String output = outContent.toString();
        assertTrue(output.contains("Tarea Existente"));
        assertTrue(output.contains("ALTA"));
    }

    @Test
    @DisplayName("Opción 1: Debe ejecutar el flujo de adición de tarea y persistirla")
    void testOption1AddTask() {
        // Opción 1 (Agregar) -> Nombre -> Prioridad (2 = MEDIA) -> Opción 2 (Listar) ->
        // Opción 0 (Salir)
        TestConsoleHelper.setSimulatedInput("1\nTarea Menu Principal\n2\n2\n0\n");

        MainMenu menu = MainMenu.getInstance();
        menu.show();

        String output = outContent.toString();
        assertTrue(output.contains("Tarea ingresada correctamente."));
        assertTrue(output.contains("Tarea Menu Principal"));
        assertEquals(1, taskRepository.findAll().size());
        assertEquals("Tarea Menu Principal", taskRepository.findById(1).getName());
    }

    @Test
    @DisplayName("Opción 3: Debe ejecutar el flujo de actualización de tarea")
    void testOption3UpdateTask() {
        taskRepository.save(new Task("Tarea a actualizar", Priority.BAJA));

        // Opción 3 (Actualizar) -> ID 1 -> Prioridad 3 (ALTA) -> Estado 2 (COMPLETADA)
        // -> Opción 0 (Salir)
        TestConsoleHelper.setSimulatedInput("3\n1\n3\n2\n0\n");

        MainMenu menu = MainMenu.getInstance();
        menu.show();

        Task updatedTask = taskRepository.findById(1);
        assertNotNull(updatedTask);
        assertEquals(Priority.ALTA, updatedTask.getPriority());
        assertEquals(Status.COMPLETADA, updatedTask.getStatus());
    }

    @Test
    @DisplayName("Opción 4: Debe ejecutar el flujo de eliminación de tarea")
    void testOption4DeleteTask() {
        taskRepository.save(new Task("Tarea a eliminar", Priority.MEDIA));

        // Opción 4 (Eliminar) -> ID 1 -> Opción 2 (Listar para verificar vacía) ->
        // Opción 0 (Salir)
        TestConsoleHelper.setSimulatedInput("4\n1\n2\n0\n");

        MainMenu menu = MainMenu.getInstance();
        menu.show();

        String output = outContent.toString();
        assertTrue(output.contains("Registro eliminado."));
        assertTrue(output.contains("Sin datos que mostrar."));
        assertTrue(taskRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Debe manejar opciones inválidas y reintentar hasta recibir una opción válida")
    void testInvalidOptionRetry() {
        // Ingresa opción inválida "9", texto "abc", y finalmente "0" (Salir)
        TestConsoleHelper.setSimulatedInput("9\nabc\n0\n");

        MainMenu menu = MainMenu.getInstance();
        menu.show();

        String output = outContent.toString();
        assertTrue(output.contains("saliendo"));
    }
}
