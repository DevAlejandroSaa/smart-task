package com.smarttask.console.input;

import java.util.Scanner;

/**
 * Gestor de lectura de entradas del usuario por consola.
 * <p>
 * Implementa el patrón <b>Singleton</b> para centralizar el uso de una única
 * instancia de {@link Scanner} asociada a {@code System.in} a lo largo de
 * toda la ejecución del ciclo de vida de la aplicación.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class ConsoleInput {

    /**
     * Única instancia singleton de la clase ConsoleInput.
     */
    private static ConsoleInput instance;

    /**
     * Objeto Scanner para capturar datos de entrada desde la consola estándar.
     */
    private final Scanner scanner;

    /**
     * Constructor privado para prevenir la instanciación externa (patrón
     * Singleton).
     */
    private ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Obtiene la instancia única de {@link ConsoleInput}.
     *
     * @return La instancia singleton de ConsoleInput.
     */
    public static ConsoleInput getInstance() {
        if (instance == null) {
            instance = new ConsoleInput();
        }

        return instance;
    }

    /**
     * Lee la siguiente línea completa de texto introducida por el usuario en la
     * consola.
     *
     * @return La cadena de caracteres leída desde la entrada estándar.
     */
    public String read() {
        return this.scanner.nextLine();
    }

    /**
     * Cierra el lector {@link Scanner} subyacente y libera los recursos del flujo
     * de entrada.
     */
    public void close() {
        this.scanner.close();
    }

}
