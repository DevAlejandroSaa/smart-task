package com.smarttask.core.repository;

import java.util.List;

import com.smarttask.core.database.DataStore;
import com.smarttask.core.exception.exceptions.TaskAlreadyExistsException;
import com.smarttask.core.models.Task;
import com.smarttask.core.resources.MessageResources;

/**
 * Implementación concreta del repositorio de tareas {@link TaskRepository}.
 * <p>
 * Gestiona el acceso al almacenamiento en memoria {@link DataStore}, valida la unicidad
 * de nombres de tareas y coordina la asignación de identificadores únicos.
 * Implementa el patrón de diseño <b>Singleton</b>.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class TaskRepositoryImpl implements TaskRepository {

    /**
     * Instancia única estática (patrón Singleton).
     */
    private static final TaskRepositoryImpl INSTANCE = new TaskRepositoryImpl();

    /**
     * Referencia al almacén de datos en memoria.
     */
    private DataStore dataStore;

     /**
     * Proveedor de mensajes internacionalizados.
     */
    private final MessageResources messageResources;

    /**
     * Constructor público que inicializa las dependencias singleton de DataStore y MessageResources.
     */
    public TaskRepositoryImpl() {
        this.dataStore = DataStore.getInstance();
        this.messageResources = MessageResources.getInstance();
    }

    /**
     * Obtiene la instancia singleton de {@link TaskRepositoryImpl}.
     *
     * @return Instancia única de TaskRepositoryImpl.
     */
    public static TaskRepositoryImpl getInstance() {
        return INSTANCE;
    }

    /**
     * Genera el siguiente ID numérico disponible consultando el DataStore.
     *
     * @return Siguiente número entero disponible como ID.
     */
    private int generateId() {
        return this.dataStore.getNextId();
    }

    @Override
    public void save(Task task) {
        boolean existsTask = this.existsTaskByName(task.getName());

        if (existsTask) {
            throw new TaskAlreadyExistsException(this.messageResources.getMessage("error.task.already.exists"));
        }

        task.setId(this.generateId());
        this.dataStore.addTask(task);
    }

    private boolean existsTaskByName(String name) {
        return this.dataStore.existsTaskByName(name);
    }

    @Override
    public List<Task> findAll() {
        return this.dataStore.getTasks();
    }

    @Override
    public Task findById(int id) {
        return this.dataStore.getTaskById(id);
    }

    @Override
    public void update(Task task) {
        this.dataStore.updateTask(task);
    }

    @Override
    public void delete(int id) {
        this.dataStore.delete(id);
    }

}
