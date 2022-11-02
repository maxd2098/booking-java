package plugin.atb.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@ToString
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee", nullable = false)
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

    /*@Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }*/

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
