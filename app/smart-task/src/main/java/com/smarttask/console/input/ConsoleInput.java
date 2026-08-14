package com.smarttask.console.input;

import java.util.Scanner;

public class ConsoleInput {

    private static ConsoleInput instance;

    private final Scanner scanner;

    private ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    public static ConsoleInput getInstance() {
        if (instance == null) {
            instance = new ConsoleInput();
        }

        return instance;
    }

    public String read() {
        return this.scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }

}
