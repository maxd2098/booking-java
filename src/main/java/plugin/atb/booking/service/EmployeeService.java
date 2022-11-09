package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeResponseDto;
import plugin.atb.booking.dto.EmployeeUpdateDto;
import plugin.atb.booking.mapper.EmployeeMapper;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service //controller
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployeeById(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long idEmployee) {
        employeeRepository.deleteById(idEmployee);
    }

}
