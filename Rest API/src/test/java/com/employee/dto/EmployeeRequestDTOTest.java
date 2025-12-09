package com.employee.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRequestDTOTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testValidEmployeeRequest() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                2000000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmployeeRequest_BlankName() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                50000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("nombre")));
    }

    @Test
    void testEmployeeRequest_MultipleValidFields() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan María",
                "Pérez García",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero Senior",
                2500000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmployeeRequest_BlankTipoDocumento() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                50000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testEmployeeRequest_FutureVinculationDate() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.now().plusYears(1),
                "Ingeniero",
                1800000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmployeeRequest_RealWorldScenario() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Fernando",
                "Rodríguez Martínez",
                "TI",
                "98765432101",
                LocalDate.of(1985, 8, 22),
                LocalDate.of(2015, 3, 15),
                "Analista de Sistemas",
                2200000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmployeeRequest_HighSalary() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Director",
                5000000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "CC",
            "TI",
            "PP",
            "CE",
            "NIT"
    })
    void testEmployeeRequest_ValidDocumentTypes(String docType) {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                docType,
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                1800000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmployeeRequest_BlankCargo() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "",
                50000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testEmployeeRequest_BlankNumeroDocumento() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                50000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testEmployeeRequest_ZeroSalario() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                0.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty()); 
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("salario mínimo")));
    }

    @Test
    void testEmployeeRequest_NegativeSalario() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                -5000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty()); 
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("salario mínimo")));
    }

    @Test
    void testEmployeeRequest_BelowMinimumSalary() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                1000000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty()); 
    }

    @Test
    void testEmployeeRequest_ExactMinimumSalary() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                1613426.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty()); 
    }

    @Test
    void testEmployeeRequest_AboveMinimumSalary() {
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                2000000.0
        );

        Set<ConstraintViolation<EmployeeRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty()); 
    }
}
