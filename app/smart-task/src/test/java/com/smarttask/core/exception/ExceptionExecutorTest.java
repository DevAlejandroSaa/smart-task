package com.smarttask.core.exception;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para ExceptionExecutor")
public class ExceptionExecutorTest {

    private final ExceptionExecutor executor = ExceptionExecutor.getInstance();

    @Test
    @DisplayName("Debe retornar la misma instancia singleton")
    void testSingleton() {
        ExceptionExecutor instance1 = ExceptionExecutor.getInstance();
        ExceptionExecutor instance2 = ExceptionExecutor.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("execute(Runnable) debe ejecutar la acción correctamente")
    void testExecuteRunnableSuccess() {
        AtomicInteger counter = new AtomicInteger(0);
        executor.execute(counter::incrementAndGet);

        assertEquals(1, counter.get());
    }

    @Test
    @DisplayName("execute(Runnable) debe capturar excepciones sin propagarlas")
    void testExecuteRunnableWithException() {
        assertDoesNotThrow(() -> {
            executor.execute(() -> {
                throw new RuntimeException("Fallo controlado");
            });
        });
    }

    @Test
    @DisplayName("execute(Supplier) debe retornar el valor suministrado si es exitoso")
    void testExecuteSupplierSuccess() {
        String result = executor.execute(() -> "Resultado Exitoso");
        assertEquals("Resultado Exitoso", result);
    }

    @Test
    @DisplayName("execute(Supplier) debe retornar null cuando la acción arroja una excepción")
    void testExecuteSupplierWithException() {
        String result = executor.execute(() -> {
            throw new RuntimeException("Fallo en supplier");
        });
        assertNull(result);
    }

    @Test
    @DisplayName("executeLoop(Runnable) debe reintentar hasta que la acción sea exitosa")
    void testExecuteLoopRunnableRetry() {
        AtomicInteger attempts = new AtomicInteger(0);

        executor.executeLoop(() -> {
            if (attempts.incrementAndGet() < 3) {
                throw new RuntimeException("Intento fallido");
            }
        });

        assertEquals(3, attempts.get());
    }

    @Test
    @DisplayName("executeLoop(Supplier) debe reintentar hasta que el proveedor retorne un resultado válido")
    void testExecuteLoopSupplierRetry() {
        AtomicInteger attempts = new AtomicInteger(0);

        String result = executor.executeLoop(() -> {
            if (attempts.incrementAndGet() < 3) {
                throw new RuntimeException("Intento fallido");
            }
            return "Valor Obtenido";
        });

        assertEquals(3, attempts.get());
        assertEquals("Valor Obtenido", result);
    }
}
