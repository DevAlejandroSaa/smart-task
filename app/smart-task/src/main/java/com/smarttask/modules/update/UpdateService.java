package com.smarttask.modules.update;

import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;

public interface UpdateService {

    public void updateTask(int id, Priority priority, Status status);

}
