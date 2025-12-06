package com.employee.service;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;
import com.employee.mapper.EmployeeMapper;
import com.employee.models.Employee;
import com.employee.port.EmployeeRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class EmployeeService {

    private final EmployeeRepositoryPort soapRepository;
    private final EmployeeMapper employeeMapper;


    public EmployeeService(EmployeeRepositoryPort soapRepository, EmployeeMapper employeeMapper) {
        this.soapRepository = soapRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponseDTO processEmployee(EmployeeRequestDTO request) {

        if (!isAdult(request.fechaNacimiento())) {
            throw new IllegalArgumentException("El empleado debe ser mayor de edad.");
        }


        Employee employee = employeeMapper.toDomain(request);

        soapRepository.saveEmployeeViaSoap(employee);


        String edadStr = calculateTimeMessage(request.fechaNacimiento(), LocalDate.now());
        String vinculacionStr = calculateTimeMessage(request.fechaVinculacion(), LocalDate.now());


        return new EmployeeResponseDTO(
                employee.getNombres(),
                employee.getApellidos(),
                employee.getFechaNacimiento(),
                edadStr,
                vinculacionStr,
                true
        );
    }


    private boolean isAdult(LocalDate birthDate) {
        if (birthDate == null) return false;
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }

    private String calculateTimeMessage(LocalDate start, LocalDate end) {
        if (start == null || end == null) return "Fecha no disponible";
        Period period = Period.between(start, end);
        return String.format("%d años, %d meses y %d días",
                period.getYears(), period.getMonths(), period.getDays());
    }
}