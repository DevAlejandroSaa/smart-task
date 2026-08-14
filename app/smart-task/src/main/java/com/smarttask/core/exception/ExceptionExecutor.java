package com.smarttask.core.exception;

import java.util.function.Supplier;

public class ExceptionExecutor {

    private static ExceptionExecutor instance;

    public static ExceptionExecutor getInstance() {
        if (instance == null) {
            instance = new ExceptionExecutor();
        }

        return instance;
    }

    public void execute(Runnable action) {
        try {
            action.run();
        } catch (Exception exception) {
            GlobalException.handle(exception);
        }
    }

    public <T> T execute(Supplier<T> action) {
        try {
            return action.get();
        } catch (Exception exception) {
            GlobalException.handle(exception);
            return null;
        }
    }

    public void executeLoop(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (Exception exception) {
                GlobalException.handle(exception);
            }
        }
    }

    public <T> T executeLoop(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (Exception exception) {
                GlobalException.handle(exception);
            }
        }
    }

}
