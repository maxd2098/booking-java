package plugin.atb.booking.mapper;

import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.entity.EmployeeEntity;
import plugin.atb.booking.model.Employee;

public class EmployeeMapper {
    public static Employee mapDtoToEmployee(EmployeeDto employeeDto) {
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
}
