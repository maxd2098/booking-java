package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.model.Employee;
import org.springframework.stereotype.Service;
import plugin.atb.booking.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //controller
@RequiredArgsConstructor
public class EmployeeService {
    public List<Employee> dataBase = new ArrayList<>();
    private final EmployeeRepository employeeRepository;

    public void createEmployee(EmployeeDto employeeDto) {
        String name = employeeDto.getName();
        String login = employeeDto.getLogin();
        String password = employeeDto.getPassword();
        try {
            boolean checkLogin = employeeRepository.findAll().stream()
                    .anyMatch(e -> e.getLogin().equals(login));
            if (checkLogin) {
                throw new Exception("Пользователь с логином " + login + " уже существует");
            }
            employeeRepository.save(new Employee(name, login, password));
            System.out.println("Пользователь с логином " + login + " был создан");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Employee readEmployee(String login) {
        try {
            Optional<Employee> employee = dataBase.stream()
                    .filter(e -> e.getLogin().equals(login))
                    .findFirst();
            if (employee.isEmpty()) {
                throw new Exception("Пользователь с логином " + login + " не найден");
            } else {
                System.out.println("Пользователь с логином " + login + " был получен");
                return employee.get();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void updateEmployee(EmployeeDto employeeDto) {
        String name = employeeDto.getName();
        String login = employeeDto.getLogin();
        String password = employeeDto.getPassword();
        try {
            Optional<Employee> employee = dataBase.stream()
                    .filter(e -> e.getLogin().equals(login))
                    .findFirst();
            if (employee.isEmpty()) {
                throw new Exception("Пользователь с логином " + login + " не найден для изменения");
            }
            //int indexEmployee = dataBase.indexOf(employee.get());
            if (name != null) {
                employee.get().setName(name);
            }
            if (password != null) {
                employee.get().setPassword(password);
            }
            //dataBase.set(indexEmployee, employee.get());
            System.out.println("Пользователь с логином " + login + " был отредактирован");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEmployee(String login) {
        try {
            if (dataBase.removeIf(e -> e.getLogin().equals(login))) {
                System.out.println("Пользователь с логином " + login + " был удален");
            } else {
                throw new Exception("Пользователь с логином " + login + " не найден для удаления");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



}
