package com.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EmployeeRequestDTO(
        @JsonProperty("nombres")
        @NotBlank(message = "El nombre es requerido") String nombres,
        
        @JsonProperty("apellidos")
        @NotBlank(message = "El apellido es requerido") String apellidos,
        
        @JsonProperty("tipoDocumento")
        @NotBlank(message = "El tipo de documento es requerido") String tipoDocumento,
        
        @JsonProperty("numeroDocumento")
        @NotBlank(message = "El número de documento es requerido") String numeroDocumento,

        @JsonProperty("fechaNacimiento")
        @NotNull(message = "Fecha nacimiento requerida")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate fechaNacimiento,

        @JsonProperty("fechaVinculacion")
        @NotNull(message = "Fecha vinculación requerida")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate fechaVinculacion,

        @JsonProperty("cargo")
        @NotBlank(message = "El cargo es requerido") String cargo,
        
        @JsonProperty("salario")
        @NotNull(message = "El salario es requerido") Double salario
) {}