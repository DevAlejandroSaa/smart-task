package com.smarttask.core.models;

/**
 * Entidad de dominio que representa una tarea individual en SmartTask.
 * <p>
 * Contiene información sobre su identificador único numérico, nombre
 * descriptivo,
 * nivel de prioridad ({@link Priority}) y estado actual ({@link Status}).
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class Task {
    /**
     * Identificador numérico único de la tarea.
     */
    private int id;

    /**
     * Nombre descriptivo de la tarea.
     */
    private String name;

    /**
     * Nivel de prioridad asignado a la tarea.
     */
    private Priority priority;

    /**
     * Estado actual de la tarea.
     */
    private Status status;

    /**
     * Construye una nueva tarea asignándole nombre, prioridad y el estado inicial
     * {@link Status#ACTIVA}.
     *
     * @param name     Nombre descriptivo de la tarea.
     * @param priority Nivel de prioridad inicial de la tarea.
     */
    public Task(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
        this.status = Status.ACTIVA;
    }

    /**
     * Obtiene el identificador único de la tarea.
     *
     * @return El identificador numérico de la tarea.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la tarea.
     *
     * @param id Nuevo identificador numérico.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre descriptivo de la tarea.
     *
     * @return El nombre de la tarea.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la tarea.
     *
     * @param name Nuevo nombre descriptivo para la tarea.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la prioridad asignada a la tarea.
     *
     * @return La prioridad {@link Priority}.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Establece la prioridad de la tarea.
     *
     * @param priority Nueva prioridad {@link Priority}.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Obtiene el estado actual de la tarea.
     *
     * @return El estado {@link Status}.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Establece el estado de la tarea.
     *
     * @param status Nuevo estado {@link Status}.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Retorna una representación en cadena de caracteres de la tarea.
     *
     * @return Cadena que representa el estado y valores de la tarea.
     */
    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", priority=" + priority + ", status=" + status + "]";
    }

}
