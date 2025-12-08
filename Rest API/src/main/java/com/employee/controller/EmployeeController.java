package com.employee.controller;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public ResponseEntity<EmployeeResponseDTO> receiveEmployee(@Valid @RequestBody EmployeeRequestDTO request) {


        EmployeeResponseDTO response = employeeService.processEmployee(request);


        return ResponseEntity.ok(response);
    }
}
