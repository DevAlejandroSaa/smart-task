package com.smarttask.core.exception;

public class GlobalException {

    public static void handle(Exception exception) {
        System.out.println(exception.getMessage());
    }

}
