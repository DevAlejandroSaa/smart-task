package com.smarttask.menu;

import com.smarttask.console.input.ConsoleInput;
import com.smarttask.console.screen.ConsoleScreen;
import com.smarttask.core.exception.ExceptionExecutor;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Task;
import com.smarttask.validation.NumberRangeValidator;

public class AddMenu {

    private static final AddMenu INSTANCE = new AddMenu();

    private final ConsoleScreen consoleScreen;
    private final ConsoleInput consoleInput;
    private final ExceptionExecutor exceptionExecutor;
    private final NumberRangeValidator numberRangeValidator;

    private static final String HEADER = "AGREGAR TAREA";
    private static final String FOOTER = "Seleccione una opción: ";

    private AddMenu() {
        this.consoleScreen = ConsoleScreen.getInstance();
        this.consoleInput = ConsoleInput.getInstance();
        this.exceptionExecutor = ExceptionExecutor.getInstance();
        this.numberRangeValidator = NumberRangeValidator.getInstance();
    }

    public static AddMenu getInstance() {
        return INSTANCE;
    }

    private String getPriorityOptions() {
        StringBuilder options = new StringBuilder();
        options
                .append("1.- Baja\n")
                .append("2.- Media\n")
                .append("3.- Alta\n");
        return options.toString();
    }

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

    private char requestOption(int min, int max) {
        String option = this.consoleInput.read();
        this.numberRangeValidator.validateOption(option, min, max);
        return option.charAt(0);
    }

    private Priority getPriority() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Seleccione la prioridad:\n" + this.getPriorityOptions(), FOOTER);
            char option = this.requestOption(1, 3);
            return this.getOption(option);
        });
    }

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
