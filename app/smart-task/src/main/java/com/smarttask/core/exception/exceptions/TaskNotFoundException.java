package com.smarttask.core.exception.exceptions;

/**
 * Excepción lanzada cuando una tarea solicitada por su ID no existe en el
 * almacenamiento de datos.
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class TaskNotFoundException extends RuntimeException {

    /**
     * Construye una nueva excepción indicando que la tarea no fue encontrada.
     *
     * @param message Mensaje descriptivo con el detalle del error.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }

}
