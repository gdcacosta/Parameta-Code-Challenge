package com.employee.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeResponseDTOTest {

    @Test
    void testEmployeeResponseDTO_Creation() {
        EmployeeResponseDTO response = new EmployeeResponseDTO(
                "Juan",
                "Pérez",
                LocalDate.of(1990, 5, 15),
                "34 años, 6 meses y 23 días",
                "4 años, 11 meses y 8 días",
                true
        );

        assertEquals("Juan", response.nombres());
        assertEquals("Pérez", response.apellidos());
        assertEquals(LocalDate.of(1990, 5, 15), response.fechaNacimiento());
        assertTrue(response.guardadoExitoso());
    }

    @Test
    void testEmployeeResponseDTO_AllFields() {
        String nombres = "María";
        String apellidos = "García";
        LocalDate fechaNacimiento = LocalDate.of(1995, 12, 10);
        String edadActual = "29 años, 0 meses y 28 días";
        String tiempoVinculacion = "3 años, 0 meses y 0 días";
        boolean guardadoExitoso = true;

        EmployeeResponseDTO response = new EmployeeResponseDTO(
                nombres,
                apellidos,
                fechaNacimiento,
                edadActual,
                tiempoVinculacion,
                guardadoExitoso
        );

        assertEquals(nombres, response.nombres());
        assertEquals(apellidos, response.apellidos());
        assertEquals(fechaNacimiento, response.fechaNacimiento());
        assertEquals(edadActual, response.edadActual());
        assertEquals(tiempoVinculacion, response.tiempoVinculacion());
        assertEquals(guardadoExitoso, response.guardadoExitoso());
    }

    @Test
    void testEmployeeResponseDTO_NotSuccessful() {
        EmployeeResponseDTO response = new EmployeeResponseDTO(
                "Carlos",
                "López",
                LocalDate.of(1988, 3, 20),
                "36 años, 8 meses y 18 días",
                "2 años, 11 meses y 8 días",
                false
        );

        assertFalse(response.guardadoExitoso());
    }

    @Test
    void testEmployeeResponseDTO_RecordEquality() {
        EmployeeResponseDTO response1 = new EmployeeResponseDTO(
                "Juan",
                "Pérez",
                LocalDate.of(1990, 5, 15),
                "34 años, 6 meses y 23 días",
                "4 años, 11 meses y 8 días",
                true
        );

        EmployeeResponseDTO response2 = new EmployeeResponseDTO(
                "Juan",
                "Pérez",
                LocalDate.of(1990, 5, 15),
                "34 años, 6 meses y 23 días",
                "4 años, 11 meses y 8 días",
                true
        );

        assertEquals(response1, response2);
    }

    @Test
    void testEmployeeResponseDTO_RecordInequality() {
        EmployeeResponseDTO response1 = new EmployeeResponseDTO(
                "Juan",
                "Pérez",
                LocalDate.of(1990, 5, 15),
                "34 años, 6 meses y 23 días",
                "4 años, 11 meses y 8 días",
                true
        );

        EmployeeResponseDTO response2 = new EmployeeResponseDTO(
                "Juan",
                "Pérez",
                LocalDate.of(1990, 5, 15),
                "34 años, 6 meses y 23 días",
                "4 años, 11 meses y 8 días",
                false
        );

        assertNotEquals(response1, response2);
    }
}
