package plugin.atb.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeUpdateDto;
import plugin.atb.booking.exception.ConflictException;
import plugin.atb.booking.exception.NotFoundException;
import plugin.atb.booking.mapper.EmployeeMapper;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    @PostMapping("/")
    public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee checkLogin = employeeService.readEmployeeByLogin(employeeDto.getLogin());
        if (checkLogin != null) {
            throw new ConflictException("Пользователь с логином " + employeeDto.getLogin() + " уже существует");
        }
        Employee employee = employeeMapper.mapEmployeeDtoToEmployee(employeeDto);
        employeeService.createEmployee(employee);
        System.out.println("Пользователь с логином " + employeeDto.getLogin() + " был создан");
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<Object> readEmployeeByLogin(@PathVariable String login) {
        Employee employee = employeeService.readEmployeeByLogin(login);
        if (employee == null) {
            throw new NotFoundException("Пользователь с логином " + login + " не найден");
        }
        System.out.println("Пользователь с логином " + login + " был получен");
        return ResponseEntity.ok(employeeMapper.mapEmployeeToResponseDto(employee));
    }

    @GetMapping("/id/{idEmployee}")
    public ResponseEntity<Object> readEmployeeById(@PathVariable Long idEmployee) {
        Employee employee = employeeService.readEmployeeById(idEmployee);
        if (employee == null) {
            throw new NotFoundException("Пользователь с id " + idEmployee + " не найден");
        }
        System.out.println("Пользователь с id " + idEmployee + " был получен");
        return ResponseEntity.ok(employeeMapper.mapEmployeeToResponseDto(employee));
    }

    @PutMapping("/{idEmployee}")
    public ResponseEntity<Object> updateEmployeeById(
            @PathVariable Long idEmployee,
            @RequestBody EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeService.readEmployeeById(idEmployee);
        if (employee == null) {
            throw new NotFoundException("Пользователь с id " + idEmployee + " не найден для изменения");
        }
        if (employeeUpdateDto.getName() != null) {
            employee.setName(employeeUpdateDto.getName());
        }
        if (employeeUpdateDto.getPassword() != null) {
            employee.setPassword(employeeUpdateDto.getPassword());
        }
        employeeService.updateEmployee(employee);
        System.out.println("Пользователь с id " + idEmployee + " был отредактирован");
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable Long idEmployee) {
        Employee employee = employeeService.readEmployeeById(idEmployee);
        if (employee == null) {
            throw new NotFoundException("Пользователь с id " + idEmployee + " не найден для удаления");
        } else {
            employeeService.deleteEmployeeById(idEmployee);
            System.out.println("Пользователь с id " + idEmployee + " был удален");
            return ResponseEntity.ok(employee);
        }
    }
}
