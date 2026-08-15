package com.smarttask.core.exception.exceptions;

/**
 * Excepción lanzada cuando los datos de una tarea no cumplen con los requisitos
 * de validación.
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class InvalidTaskException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje de detalle especificado.
     *
     * @param message Mensaje que describe el motivo del error.
     */
    public InvalidTaskException(String message) {
        super(message);
    }

}
