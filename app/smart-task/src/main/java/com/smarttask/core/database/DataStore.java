package com.smarttask.core.database;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import com.smarttask.core.models.Task;

/**
 * Almacén de datos en memoria para la persistencia y gestión del ciclo de vida
 * de las tareas.
 * <p>
 * Implementa el patrón <b>Singleton</b> y un algoritmo eficiente de reciclaje
 * de identificadores
 * numéricos (IDs) mediante una cola de prioridad ({@link PriorityQueue} -
 * Min-Heap), lo que
 * permite reutilizar los índices más bajos liberados tras la eliminación de
 * registros.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class DataStore {

    /**
     * Única instancia singleton de DataStore.
     */
    private static DataStore instance;

    /**
     * Lista interna que almacena las tareas en memoria indexadas de forma
     * posicional.
     */
    private List<Task> tasks;

    /**
     * Cola de prioridad que mantiene los índices libres ordenados de menor a mayor
     * para su reciclaje.
     */
    private PriorityQueue<Integer> freeIds;

    /**
     * Constructor privado que inicializa la lista de tareas y la cola de prioridad
     * de IDs.
     */
    private DataStore() {
        tasks = new ArrayList<Task>();
        this.freeIds = new PriorityQueue<>();
    }

    /**
     * Obtiene la instancia única de {@link DataStore}.
     *
     * @return Instancia singleton de DataStore.
     */
    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }

        return instance;
    }

    /**
     * Calcula y retorna el siguiente identificador disponible para una nueva tarea.
     * <p>
     * Si existen IDs previamente reciclados en la cola de prioridad, extrae el
     * menor valor;
     * de lo contrario, asigna un nuevo ID incremental basado en el tamaño actual de
     * la lista.
     * </p>
     *
     * @return El siguiente ID entero disponible (&gt;= 1).
     */
    public int getNextId() {
        if (!this.freeIds.isEmpty()) {
            return this.freeIds.poll() + 1;
        }

        return this.tasks.size() + 1;
    }

    /**
     * Agrega o reasigna una tarea en la posición correspondiente según su ID.
     *
     * @param task Objeto {@link Task} con su identificador ya establecido.
     */
    public void addTask(Task task) {
        int index = task.getId() - 1;

        if (index < this.tasks.size()) {
            this.tasks.set(index, task);
        } else {
            this.tasks.add(task);
        }
    }

    /**
     * Retorna la lista completa de tareas en memoria (puede contener elementos null
     * en posiciones liberadas).
     *
     * @return Lista de objetos {@link Task}.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Busca y retorna una tarea por su identificador único.
     *
     * @param id Identificador numérico de la tarea a consultar.
     * @return La tarea {@link Task} si existe, o {@code null} si no se encuentra o
     *         el slot está vacío.
     */
    public Task getTaskById(int id) {
        return id - 1 >= this.tasks.size() ? null : this.tasks.get(id - 1);
    }

    /**
     * Actualiza la información de una tarea existente en su posición indexada.
     *
     * @param task Objeto {@link Task} con los datos modificados.
     */
    public void updateTask(Task task) {
        this.tasks.set(task.getId() - 1, task);
    }

    /**
     * Elimina una tarea por su ID, estableciendo su posición como null y
     * registrando el índice para su reciclaje.
     *
     * @param id Identificador de la tarea a eliminar.
     */
    public void delete(int id) {
        this.tasks.set(id - 1, null);
        this.registerFreePosition(id);
    }

    /**
     * Registra una posición libre en el almacenamiento, limpia nulos finales y
     * sincroniza la cola de IDs reciclables.
     *
     * @param id Identificador numérico de la posición que se libera.
     */
    public void registerFreePosition(int id) {
        this.tasks.set(id - 1, null);
        this.cleanTrailingNulls();
        this.verifyAndRegisterNulls();
        this.validateAndCleanFreeIds();
    }

    /**
     * Elimina los elementos {@code null} que se encuentren al final de la lista
     * para compactar la memoria.
     */
    private void cleanTrailingNulls() {
        while (!this.tasks.isEmpty() && this.tasks.get(this.tasks.size() - 1) == null) {
            this.tasks.remove(this.tasks.size() - 1);
        }
    }

    /**
     * Revisa la lista de tareas e inserta en la cola de prioridad los índices que
     * contengan {@code null}.
     */
    private void verifyAndRegisterNulls() {
        IntStream.range(0, this.tasks.size())
                .filter(i -> this.tasks.get(i) == null)
                .forEach(i -> {
                    if (!this.freeIds.contains(i)) {
                        this.freeIds.offer(i);
                    }
                });
    }

    /**
     * Valida y purga de la cola de IDs libres aquellos índices que ya hayan sido
     * reocupados por tareas activas.
     */
    private void validateAndCleanFreeIds() {
        this.freeIds.removeIf(id -> this.tasks.get(id) != null);
    }

    /**
     * Verifica si ya existe alguna tarea activa registrada con el nombre
     * especificado (comparación insensible a mayúsculas).
     *
     * @param name Nombre de la tarea a verificar.
     * @return {@code true} si existe una tarea con dicho nombre, {@code false} en
     *         caso contrario.
     */
    public boolean existsTaskByName(String name) {
        return this.tasks.stream()
                .filter(task -> task != null)
                .anyMatch(task -> task.getName().equalsIgnoreCase(name));
    }

}
