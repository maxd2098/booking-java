package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee readEmployeeByLogin(String login) {
        return employeeRepository.findEmployeeByLogin(login);
    }

    public Employee readEmployeeById(Long idEmployee) {
        return employeeRepository.findByIdEmployee(idEmployee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long idEmployee) {
        employeeRepository.deleteById(idEmployee);
    }
}
