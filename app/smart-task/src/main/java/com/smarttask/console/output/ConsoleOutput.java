package com.smarttask.console.output;

import java.util.List;

import com.smarttask.core.models.Task;

/**
 * Utilidad encargada de imprimir salidas formateadas y mensajes en la consola
 * estándar.
 * <p>
 * Proporciona métodos estáticos para la impresión básica, formateada y
 * visualización
 * estructurada de colecciones de tareas ({@link Task}).
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class ConsoleOutput {

    /**
     * Constructor privado para evitar la instanciación de esta clase utilitaria.
     */
    private ConsoleOutput() {
    }

    /**
     * Imprime un mensaje en la consola sin salto de línea al final.
     *
     * @param message Mensaje de texto a mostrar en consola.
     */
    public static void print(String message) {
        System.out.print(message);
    }

    /**
     * Imprime una cadena formateada en la consola según el formato y los argumentos
     * provistos.
     *
     * @param format Cadena de formato según la especificación de
     *               {@link java.util.Formatter}.
     * @param values Argumentos referenciados por los especificadores de formato.
     */
    public static void print(String format, Object... values) {
        System.out.printf(format, values);
    }

    /**
     * Imprime un mensaje en la consola seguido de un salto de línea.
     *
     * @param message Mensaje de texto a mostrar.
     */
    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Imprime una cadena formateada en la consola agregando automáticamente un
     * salto de línea al final.
     *
     * @param format Cadena de formato según la especificación de
     *               {@link java.util.Formatter}.
     * @param values Argumentos referenciados por los especificadores de formato.
     */
    public static void println(String format, Object... values) {
        System.out.printf(format + "%n", values);
    }

    /**
     * Imprime en consola una lista de tareas con un formato estructurado y legible
     * que detalla ID, Nombre, Prioridad y Estado de cada tarea.
     *
     * @param tasks Lista de tareas {@link Task} a mostrar en pantalla.
     */
    public static void println(List<Task> tasks) {
        tasks.stream()
                .forEach(task -> {
                    println(
                            "ID: %d%n" +
                                    "Nombre: %s%n" +
                                    "Prioridad: %s%n" +
                                    "Estado: %s%n",
                            task.getId(),
                            task.getName(),
                            task.getPriority(),
                            task.getStatus());

                    println("");
                });
    }

}
