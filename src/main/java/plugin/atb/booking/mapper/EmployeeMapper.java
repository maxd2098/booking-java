package plugin.atb.booking.mapper;

import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeResponseDto;
import plugin.atb.booking.model.Employee;

public class EmployeeMapper {
    public static Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getName(),
                employeeDto.getLogin(),
                employeeDto.getPassword()
        );
    }

    public static EmployeeResponseDto mapEmployeeToResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getIdEmployee(),
                employee.getLogin(),
                employee.getName()
        );
    }

    public static Employee mapResponseDtoToEmployee(EmployeeResponseDto employeeResponseDto) {
        return new Employee(
                employeeResponseDto.getIdEmployee(),
                employeeResponseDto.getLogin(),
                employeeResponseDto.getName()
        );
    }
}
