package com.employee.soap;

import com.employee.dto.GuardarEmpleadoRequest;
import com.employee.dto.GuardarEmpleadoResponse;
import com.employee.mapper.SoapEmployeeMapper;
import com.employee.models.Employee;
import com.employee.port.EmployeeRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class SoapClientAdapter implements EmployeeRepositoryPort {

    private final WebServiceTemplate webServiceTemplate;
    private final SoapEmployeeMapper soapMapper;

    public SoapClientAdapter(WebServiceTemplate webServiceTemplate, SoapEmployeeMapper soapMapper) {
        this.webServiceTemplate = webServiceTemplate;
        this.soapMapper = soapMapper;
    }

    @Override
    public void saveEmployeeViaSoap(Employee employee) {

        GuardarEmpleadoRequest request = soapMapper.toSoapRequest(employee);

        GuardarEmpleadoResponse response = (GuardarEmpleadoResponse) webServiceTemplate
                .marshalSendAndReceive(request);

        if (response != null && !response.isExito()) {
            throw new RuntimeException("Error reportado por el servicio SOAP: " + response.getMensaje());
        }
    }
}
