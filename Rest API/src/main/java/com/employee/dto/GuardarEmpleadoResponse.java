package com.employee.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "guardarEmpleadoResponse", namespace = "http://soap-service.com/soap/empleados")
public class GuardarEmpleadoResponse {

    @XmlElement(required = true)
    protected boolean exito;

    @XmlElement(required = true)
    protected String mensaje;

    protected long idEmpleado;
}
