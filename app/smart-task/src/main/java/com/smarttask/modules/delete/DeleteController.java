package com.smarttask.modules.delete;

import com.smarttask.console.output.ConsoleOutput;
import com.smarttask.menu.DeleteMenu;

public class DeleteController {

    private static final DeleteController INSTANCE = new DeleteController();

    private final DeleteMenu deleteMenu;
    private final DeleteService deleteService;

    private DeleteController() {
        this.deleteMenu = DeleteMenu.getInstance();
        this.deleteService = DeleteServiceImpl.getInstance();
    }

    public static DeleteController getInstance() {
        return INSTANCE;
    }

    public void deleteTask() {
        int id = this.deleteMenu.show();
        this.deleteService.deleteTask(id);
        ConsoleOutput.println("Registro eliminado.");
    }
}
