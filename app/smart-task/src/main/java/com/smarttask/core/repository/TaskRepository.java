package com.smarttask.core.repository;

import java.util.List;

import com.smarttask.core.models.Task;

/**
 * Contrato de repositorio para la persistencia, consulta y modificación de
 * tareas.
 * <p>
 * Define las operaciones CRUD elementales sobre el modelo {@link Task}
 * desacoplando
 * la lógica de negocio del mecanismo de almacenamiento subyacente.
 * </p>
 *
 * @author DevAlejandroSaa
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public interface TaskRepository {

    /**
     * Guarda y persiste una nueva tarea en el repositorio generando su identificador único.
     *
     * @param task Objeto {@link Task} a persistir.
     * @throws com.smarttask.core.exception.exceptions.TaskAlreadyExistsException Si ya existe una tarea con el mismo nombre.
     */
    public void save(Task task);

    /**
     * Retorna todas las tareas almacenadas en el sistema.
     *
     * @return Lista de tareas {@link Task} registradas.
     */
    public List<Task> findAll();

    /**
     * Busca y retorna una tarea por su identificador numérico único.
     *
     * @param id Identificador de la tarea a consultar.
     * @return El objeto {@link Task} correspondiente, o {@code null} si no existe.
     */
    public Task findById(int id);

    /**
     * Actualiza la información de una tarea previamente existente en el almacenamiento.
     *
     * @param task Objeto {@link Task} con las propiedades modificadas.
     */
    public void update(Task task);

    /**
     * Elimina una tarea por su identificador y libera su posición para reciclaje de ID.
     *
     * @param id Identificador numérico de la tarea a eliminar.
     */
    public void delete(int id);

}
