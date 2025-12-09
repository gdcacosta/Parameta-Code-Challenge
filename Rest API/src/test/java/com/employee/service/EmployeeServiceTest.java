package com.employee.service;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;
import com.employee.mapper.EmployeeMapper;
import com.employee.models.Employee;
import com.employee.port.EmployeeRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepositoryPort employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeRequestDTO validRequest;
    private EmployeeRequestDTO minorRequest;
    private Employee mockEmployee;

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

        minorRequest = new EmployeeRequestDTO(
                "Carlos",
                "López",
                "CC",
                "9876543210",
                LocalDate.of(2010, 3, 20),
                LocalDate.of(2024, 1, 1),
                "Practicante",
                0.0
        );

        mockEmployee = new Employee();
        mockEmployee.setNombres("Juan");
        mockEmployee.setApellidos("Pérez");
        mockEmployee.setFechaNacimiento(LocalDate.of(1990, 5, 15));
    }

    @Test
    void testProcessEmployee_Success() {
        when(employeeMapper.toDomain(any(EmployeeRequestDTO.class))).thenReturn(mockEmployee);

        EmployeeResponseDTO response = employeeService.processEmployee(validRequest);

        assertNotNull(response);
        assertEquals("Juan", response.nombres());
        assertEquals("Pérez", response.apellidos());
        assertTrue(response.guardadoExitoso());
        verify(employeeRepository).saveEmployeeViaSoap(mockEmployee);
    }

    @Test
    void testProcessEmployee_MinorThrowsException() {
        assertThrows(IllegalArgumentException.class, 
                () -> employeeService.processEmployee(minorRequest),
                "El empleado debe ser mayor de edad.");
    }

    @Test
    void testProcessEmployee_CalculatesAgeCorrectly() {
        when(employeeMapper.toDomain(any(EmployeeRequestDTO.class))).thenReturn(mockEmployee);

        EmployeeResponseDTO response = employeeService.processEmployee(validRequest);

        assertNotNull(response.edadActual());
        assertTrue(response.edadActual().contains("años"));
        assertTrue(response.edadActual().contains("meses"));
        assertTrue(response.edadActual().contains("días"));
    }

    @Test
    void testProcessEmployee_CalculatesVinculationTimeCorrectly() {
        when(employeeMapper.toDomain(any(EmployeeRequestDTO.class))).thenReturn(mockEmployee);

        EmployeeResponseDTO response = employeeService.processEmployee(validRequest);

        assertNotNull(response.tiempoVinculacion());
        assertTrue(response.tiempoVinculacion().contains("años"));
    }

    @Test
    void testIsAdult_BornExactly18YearsAgo() {
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "Pedro",
                "García",
                "CC",
                "1111111111",
                eighteenYearsAgo,
                LocalDate.now(),
                "Empleado",
                30000.0
        );
        
        when(employeeMapper.toDomain(any(EmployeeRequestDTO.class))).thenReturn(mockEmployee);

        assertDoesNotThrow(() -> employeeService.processEmployee(request));
    }

    @Test
    void testIsAdult_BornOneDay18Years() {
        LocalDate almostEighteen = LocalDate.now().minusYears(18).plusDays(1);
        EmployeeRequestDTO request = new EmployeeRequestDTO(
                "María",
                "Rodríguez",
                "CC",
                "2222222222",
                almostEighteen,
                LocalDate.now(),
                "Empleada",
                30000.0
        );

        assertThrows(IllegalArgumentException.class, 
                () -> employeeService.processEmployee(request));
    }

    @Test
    void testProcessEmployee_CallsRepositoryWithCorrectEmployee() {
        when(employeeMapper.toDomain(any(EmployeeRequestDTO.class))).thenReturn(mockEmployee);

        employeeService.processEmployee(validRequest);

        verify(employeeRepository).saveEmployeeViaSoap(mockEmployee);
    }

    @Test
    void testProcessEmployee_AllFieldsPopulated() {
        when(employeeMapper.toDomain(any(EmployeeRequestDTO.class))).thenReturn(mockEmployee);

        EmployeeResponseDTO response = employeeService.processEmployee(validRequest);

        assertNotNull(response.nombres());
        assertNotNull(response.apellidos());
        assertNotNull(response.fechaNacimiento());
        assertNotNull(response.edadActual());
        assertNotNull(response.tiempoVinculacion());
    }
}
