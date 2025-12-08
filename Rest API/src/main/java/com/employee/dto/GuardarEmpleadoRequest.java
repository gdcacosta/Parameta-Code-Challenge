package com.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "nombres",
        "apellidos",
        "tipoDocumento",
        "numeroDocumento",
        "fechaNacimiento",
        "fechaVinculacion",
        "cargo",
        "salario"
})

@XmlRootElement(name = "guardarEmpleadoRequest", namespace = "http://soap-service.com/soap/empleados")
public class GuardarEmpleadoRequest {

    @XmlElement(required = true)
    @NotBlank(message = "El nombre es requerido") 
    protected String nombres;

    @XmlElement(required = true)
    @NotBlank(message = "El apellido es requerido")
    protected String apellidos;

    @XmlElement(required = true)
    @NotBlank(message = "El tipo de documento es requerido")
    protected String tipoDocumento;

    @XmlElement(required = true)
    @NotBlank(message = "El tipo de documento es requerido")
    protected String numeroDocumento;

    @XmlElement(required = true)
    @NotNull(message = "Fecha nacimiento requerida")
    protected String fechaNacimiento;

    @XmlElement(required = true)
    protected String fechaVinculacion;

    @XmlElement(required = true)
    @NotBlank(message = "El cargo es requerido")
    protected String cargo;

    @XmlElement(required = true)
    @NotNull(message = "El salario es requerido")
    protected Double salario;
}