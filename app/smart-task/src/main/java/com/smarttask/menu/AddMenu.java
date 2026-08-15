package com.smarttask.menu;

import com.smarttask.console.input.ConsoleInput;
import com.smarttask.console.screen.ConsoleScreen;
import com.smarttask.core.exception.ExceptionExecutor;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Task;
import com.smarttask.validation.NumberRangeValidator;

/**
 * Menú interactivo de consola para la captura y creación de nuevas tareas.
 * <p>
 * Solicita al usuario el nombre y el nivel de prioridad (Baja, Media o Alta)
 * validando los rangos numéricos mediante {@link NumberRangeValidator} y
 * reintentando la captura en caso de error mediante {@link ExceptionExecutor}.
 * Implementa el patrón de diseño <b>Singleton</b>.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class AddMenu {

    /**
     * Instancia única estática del menú (patrón Singleton).
     */
    private static final AddMenu INSTANCE = new AddMenu();

    /**
     * Gestor de pantalla para renderizar encabezados y menús.
     */
    private final ConsoleScreen consoleScreen;

    /**
     * Gestor de lectura de entrada de usuario.
     */
    private final ConsoleInput consoleInput;

    /**
     * Ejecutor de bucles seguros contra excepciones.
     */
    private final ExceptionExecutor exceptionExecutor;

    /**
     * Validador de rangos numéricos de opciones.
     */
    private final NumberRangeValidator numberRangeValidator;

    /**
     * Título del encabezado del menú.
     */
    private static final String HEADER = "AGREGAR TAREA";

    /**
     * Mensaje de solicitud de opción al usuario.
     */
    private static final String FOOTER = "Seleccione una opción: ";

    /**
     * Constructor privado que inicializa los componentes singleton requeridos.
     */
    private AddMenu() {
        this.consoleScreen = ConsoleScreen.getInstance();
        this.consoleInput = ConsoleInput.getInstance();
        this.exceptionExecutor = ExceptionExecutor.getInstance();
        this.numberRangeValidator = NumberRangeValidator.getInstance();
    }

    /**
     * Obtiene la instancia singleton de {@link AddMenu}.
     *
     * @return Instancia única de AddMenu.
     */
    public static AddMenu getInstance() {
        return INSTANCE;
    }

    /**
     * Construye y retorna la cadena con las opciones de prioridad disponibles.
     *
     * @return Texto estructurado con el menú de prioridades (1.- Baja, 2.- Media, 3.- Alta).
     */
    private String getPriorityOptions() {
        StringBuilder options = new StringBuilder();
        options
                .append("1.- Baja\n")
                .append("2.- Media\n")
                .append("3.- Alta\n");
        return options.toString();
    }

    /**
     * Convierte el carácter numérico de la opción ingresada al enum {@link Priority} correspondiente.
     *
     * @param option Carácter representativo de la opción elegida ('1', '2' o '3').
     * @return Valor del enum {@link Priority} mapeado.
     */
    private Priority getOption(char option) {
        switch (option) {
            case '1':
                return Priority.BAJA;
            case '2':
                return Priority.MEDIA;
            case '3':
                return Priority.ALTA;
            default:
                return Priority.BAJA;
        }
    }

    /**
     * Lee y valida la opción de prioridad introducida por el usuario dentro del rango permitido.
     *
     * @param min Valor mínimo aceptado.
     * @param max Valor máximo aceptado.
     * @return Primer carácter de la opción validada.
     */
    private char requestOption(int min, int max) {
        String option = this.consoleInput.read();
        this.numberRangeValidator.validateOption(option, min, max);
        return option.charAt(0);
    }

    /**
     * Despliega el menú de prioridades y captura la selección del usuario en un bucle seguro.
     *
     * @return Nivel de prioridad {@link Priority} seleccionado.
     */
    private Priority getPriority() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Seleccione la prioridad:\n" + this.getPriorityOptions(), FOOTER);
            char option = this.requestOption(1, 3);
            return this.getOption(option);
        });
    }

    /**
     * Despliega el flujo completo de captura para una nueva tarea (nombre y prioridad).
     *
     * @return Nueva instancia de {@link Task} con los datos capturados y estado activo.
     */
    public Task show() {
        Task task = new Task(null, null);

        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Ingrese el nombre de la tarea: ");
            task.setName(this.consoleInput.read());

            task.setPriority(this.getPriority());

            return task;
        });
    }

}
