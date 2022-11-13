package plugin.atb.booking.mapper;

import org.springframework.stereotype.Component;
import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeResponseDto;
import plugin.atb.booking.model.Employee;

@Component
public class EmployeeMapper {
    public Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getName(),
                employeeDto.getLogin(),
                employeeDto.getPassword()
        );
    }

    public EmployeeResponseDto mapEmployeeToResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getIdEmployee(),
                employee.getLogin(),
                employee.getName()
        );
    }
}
