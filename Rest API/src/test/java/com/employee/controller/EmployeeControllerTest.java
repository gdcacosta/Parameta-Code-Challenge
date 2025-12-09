package com.employee.controller;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;
import com.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeRequestDTO validRequest;
    private EmployeeResponseDTO mockResponse;

    @BeforeEach
    void setUp() {
        validRequest = new EmployeeRequestDTO(
                "Juan",
                "Pérez",
                "CC",
                "1234567890",
                LocalDate.of(1990, 5, 15),
                LocalDate.of(2020, 1, 1),
                "Ingeniero",
                50000.0
        );

        mockResponse = new EmployeeResponseDTO(
                "Juan",
                "Pérez",
                LocalDate.of(1990, 5, 15),
                "34 años, 6 meses y 23 días",
                "4 años, 11 meses y 8 días",
                true
        );
    }

    @Test
    void testReceiveEmployee_Success() {
        when(employeeService.processEmployee(any(EmployeeRequestDTO.class)))
                .thenReturn(mockResponse);

        ResponseEntity<EmployeeResponseDTO> response = employeeController.receiveEmployee(validRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().nombres());
        assertEquals("Pérez", response.getBody().apellidos());
        assertTrue(response.getBody().guardadoExitoso());
        verify(employeeService).processEmployee(validRequest);
    }

    @Test
    void testReceiveEmployee_ServiceThrowsException() {
        when(employeeService.processEmployee(any(EmployeeRequestDTO.class)))
                .thenThrow(new IllegalArgumentException("El empleado debe ser mayor de edad."));

        assertThrows(IllegalArgumentException.class, 
                () -> employeeController.receiveEmployee(validRequest));
    }

    @Test
    void testReceiveEmployee_ReturnsCorrectAgeCalculation() {
        when(employeeService.processEmployee(any(EmployeeRequestDTO.class)))
                .thenReturn(mockResponse);

        ResponseEntity<EmployeeResponseDTO> response = employeeController.receiveEmployee(validRequest);

        assertNotNull(response.getBody().edadActual());
        assertTrue(response.getBody().edadActual().contains("años"));
    }
}
