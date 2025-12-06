package com.employee.port;

import com.employee.models.Employee;

public interface EmployeeRepositoryPort {
    void saveEmployeeViaSoap(Employee employee);
}