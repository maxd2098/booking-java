package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeResponseDto;
import plugin.atb.booking.dto.EmployeeUpdateDto;
import plugin.atb.booking.entity.EmployeeEntity;
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

    public void createEmployee(EmployeeDto employeeDto) {
        try {
            boolean checkLogin = employeeRepository.findAll().stream()
                    .anyMatch(e -> e.getLogin().equals(employeeDto.getLogin()));
            if (checkLogin) {
                throw new Exception("Пользователь с логином " + employeeDto.getLogin() + " уже существует");
            }
            Employee employee = EmployeeMapper.mapEmployeeDtoToEmployee(employeeDto);
            employeeRepository.save(EmployeeMapper.mapEmployeeToEmployeeEntity(employee));
            System.out.println("Пользователь с логином " + employeeDto.getLogin() + " был создан");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public EmployeeResponseDto readEmployeeByLogin(String login) {
        try {
            EmployeeEntity employeeEntity = employeeRepository.findAll().stream()
                    .filter(e -> e.getLogin().equals(login))
                    .findFirst().orElseThrow(() -> new RuntimeException("Пользователь с логином " + login + " не найден"));
            System.out.println("Пользователь с логином " + login + " был получен");
            return EmployeeMapper.mapEmployeeEntityToResponseDto(employeeEntity);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Employee readEmployeeById(Long idEmployee) {
        try {
            List<Employee> employees = new ArrayList<>();
            employeeRepository.findAll()
                    .forEach(tt -> employees.add(EmployeeMapper.mapEmployeeEntityToEmployee(tt)));
            Employee employee = employees.stream()
                    .filter(e -> e.getIdEmployee().equals(idEmployee))
                    .findFirst().orElseThrow(() -> new RuntimeException("Пользователь с id " + idEmployee + " не найден"));;
            System.out.println("Пользователь с id " + idEmployee + " был получен");
            return employee;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void updateEmployeeById(Long idEmployee, EmployeeUpdateDto employeeUpdateDto) {
        try {
            EmployeeEntity employeeEntity = employeeRepository.findAll().stream()
                    .filter(e -> Objects.equals(e.getIdEmployee(), idEmployee))
                    .findFirst().orElseThrow(() -> new RuntimeException("Пользователь с id " +
                            idEmployee + " не найден для изменения"));
            if (employeeUpdateDto.getName() != null) {
                employeeEntity.setName(employeeUpdateDto.getName());
            }
            if (employeeUpdateDto.getPassword() != null) {
                employeeEntity.setPassword(employeeUpdateDto.getPassword());
            }
            employeeRepository.save(employeeEntity);
            System.out.println("Пользователь с id " + idEmployee + " был отредактирован");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEmployeeById(Long idEmployee) {
        try {
            boolean checkEmployee = employeeRepository.findAll().stream()
                    .anyMatch(e -> Objects.equals(e.getIdEmployee(), idEmployee));
            if (checkEmployee) {
                employeeRepository.deleteById(idEmployee);
                System.out.println("Пользователь с id " + idEmployee + " был удален");
            } else {
                throw new Exception("Пользователь с id " + idEmployee + " не найден для удаления");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
