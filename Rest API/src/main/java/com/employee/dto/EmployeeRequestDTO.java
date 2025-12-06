package com.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public record EmployeeRequestDTO(
        @NotBlank(message = "El nombre es requerido") String nombres,
        @NotBlank(message = "El apellido es requerido") String apellidos,
        @NotBlank(message = "El tipo de documento es requerido") String tipoDocumento,
        @NotBlank(message = "El número de documento es requerido") String numeroDocumento,

        @NotNull(message = "Fecha nacimiento requerida")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate fechaNacimiento,

        @NotNull(message = "Fecha vinculación requerida")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate fechaVinculacion,

        @NotBlank(message = "El cargo es requerido") String cargo,
        @NotNull(message = "El salario es requerido") Double salario
) {}