package plugin.atb.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeResponseDto;
import plugin.atb.booking.dto.EmployeeUpdateDto;
import plugin.atb.booking.mapper.EmployeeMapper;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.repository.EmployeeRepository;
import plugin.atb.booking.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController //view
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
 
    @PostMapping("/")
    public void createEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            boolean checkLogin = employeeRepository.findAll().stream()
                    .anyMatch(e -> e.getLogin().equals(employeeDto.getLogin()));
            if (checkLogin) {
                throw new Exception("Пользователь с логином " + employeeDto.getLogin() + " уже существует");
            }
            Employee employee = EmployeeMapper.mapEmployeeDtoToEmployee(employeeDto);
            employeeService.createEmployee(employee);
            System.out.println("Пользователь с логином " + employeeDto.getLogin() + " был создан");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @GetMapping("/login/{login}")
    public EmployeeResponseDto readEmployeeByLogin(@PathVariable String login) {
        //return ResponseEntity.status(HttpStatus.OK).body(employeeService.readEmployeeByLogin(login));
        try {
            Employee employee = employeeRepository.findAll().stream()
                    .filter(e -> e.getLogin().equals(login))
                    .findFirst().orElseThrow(() -> new RuntimeException("Пользователь с логином " + login + " не найден"));
            System.out.println("Пользователь с логином " + login + " был получен");
            return EmployeeMapper.mapEmployeeToResponseDto(employee);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/id/{idEmployee}")
    public EmployeeResponseDto readEmployeeById(@PathVariable Long idEmployee) {
        try {
            List<Employee> employees = new ArrayList<>(employeeRepository.findAll());
            Employee employee = employees.stream()
                    .filter(e -> e.getIdEmployee().equals(idEmployee))
                    .findFirst().orElseThrow(() -> new RuntimeException("Пользователь с id " + idEmployee + " не найден"));;
            System.out.println("Пользователь с id " + idEmployee + " был получен");
            return EmployeeMapper.mapEmployeeToResponseDto(employee);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @PutMapping("/{idEmployee}")
    public void updateEmployeeById(@PathVariable Long idEmployee, @RequestBody EmployeeUpdateDto employeeUpdateDto) {
        try {
            Employee employee = employeeRepository.findAll().stream()
                    .filter(e -> Objects.equals(e.getIdEmployee(), idEmployee))
                    .findFirst().orElseThrow(() -> new RuntimeException("Пользователь с id " +
                            idEmployee + " не найден для изменения"));
            if (employeeUpdateDto.getName() != null) {
                employee.setName(employeeUpdateDto.getName());
            }
            if (employeeUpdateDto.getPassword() != null) {
                employee.setPassword(employeeUpdateDto.getPassword());
            }
            employeeService.updateEmployeeById(employee);
            System.out.println("Пользователь с id " + idEmployee + " был отредактирован");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @DeleteMapping("/{idEmployee}")
    public void deleteEmployeeById(@PathVariable Long idEmployee) {
        try {
            boolean checkEmployee = employeeRepository.findAll().stream()
                    .anyMatch(e -> Objects.equals(e.getIdEmployee(), idEmployee));
            if (checkEmployee) {
                employeeService.deleteEmployeeById(idEmployee);
                System.out.println("Пользователь с id " + idEmployee + " был удален");
            } else {
                throw new Exception("Пользователь с id " + idEmployee + " не найден для удаления");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
