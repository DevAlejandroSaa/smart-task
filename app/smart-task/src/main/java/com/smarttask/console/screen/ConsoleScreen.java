package com.smarttask.console.screen;

import com.smarttask.console.output.ConsoleOutput;

public class ConsoleScreen {

    private static ConsoleScreen instance;

    private boolean firstDisplay;

    private ConsoleScreen() {
        this.firstDisplay = true;
    }

    public static ConsoleScreen getInstance() {
        if (instance == null) {
            instance = new ConsoleScreen();
        }

        return instance;
    }

    public void show(String header, String content) {
        this.prepareScreen();
        this.showHeader(header);
        this.showContent(content);
    }

    public void show(String header, String content, String footer) {
        this.show(header, content);
        this.showFooter(footer);
    }

    private void prepareScreen() {
        if (this.firstDisplay) {
            clear();
            this.firstDisplay = false;
            return;
        }

        this.newLine();
    }

    private void clear() {
        ConsoleOutput.print("\033[H\033[2J");
        System.out.flush();
    }

    private void showHeader(String header) {
        this.showSeparator();
        ConsoleOutput.println(header);
        this.showSeparator();
        this.newLine();
    }

    private void showContent(String content) {
        ConsoleOutput.print(content);
    }

    private void showFooter(String footer) {
        this.newLine();
        ConsoleOutput.print(footer);
    }

    private void showSeparator() {
        ConsoleOutput.println("========================================");
    }

    private void newLine() {
        ConsoleOutput.print(System.lineSeparator());
    }

}
