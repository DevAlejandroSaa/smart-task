package com.smarttask.modules.update;

import java.util.List;

import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.menu.UpdateMenu;

public class UpdateController {

    private static final UpdateController INSTANCE = new UpdateController();

    private final UpdateMenu updateMenu;
    private final UpdateService updateService;

    private UpdateController() {
        this.updateMenu = UpdateMenu.getInstance();
        this.updateService = UpdateServiceImpl.getInstance();
    }

    public static UpdateController getInstance() {
        return INSTANCE;
    }

    public void updateTask() {
        List<Object> updateTask = this.updateMenu.show();
        int id = (int) updateTask.get(0);
        Priority priority = (Priority) updateTask.get(1);
        Status status = (Status) updateTask.get(2);
        this.updateService.updateTask(id, priority, status);
    }

}
