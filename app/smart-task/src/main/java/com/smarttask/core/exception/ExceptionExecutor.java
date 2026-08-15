package com.smarttask.core.exception;

import java.util.function.Supplier;

/**
 * Ejecutor funcional centralizado para el manejo de excepciones y bucles de
 * reintento en tiempo de ejecución.
 * <p>
 * Facilita la ejecución segura de acciones {@link Runnable} o proveedores
 * {@link Supplier}
 * atrapando excepciones no controladas y delegándolas a
 * {@link GlobalException}.
 * Implementa el patrón de diseño <b>Singleton</b>.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class ExceptionExecutor {

    /**
     * Única instancia singleton de ExceptionExecutor.
     */
    private static ExceptionExecutor instance;

    /**
     * Constructor privado por defecto (patrón Singleton).
     */
    private ExceptionExecutor() {
    }

    /**
     * Obtiene la instancia única de {@link ExceptionExecutor}.
     *
     * @return Instancia singleton de ExceptionExecutor.
     */
    public static ExceptionExecutor getInstance() {
        if (instance == null) {
            instance = new ExceptionExecutor();
        }

        return instance;
    }

    /**
     * Ejecuta una acción {@link Runnable} de forma segura capturando cualquier
     * excepción.
     *
     * @param action Acción a ejecutar.
     */
    public void execute(Runnable action) {
        try {
            action.run();
        } catch (Exception exception) {
            GlobalException.handle(exception);
        }
    }

    /**
     * Ejecuta una función que produce un valor ({@link Supplier}) capturando
     * excepciones.
     *
     * @param <T>    Tipo de dato devuelto por la acción.
     * @param action Proveedor con la lógica a ejecutar.
     * @return El valor producido por la acción, o {@code null} en caso de error.
     */
    public <T> T execute(Supplier<T> action) {
        try {
            return action.get();
        } catch (Exception exception) {
            GlobalException.handle(exception);
            return null;
        }
    }

    /**
     * Ejecuta una acción en un bucle continuo hasta que se complete
     * satisfactoriamente sin lanzar excepciones.
     *
     * @param action Acción a reintentar hasta que sea exitosa.
     */
    public void executeLoop(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (Exception exception) {
                GlobalException.handle(exception);
            }
        }
    }

    /**
     * Ejecuta un proveedor en un bucle continuo hasta que retorne un resultado
     * válido sin lanzar excepciones.
     *
     * @param <T>    Tipo de dato devuelto por la acción.
     * @param action Proveedor con la lógica a reintentar.
     * @return El valor obtenido al completarse la acción exitosamente.
     */
    public <T> T executeLoop(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (Exception exception) {
                GlobalException.handle(exception);
            }
        }
    }

}
