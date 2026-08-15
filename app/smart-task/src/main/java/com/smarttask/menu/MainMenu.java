package com.smarttask.menu;

import java.util.List;

import com.smarttask.console.input.ConsoleInput;
import com.smarttask.console.output.ConsoleOutput;
import com.smarttask.console.screen.ConsoleScreen;
import com.smarttask.core.exception.ExceptionExecutor;
import com.smarttask.core.models.Task;
import com.smarttask.modules.add.AddController;
import com.smarttask.modules.delete.DeleteController;
import com.smarttask.modules.search.SearchController;
import com.smarttask.modules.update.UpdateController;
import com.smarttask.validation.NumberRangeValidator;

/**
 * Menú principal interactivo de la aplicación de consola SmartTask.
 * <p>
 * Coordina la visualización de las opciones generales (Salir, Agregar, Listar, Actualizar, Eliminar)
 * y delega la ejecución de cada acción a los respectivos controladores de los módulos.
 * Implementa el patrón de diseño <b>Singleton</b>.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class MainMenu {

    /**
     * Instancia única estática del menú principal (patrón Singleton).
     */
    private static final MainMenu INSTANCE = new MainMenu();

    /**
     * Validador de rangos numéricos de opciones.
     */
    private final NumberRangeValidator numberRangeValidator;

    /**
     * Ejecutor funcional de bucles protegidos contra excepciones.
     */
    private final ExceptionExecutor exceptionExecutor;

    /**
     * Gestor de lectura de entrada de usuario.
     */
    private final ConsoleInput consoleInput;

    /**
     * Gestor de pantalla para renderizado visual.
     */
    private final ConsoleScreen consoleScreen;

    /**
     * Controlador del módulo de adición de tareas.
     */
    private final AddController addController;

    /**
     * Controlador del módulo de búsqueda y listado.
     */
    private final SearchController searchController;

    /**
     * Controlador del módulo de actualización.
     */
    private final UpdateController updateController;

    /**
     * Controlador del módulo de eliminación.
     */
    private final DeleteController deleteController;

    /**
     * Título general del encabezado de la aplicación.
     */
    private static final String HEADER = "SMART TASK";

    /**
     * Mensaje de solicitud de opción al usuario.
     */
    private static final String FOOTER = "Seleccione una opción: ";

    /**
     * Constructor privado que inicializa todas las dependencias singleton de la capa de presentación y controladores.
     */
    private MainMenu() {
        this.numberRangeValidator = NumberRangeValidator.getInstance();
        this.exceptionExecutor = ExceptionExecutor.getInstance();
        this.consoleInput = ConsoleInput.getInstance();
        this.consoleScreen = ConsoleScreen.getInstance();
        this.addController = AddController.getInstance();
        this.searchController = SearchController.getInstance();
        this.updateController = UpdateController.getInstance();
        this.deleteController = DeleteController.getInstance();
    }

    /**
     * Obtiene la instancia singleton de {@link MainMenu}.
     *
     * @return Instancia única de MainMenu.
     */
    public static MainMenu getInstance() {
        return INSTANCE;
    }

    /**
     * Construye y retorna el texto con las opciones del menú principal.
     *
     * @return Cadena formateada con las opciones del menú (0 a 4).
     */
    private String getMenuOptions() {
        StringBuilder options = new StringBuilder();
        options
                .append("0.- Salir\n")
                .append("1.- Agregar tarea\n")
                .append("2.- Listar tareas\n")
                .append("3.- Actualizar tarea\n")
                .append("4.- Eliminar tarea\n");
        return options.toString();
    }

    /**
     * Ejecuta la acción seleccionada según el carácter numérico ingresado.
     *
     * @param option Carácter correspondiente a la opción ('0', '1', '2', '3', '4').
     * @return {@code true} si la aplicación debe continuar ejecutándose, {@code false} si el usuario seleccionó salir ('0').
     */
    private boolean executeOption(char option) {
        switch (option) {
            case '0':
                ConsoleOutput.println("saliendo");
                return false;
            case '1':
                this.addController.addTask();
                return true;
            case '2':
                List<Task> tasks = this.searchController.listTask();

                if (tasks.isEmpty()) {
                    ConsoleOutput.println("Sin datos que mostrar.");
                } else {
                    ConsoleOutput.println(tasks);
                }
                return true;
            case '3':
                this.updateController.updateTask();
                return true;
            case '4':
                this.deleteController.deleteTask();
                return true;
            default:
                return false;
        }
    }

    /**
     * Lee y valida que la opción elegida por el usuario sea un número entre 0 y 4.
     *
     * @return Carácter correspondiente a la opción validada.
     */
    private char requestOption() {
        String option = this.consoleInput.read();
        this.numberRangeValidator.validateOption(option, 0, 4);
        return option.charAt(0);
    }

    /**
     * Inicia el bucle principal de interacción de SmartTask mostrando el menú y respondiendo a las entradas del usuario.
     */
    public void show() {
        boolean running = true;

        while (running) {
            running = this.exceptionExecutor.executeLoop(() -> {
                this.consoleScreen.show(HEADER, this.getMenuOptions(), FOOTER);

                char option = this.requestOption();

                return this.executeOption(option);
            });
        }

        this.consoleInput.close();
    }

}
