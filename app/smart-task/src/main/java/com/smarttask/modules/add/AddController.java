package com.smarttask.modules.add;

import com.smarttask.console.output.ConsoleOutput;
import com.smarttask.core.models.Task;
import com.smarttask.menu.AddMenu;

public class AddController {

    private static final AddController INSTANCE = new AddController();

    private final AddMenu addMenu;
    private final AddService addService;

    private AddController() {
        this.addMenu = AddMenu.getInstance();
        this.addService = AddServiceImpl.getInstance();
    }

    public static AddController getInstance() {
        return INSTANCE;
    }

    public void addTask() {
        Task task = this.addMenu.show();
        this.addService.addTask(task);
        ConsoleOutput.println("Tarea ingresada correctamente.");
    }

}
