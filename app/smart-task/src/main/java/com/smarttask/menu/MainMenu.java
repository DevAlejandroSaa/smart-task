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

public class MainMenu {

    private static final MainMenu INSTANCE = new MainMenu();

    private final NumberRangeValidator numberRangeValidator;
    private final ExceptionExecutor exceptionExecutor;
    private final ConsoleInput consoleInput;
    private final ConsoleScreen consoleScreen;

    private final AddController addController;
    private final SearchController searchController;
    private final UpdateController updateController;
    private final DeleteController deleteController;

    private static final String HEADER = "SMART TASK";
    private static final String FOOTER = "Seleccione una opción: ";

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

    public static MainMenu getInstance() {
        return INSTANCE;
    }

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

    private char requestOption() {
        String option = this.consoleInput.read();
        this.numberRangeValidator.validateOption(option, 0, 4);
        return option.charAt(0);
    }

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
