package com.smarttask.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para NumberRangeValidator")
public class NumberRangeValidatorTest {

    private final NumberRangeValidator validator = NumberRangeValidator.getInstance();

    @Test
    @DisplayName("Debe validar opciones válidas de menú sin lanzar excepciones")
    void testValidateOptionSuccess() {
        assertDoesNotThrow(() -> validator.validateOption("1", 1, 4));
        assertDoesNotThrow(() -> validator.validateOption("4", 1, 4));
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException con mensaje internacionalizado si la opción es inválida")
    void testValidateOptionFailure() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateOption("5", 1, 4));
        assertNotNull(ex.getMessage());
        assertFalse(ex.getMessage().trim().isEmpty());

        assertThrows(IllegalArgumentException.class, () -> validator.validateOption("abc", 1, 4));
        assertThrows(IllegalArgumentException.class, () -> validator.validateOption(null, 1, 4));
    }

    @Test
    @DisplayName("Debe validar enteros positivos correctamente")
    void testValidatePositiveIntegerSuccess() {
        assertDoesNotThrow(() -> validator.validatePositiveInteger("1"));
        assertDoesNotThrow(() -> validator.validatePositiveInteger("100"));
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException para valores menores o iguales a cero o no numéricos")
    void testValidatePositiveIntegerFailure() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validatePositiveInteger("0"));
        assertNotNull(ex.getMessage());

        assertThrows(IllegalArgumentException.class, () -> validator.validatePositiveInteger("-5"));
        assertThrows(IllegalArgumentException.class, () -> validator.validatePositiveInteger("texto"));
        assertThrows(IllegalArgumentException.class, () -> validator.validatePositiveInteger(null));
    }
}
