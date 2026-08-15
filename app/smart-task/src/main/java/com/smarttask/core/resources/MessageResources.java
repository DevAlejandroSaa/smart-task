package com.smarttask.core.resources;

import java.util.ResourceBundle;

/**
 * Gestor de recursos y cadenas de mensajes internacionalizadas.
 * <p>
 * Carga el archivo {@code messages.properties} del classpath mediante {@link ResourceBundle}
 * y proporciona acceso a los mensajes de la aplicación.
 * Implementa el patrón de diseño <b>Singleton</b>.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class MessageResources {

    /**
     * Única instancia singleton de MessageResources.
     */
    private static MessageResources instance;

    /**
     * Paquete de recursos que contiene los mensajes traducidos.
     */
    private ResourceBundle messages;

    /**
     * Constructor privado que carga el ResourceBundle "messages" (patrón Singleton).
     */
    private MessageResources() {
        this.messages = ResourceBundle.getBundle("messages");
    }

    /**
     * Obtiene la instancia única de {@link MessageResources}.
     *
     * @return Instancia singleton de MessageResources.
     */
    public static MessageResources getInstance() {

        if (instance == null) {
            instance = new MessageResources();
        }

        return instance;
    }

    /**
     * Obtiene el mensaje de texto asociado a la clave especificada en el archivo de recursos.
     *
     * @param key Clave identificadora del mensaje en el archivo {@code messages.properties}.
     * @return El texto del mensaje correspondiente.
     */
    public String getMessage(String key) {
        return this.messages.getString(key);
    }

}
