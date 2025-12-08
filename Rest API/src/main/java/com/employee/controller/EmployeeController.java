package com.employee.controller;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;
import com.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employees", description = "Endpoints para gestionar empleados")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    @Operation(summary = "Crear un nuevo empleado", description = "Procesa y guarda un nuevo empleado en la base de datos. Valida que sea mayor de edad.")
    public ResponseEntity<EmployeeResponseDTO> receiveEmployee(@Valid @RequestBody EmployeeRequestDTO request) {


        EmployeeResponseDTO response = employeeService.processEmployee(request);


        return ResponseEntity.ok(response);
    }
}
