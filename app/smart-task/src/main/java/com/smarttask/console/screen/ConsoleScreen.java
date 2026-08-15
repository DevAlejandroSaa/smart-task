package com.smarttask.console.screen;

import com.smarttask.console.output.ConsoleOutput;

/**
 * Gestor de la presentación visual y renderizado de pantallas en la consola.
 * <p>
 * Se encarga de aplicar separadores estándar, encabezados, cuerpos de
 * contenido,
 * pies de página y limpieza de pantalla ANSI en la primera visualización.
 * Implementa el patrón de diseño <b>Singleton</b>.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class ConsoleScreen {

    /**
     * Única instancia singleton de la clase ConsoleScreen.
     */
    private static ConsoleScreen instance;

    /**
     * Indicador de primera visualización para controlar la limpieza de pantalla
     * inicial.
     */
    private boolean firstDisplay;

    /**
     * Constructor privado que inicializa el estado de primera visualización (patrón
     * Singleton).
     */
    private ConsoleScreen() {
        this.firstDisplay = true;
    }

    /**
     * Obtiene la instancia única de {@link ConsoleScreen}.
     *
     * @return La instancia singleton de ConsoleScreen.
     */
    public static ConsoleScreen getInstance() {
        if (instance == null) {
            instance = new ConsoleScreen();
        }

        return instance;
    }

    /**
     * Muestra en pantalla un encabezado formateado junto con su contenido
     * correspondiente.
     *
     * @param header  Título o encabezado de la sección o menú.
     * @param content Cuerpo de texto u opciones a desplegar.
     */
    public void show(String header, String content) {
        this.prepareScreen();
        this.showHeader(header);
        this.showContent(content);
    }

    /**
     * Muestra en pantalla un encabezado, contenido central y un pie de página o
     * prompt de entrada.
     *
     * @param header  Título o encabezado de la sección o menú.
     * @param content Cuerpo de texto u opciones a desplegar.
     * @param footer  Mensaje de pie de página o instrucción para el usuario.
     */
    public void show(String header, String content, String footer) {
        this.show(header, content);
        this.showFooter(footer);
    }

    /**
     * Prepara la pantalla antes de imprimir nuevo contenido, limpiando en el primer
     * render
     * o insertando un salto de línea en visualizaciones subsecuentes.
     */
    private void prepareScreen() {
        if (this.firstDisplay) {
            clear();
            this.firstDisplay = false;
            return;
        }

        this.newLine();
    }

    /**
     * Envía secuencias de escape ANSI a la consola para limpiar la terminal y
     * posicionar el cursor.
     */
    private void clear() {
        ConsoleOutput.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Renderiza el encabezado envuelto entre líneas separadoras estándar.
     *
     * @param header Texto del encabezado a mostrar.
     */
    private void showHeader(String header) {
        this.showSeparator();
        ConsoleOutput.println(header);
        this.showSeparator();
        this.newLine();
    }

    /**
     * Renderiza el contenido principal en la consola.
     *
     * @param content Texto del contenido a imprimir.
     */
    private void showContent(String content) {
        ConsoleOutput.print(content);
    }

    /**
     * Renderiza el pie de página precedido de un salto de línea.
     *
     * @param footer Texto del pie de página a imprimir.
     */
    private void showFooter(String footer) {
        this.newLine();
        ConsoleOutput.print(footer);
    }

    /**
     * Imprime una línea separadora estándar de 40 caracteres '='.
     */
    private void showSeparator() {
        ConsoleOutput.println("========================================");
    }

    /**
     * Inserta un salto de línea específico de la plataforma en la consola.
     */
    private void newLine() {
        ConsoleOutput.print(System.lineSeparator());
    }

}
