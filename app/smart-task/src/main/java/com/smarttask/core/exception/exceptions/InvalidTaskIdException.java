package com.smarttask.core.exception.exceptions;

/**
 * Excepción lanzada cuando un identificador de tarea proporcionado no es
 * válido.
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class InvalidTaskIdException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje descriptivo del fallo.
     *
     * @param message Mensaje que describe la causa del error.
     */
    public InvalidTaskIdException(String message) {
        super(message);
    }

}
