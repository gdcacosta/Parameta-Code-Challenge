package com.soap.service.mapper;
import com.soap.service.entities.EmpleadoEntity;
import com.soap_service.soap.empleados.GuardarEmpleadoRequest;
import com.soap_service.soap.empleados.GuardarEmpleadoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmpleadoSoapMapper {


    @Mapping(target = "id", ignore = true)

    EmpleadoEntity toEntity(GuardarEmpleadoRequest request);



    @Mapping(target = "idEmpleado", source = "entity.id")
    @Mapping(target = "exito", constant = "true")
    @Mapping(target = "mensaje", constant = "Empleado guardado correctamente")
    GuardarEmpleadoResponse toResponse(EmpleadoEntity entity);


}