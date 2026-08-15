package com.smarttask.testutils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.PriorityQueue;

import com.smarttask.core.database.DataStore;

/**
 * Utilidad de soporte para las pruebas unitarias para reiniciar el estado
 * interno del DataStore singleton.
 */
public class TestDataStoreHelper {

    public static void resetDataStore() {
        DataStore ds = DataStore.getInstance();
        try {
            Field tasksField = DataStore.class.getDeclaredField("tasks");
            tasksField.setAccessible(true);
            List<?> tasks = (List<?>) tasksField.get(ds);
            tasks.clear();

            Field freeIdsField = DataStore.class.getDeclaredField("freeIds");
            freeIdsField.setAccessible(true);
            PriorityQueue<?> freeIds = (PriorityQueue<?>) freeIdsField.get(ds);
            freeIds.clear();
        } catch (Exception e) {
            throw new RuntimeException("Error reiniciando estado de DataStore en pruebas", e);
        }
    }
}
