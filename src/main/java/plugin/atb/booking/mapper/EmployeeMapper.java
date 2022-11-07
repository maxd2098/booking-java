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

    public static Employee mapEmployeeEntityToEmployee(EmployeeEntity employeeEntity) {
        return new Employee(
                employeeEntity.getIdEmployee(),
                employeeEntity.getName(),
                employeeEntity.getLogin(),
                employeeEntity.getPassword()
        );
    }

    public static EmployeeResponseDto mapEmployeeEntityToResponseDto(EmployeeEntity employeeEntity) {
        return new EmployeeResponseDto(
                employeeEntity.getIdEmployee(),
                employeeEntity.getLogin(),
                employeeEntity.getName()
        );
    }

    public static EmployeeEntity mapEmployeeToEmployeeEntityForQueue(Employee employee) {
        return new EmployeeEntity(
                employee.getIdEmployee(),
                employee.getName(),
                employee.getLogin(),
                employee.getPassword()
        );
    }

    public static EmployeeEntity mapEmployeeUpdateDtoToEmployeeEntity(EmployeeUpdateDto employeeUpdateDto) {
        return new EmployeeEntity(
                employeeUpdateDto.getName(),
                employeeUpdateDto.getPassword()
        );
    }
}
