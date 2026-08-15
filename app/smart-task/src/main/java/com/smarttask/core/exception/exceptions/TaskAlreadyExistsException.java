package com.smarttask.core.exception.exceptions;

/**
 * Excepción lanzada cuando se intenta crear o registrar una tarea cuyo nombre
 * ya existe en el sistema.
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class TaskAlreadyExistsException extends RuntimeException {

    /**
     * Construye una nueva excepción indicando la existencia previa de la tarea.
     *
     * @param message Mensaje con el detalle del conflicto de unicidad.
     */
    public TaskAlreadyExistsException(String message) {
        super(message);
    }

}
