package com.soap.service.endpoint;

import com.soap.service.entities.EmpleadoEntity;
import com.soap.service.mapper.EmpleadoSoapMapper;
import com.soap.service.repositories.EmpleadoRepository;
import com.soap_service.soap.empleados.GuardarEmpleadoRequest;
import com.soap_service.soap.empleados.GuardarEmpleadoResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmpleadoSoapEndpoint {

    private static final String NAMESPACE_URI = "http://soap-service.com/soap/empleados";

    private final EmpleadoRepository repository;
    private final EmpleadoSoapMapper mapper;

    public EmpleadoSoapEndpoint(EmpleadoRepository repository, EmpleadoSoapMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "guardarEmpleadoRequest")
    @ResponsePayload
    public GuardarEmpleadoResponse guardarEmpleado(@RequestPayload GuardarEmpleadoRequest request) {


        EmpleadoEntity entidad = mapper.toEntity(request);


        EmpleadoEntity guardado = repository.save(entidad);


        GuardarEmpleadoResponse response = new GuardarEmpleadoResponse();
        response.setExito(true);
        response.setMensaje("Empleado guardado con éxito (ID: " + guardado.getId() + ")");
        response.setIdEmpleado(guardado.getId());

        return response;
    }
}