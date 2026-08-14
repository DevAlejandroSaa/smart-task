package com.smarttask.menu;

import com.smarttask.console.input.ConsoleInput;
import com.smarttask.console.screen.ConsoleScreen;
import com.smarttask.core.exception.ExceptionExecutor;
import com.smarttask.validation.NumberRangeValidator;

public class DeleteMenu {

    private static final DeleteMenu INSTANCE = new DeleteMenu();

    private final ConsoleScreen consoleScreen;
    private final ConsoleInput consoleInput;
    private final ExceptionExecutor exceptionExecutor;
    private final NumberRangeValidator numberRangeValidator;

    private static final String HEADER = "ELIMINAR TAREA";

    private DeleteMenu() {
        this.consoleScreen = ConsoleScreen.getInstance();
        this.consoleInput = ConsoleInput.getInstance();
        this.exceptionExecutor = ExceptionExecutor.getInstance();
        this.numberRangeValidator = NumberRangeValidator.getInstance();
    }

    public static DeleteMenu getInstance() {
        return INSTANCE;
    }

    public int show() {
        return this.exceptionExecutor.executeLoop(() -> {
            this.consoleScreen.show(HEADER, "Ingrese el ID de la tarea: ");

            String id = this.consoleInput.read();
            this.numberRangeValidator.validatePositiveInteger(id);

            return Integer.parseInt(id);
        });
    }

}
