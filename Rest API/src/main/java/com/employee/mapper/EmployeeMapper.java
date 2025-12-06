package com.employee.mapper;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.models.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {


    Employee toDomain(EmployeeRequestDTO request);

}