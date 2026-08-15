package com.smarttask.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para NumberRangeUtils")
public class NumberRangeUtilsTest {

    private final NumberRangeUtils utils = NumberRangeUtils.getInstance();

    @Test
    @DisplayName("Debe validar correctamente cadenas numéricas")
    void testIsNumeric() {
        assertTrue(utils.isNumeric("0"));
        assertTrue(utils.isNumeric("123"));
        assertTrue(utils.isNumeric("9999"));

        assertFalse(utils.isNumeric(null));
        assertFalse(utils.isNumeric(""));
        assertFalse(utils.isNumeric(" "));
        assertFalse(utils.isNumeric("-10"));
        assertFalse(utils.isNumeric("12a"));
        assertFalse(utils.isNumeric("abc"));
    }

    @Test
    @DisplayName("Debe validar números dentro del rango especificado")
    void testIsNumericInRange() {
        // Límites y dentro del rango
        assertTrue(utils.isNumericInRange("1", 1, 5));
        assertTrue(utils.isNumericInRange("3", 1, 5));
        assertTrue(utils.isNumericInRange("5", 1, 5));

        // Fuera de rango
        assertFalse(utils.isNumericInRange("0", 1, 5));
        assertFalse(utils.isNumericInRange("6", 1, 5));
        assertFalse(utils.isNumericInRange("-1", 1, 5));

        // No numérico
        assertFalse(utils.isNumericInRange("abc", 1, 5));
        assertFalse(utils.isNumericInRange(null, 1, 5));
    }
}
