package plugin.atb.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@ToString
public class Employee {
    private Long id_employee;
    private String name;
    private String login;
    private String password;

    public Employee() {

    }

    public Employee(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Employee(Long id_employee, String name, String login, String password) {
        this.id_employee = id_employee;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.getName()) && Objects.equals(login, employee.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login);
    }
}
