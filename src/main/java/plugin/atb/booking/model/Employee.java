package plugin.atb.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee", nullable = false)
    private Long idEmployee;
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

    public Employee(Long idEmployee, String name, String login, String password) {
        this.idEmployee = idEmployee;
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
