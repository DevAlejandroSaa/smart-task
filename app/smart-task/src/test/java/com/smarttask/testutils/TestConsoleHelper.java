package com.smarttask.testutils;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import com.smarttask.console.input.ConsoleInput;

public class TestConsoleHelper {

    public static void setSimulatedInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            Field instanceField = ConsoleInput.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            ConsoleInput consoleInput = (ConsoleInput) instanceField.get(null);

            if (consoleInput == null) {
                consoleInput = ConsoleInput.getInstance();
            }

            Field scannerField = ConsoleInput.class.getDeclaredField("scanner");
            scannerField.setAccessible(true);
            scannerField.set(consoleInput, new Scanner(in));
        } catch (Exception e) {
            throw new RuntimeException("Error configurando entrada simulada para pruebas", e);
        }
    }

}
