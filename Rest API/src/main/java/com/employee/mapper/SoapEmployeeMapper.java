package com.employee.mapper;


import com.employee.dto.GuardarEmpleadoRequest;
import com.employee.models.Employee;
import org.mapstruct.Mapper;

import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SoapEmployeeMapper {
    GuardarEmpleadoRequest toSoapRequest(Employee employee);
}
