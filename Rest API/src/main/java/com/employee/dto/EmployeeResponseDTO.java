package com.employee.dto;

import java.time.LocalDate;

public record EmployeeResponseDTO(
        String nombres,
        String apellidos,
        LocalDate fechaNacimiento,
        String edadActual,
        String tiempoVinculacion,
        boolean guardadoExitoso
) {}