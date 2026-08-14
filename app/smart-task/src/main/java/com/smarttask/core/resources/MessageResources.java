package com.smarttask.core.resources;

import java.util.ResourceBundle;

public class MessageResources {

    private static MessageResources instance;

    private ResourceBundle messages;

    private MessageResources() {
        this.messages = ResourceBundle.getBundle("messages");
    }

    public static MessageResources getInstance() {

        if (instance == null) {
            instance = new MessageResources();
        }

        return instance;
    }

    public String getMessage(String key) {
        return this.messages.getString(key);
    }

}
