package plugin.atb.booking.mapper;

import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeResponseDto;
import plugin.atb.booking.dto.EmployeeUpdateDto;
import plugin.atb.booking.model.Employee;

public class EmployeeMapper {
    public static Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getName(),
                employeeDto.getLogin(),
                employeeDto.getPassword()
        );
    }

    public static Employee mapEmployeeEntityToEmployee(Employee employeeEntity) {
        return new Employee(
                employeeEntity.getIdEmployee(),
                employeeEntity.getName(),
                employeeEntity.getLogin(),
                employeeEntity.getPassword()
        );
    }

    public static EmployeeResponseDto mapEmployeeToResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getIdEmployee(),
                employee.getLogin(),
                employee.getName()
        );
    }
}
