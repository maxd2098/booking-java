package plugin.atb.booking.mapper;

import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeResponseDto;
import plugin.atb.booking.dto.EmployeeUpdateDto;
import plugin.atb.booking.entity.EmployeeEntity;
import plugin.atb.booking.model.Employee;

public class EmployeeMapper {
    public static Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getName(),
                employeeDto.getLogin(),
                employeeDto.getPassword()
        );
    }

    public static EmployeeEntity mapEmployeeToEmployeeEntity(Employee employee) {
        return new EmployeeEntity(
                employee.getName(),
                employee.getLogin(),
                employee.getPassword()
        );
    }

    public static EmployeeResponseDto mapEmployeeEntityToResponseDto(EmployeeEntity employeeEntity) {
        return new EmployeeResponseDto(
                employeeEntity.getIdEmployee(),
                employeeEntity.getLogin(),
                employeeEntity.getName()
        );
    }

    public static EmployeeEntity mapEmployeeUpdateDtoToEmployeeEntity(EmployeeUpdateDto employeeUpdateDto) {
        return new EmployeeEntity(
                employeeUpdateDto.getName(),
                employeeUpdateDto.getPassword()
        );
    }
}
