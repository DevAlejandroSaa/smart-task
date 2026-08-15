package com.smarttask.menu;

import java.util.ArrayList;
import java.util.List;

import com.smarttask.console.input.ConsoleInput;
import com.smarttask.console.screen.ConsoleScreen;
import com.smarttask.core.exception.ExceptionExecutor;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.validation.NumberRangeValidator;

/**
 * Menú interactivo de consola para la captura de datos necesarios en la actualización de una tarea.
 * <p>
 * Solicita de forma secuencial el ID de la tarea, la nueva prioridad y el nuevo estado (Activa o Completada),
 * validando cada valor con {@link NumberRangeValidator}.
 * Implementa el patrón de diseño <b>Singleton</b>.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class UpdateMenu {

    /**
     * Instancia única estática del menú de actualización (patrón Singleton).
     */
    private static final UpdateMenu INSTANCE = new UpdateMenu();

    /**
     * Gestor de pantalla para renderizar menús.
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
     * Validador de rangos numéricos de opciones.
     */
    private final NumberRangeValidator numberRangeValidator;

    /**
     * Encabezado visual del menú de actualización.
     */
    private static final String HEADER = "ACTUALIZAR TAREA";

    /**
     * Mensaje de solicitud de opción al usuario.
     */
    private static final String FOOTER = "Seleccione una opción: ";

    /**
     * Constructor privado que inicializa los componentes requeridos.
     */
    private UpdateMenu() {
        this.consoleScreen = ConsoleScreen.getInstance();
        this.consoleInput = ConsoleInput.getInstance();
        this.exceptionExecutor = ExceptionExecutor.getInstance();
        this.numberRangeValidator = NumberRangeValidator.getInstance();
    }

    /**
     * Obtiene la instancia singleton de {@link UpdateMenu}.
     *
     * @return Instancia única de UpdateMenu.
     */
    public static UpdateMenu getInstance() {
        return INSTANCE;
    }

    /**
     * Construye y retorna las opciones de prioridad.
     *
     * @return Cadena con las opciones de prioridad (1.- Baja, 2.- Media, 3.- Alta).
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
     * Construye y retorna las opciones de estado.
     *
     * @return Cadena con las opciones de estado (1.- Activa, 2.- Completada).
     */
    private String getStatusOptions() {
        StringBuilder options = new StringBuilder();
        options
                .append("1.- Activa\n")
                .append("2.- Completada\n");
        return options.toString();
    }

    /**
     * Mapea el carácter de opción ingresado al enum {@link Priority}.
     *
     * @param option Carácter representativo ('1', '2' o '3').
     * @return Prioridad correspondiente.
     */
    private Priority getPriorityOption(char option) {
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
     * Mapea el carácter de opción ingresado al enum {@link Status}.
     *
     * @param option Carácter representativo ('1' o '2').
     * @return Estado correspondiente.
     */
    private Status getStatusOption(char option) {
        switch (option) {
            case '1':
                return Status.ACTIVA;
            case '2':
                return Status.COMPLETADA;
            default:
                return Status.ACTIVA;
        }
    }

    /**
     * Lee y valida la opción del usuario dentro de un rango numérico.
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
     * Solicita y valida el identificador de la tarea a actualizar.
     *
     * @return ID entero positivo de la tarea.
     */
    private int getId() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Ingrese el ID de la tarea: ");
            String id = this.consoleInput.read();
            this.numberRangeValidator.validatePositiveInteger(id);
            return Integer.parseInt(id);
        });
    }

    /**
     * Solicita y retorna la nueva prioridad elegida por el usuario.
     *
     * @return Objeto {@link Priority} seleccionado.
     */
    private Priority getPriority() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Seleccione la prioridad:\n" + this.getPriorityOptions(), FOOTER);
            char option = this.requestOption(1, 3);
            return this.getPriorityOption(option);
        });
    }

    /**
     * Solicita y retorna el nuevo estado elegido por el usuario.
     *
     * @return Objeto {@link Status} seleccionado.
     */
    private Status getStatus() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Seleccione el estado:\n" + this.getStatusOptions(), FOOTER);
            char option = this.requestOption(1, 2);
            return this.getStatusOption(option);
        });
    }

    /**
     * Despliega el flujo completo de captura para la actualización de una tarea.
     *
     * @return Lista heterogénea con 3 elementos: [0] ID (Integer), [1] Prioridad (Priority), [2] Estado (Status).
     */
    public List<Object> show() {
        return this.exceptionExecutor.executeLoop(() -> {
            List<Object> values = new ArrayList<Object>();

            values.add(this.getId());
            values.add(this.getPriority());
            values.add(this.getStatus());

            return values;
        });
    }

}
