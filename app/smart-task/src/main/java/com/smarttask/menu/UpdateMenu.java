package com.smarttask.menu;

import java.util.ArrayList;
import java.util.List;

import com.smarttask.console.input.ConsoleInput;
import com.smarttask.console.screen.ConsoleScreen;
import com.smarttask.core.exception.ExceptionExecutor;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.validation.NumberRangeValidator;

public class UpdateMenu {

    private static final UpdateMenu INSTANCE = new UpdateMenu();

    private final ConsoleScreen consoleScreen;
    private final ConsoleInput consoleInput;
    private final ExceptionExecutor exceptionExecutor;
    private final NumberRangeValidator numberRangeValidator;

    private static final String HEADER = "ACTUALIZAR TAREA";
    private static final String FOOTER = "Seleccione una opción: ";

    private UpdateMenu() {
        this.consoleScreen = ConsoleScreen.getInstance();
        this.consoleInput = ConsoleInput.getInstance();
        this.exceptionExecutor = ExceptionExecutor.getInstance();
        this.numberRangeValidator = NumberRangeValidator.getInstance();
    }

    public static UpdateMenu getInstance() {
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

    private String getStatusOptions() {
        StringBuilder options = new StringBuilder();
        options
                .append("1.- Activa\n")
                .append("2.- Completada\n");
        return options.toString();
    }

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

    private char requestOption(int min, int max) {
        String option = this.consoleInput.read();
        this.numberRangeValidator.validateOption(option, min, max);
        return option.charAt(0);
    }

    private int getId() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Ingrese el ID de la tarea: ");
            String id = this.consoleInput.read();
            this.numberRangeValidator.validatePositiveInteger(id);
            return Integer.parseInt(id);
        });
    }

    private Priority getPriority() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Seleccione la prioridad:\n" + this.getPriorityOptions(), FOOTER);
            char option = this.requestOption(1, 3);
            return this.getPriorityOption(option);
        });
    }

    private Status getStatus() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Seleccione el estado:\n" + this.getStatusOptions(), FOOTER);
            char option = this.requestOption(1, 2);
            return this.getStatusOption(option);
        });
    }

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
