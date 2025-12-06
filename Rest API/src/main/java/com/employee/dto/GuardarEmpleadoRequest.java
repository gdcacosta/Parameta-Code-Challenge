package com.employee.dto;

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
    protected String nombres;

    @XmlElement(required = true)
    protected String apellidos;

    @XmlElement(required = true)
    protected String tipoDocumento;

    @XmlElement(required = true)
    protected String numeroDocumento;

    @XmlElement(required = true)
    protected String fechaNacimiento;

    @XmlElement(required = true)
    protected String fechaVinculacion;

    @XmlElement(required = true)
    protected String cargo;

    @XmlElement(required = true)
    protected Double salario;
}