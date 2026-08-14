package com.smarttask.console.output;

import java.util.List;

import com.smarttask.core.models.Task;

public class ConsoleOutput {

    private ConsoleOutput() {
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void print(String format, Object... values) {
        System.out.printf(format, values);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void println(String format, Object... values) {
        System.out.printf(format + "%n", values);
    }

    public static void println(List<Task> tasks) {
        tasks.stream()
                .forEach(task -> {
                    println(
                            "ID: %d%n" +
                                    "Nombre: %s%n" +
                                    "Prioridad: %s%n" +
                                    "Estado: %s%n",
                            task.getId(),
                            task.getName(),
                            task.getPriority(),
                            task.getStatus());

                    println("");
                });
    }

}
