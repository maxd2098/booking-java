package plugin.atb.booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.EmployeeResponseDto;
import plugin.atb.booking.dto.EmployeeUpdateDto;
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

    @GetMapping("/{login}")
    public EmployeeResponseDto readEmployeeByLogin(@PathVariable String login) {
        //return ResponseEntity.status(HttpStatus.OK).body(employeeService.readEmployeeByLogin(login));
        return employeeService.readEmployeeByLogin(login);
    }

    @PutMapping("/{idEmployee}")
    public void updateEmployeeById(@PathVariable Long idEmployee, @RequestBody EmployeeUpdateDto employeeUpdateDto) {
        employeeService.updateEmployeeById(idEmployee, employeeUpdateDto);
    }

    @DeleteMapping("/{idEmployee}")
    public void deleteEmployeeById(@PathVariable Long idEmployee) {
        employeeService.deleteEmployeeById(idEmployee);
    }
}
