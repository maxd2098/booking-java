package plugin.atb.booking.controller;

import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //view
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
 
    @PostMapping("/")
    public void createEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.createEmployee(employeeDto);
    }

    @GetMapping("/")
    public Employee readEmployee(String login) {
        return employeeService.readEmployee(login);
    }

    @PutMapping("/")
    public void updateEmployee(EmployeeDto employeeDto) {
        employeeService.updateEmployee(employeeDto);
    }

    @DeleteMapping("/")
    public void deleteEmployee(String login) {
        employeeService.deleteEmployee(login);
    }
}
