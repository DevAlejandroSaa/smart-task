package com.smarttask.core.exception;

/**
 * Manejador global de excepciones para la aplicación.
 * <p>
 * Centraliza la presentación del mensaje de error de cualquier excepción
 * ocurrida en el sistema.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class GlobalException {

    /**
     * Procesa una excepción imprimiendo su mensaje descriptivo en la consola.
     *
     * @param exception Objeto {@link Exception} a gestionar.
     */
    public static void handle(Exception exception) {
        System.out.println(exception.getMessage());
    }

}
