package com.smarttask.menu;

import com.smarttask.console.input.ConsoleInput;
import com.smarttask.console.screen.ConsoleScreen;
import com.smarttask.core.exception.ExceptionExecutor;
import com.smarttask.validation.NumberRangeValidator;

/**
 * Menú interactivo de consola para la captura del ID de una tarea a eliminar.
 * <p>
 * Solicita el identificador de la tarea, valida que sea un número entero positivo mayor a cero
 * y ejecuta el flujo en un bucle seguro con manejo de excepciones.
 * Implementa el patrón de diseño <b>Singleton</b>.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class DeleteMenu {

    /**
     * Instancia única estática del menú de eliminación (patrón Singleton).
     */
    private static final DeleteMenu INSTANCE = new DeleteMenu();

    /**
     * Gestor de pantalla de consola.
     */
    private final ConsoleScreen consoleScreen;

    /**
     * Gestor de lectura de entrada de usuario.
     */
    private final ConsoleInput consoleInput;

    /**
     * Ejecutor de reintentos seguros.
     */
    private final ExceptionExecutor exceptionExecutor;

    /**
     * Validador de rangos numéricos.
     */
    private final NumberRangeValidator numberRangeValidator;

    /**
     * Encabezado visual del menú de eliminación.
     */
    private static final String HEADER = "ELIMINAR TAREA";

    /**
     * Constructor privado que inicializa los componentes singleton correspondientes.
     */
    private DeleteMenu() {
        this.consoleScreen = ConsoleScreen.getInstance();
        this.consoleInput = ConsoleInput.getInstance();
        this.exceptionExecutor = ExceptionExecutor.getInstance();
        this.numberRangeValidator = NumberRangeValidator.getInstance();
    }

    /**
     * Obtiene la instancia singleton de {@link DeleteMenu}.
     *
     * @return Instancia única de DeleteMenu.
     */
    public static DeleteMenu getInstance() {
        return INSTANCE;
    }

    /**
     * Muestra la solicitud de ID en la consola y retorna el entero ingresado tras validar que sea positivo.
     *
     * @return El identificador numérico de la tarea ingresado por el usuario.
     */
    public int show() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Ingrese el ID de la tarea: ");

            String id = this.consoleInput.read();
            this.numberRangeValidator.validatePositiveInteger(id);

            return Integer.parseInt(id);
        });
    }

}
