package plugin.atb.booking.entity;

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
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee", nullable = false)
    private Long idEmployee;
    private String name;
    private String login;
    private String password;

    public EmployeeEntity() {

    }

    public EmployeeEntity(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public EmployeeEntity(Long idEmployee, String name, String password) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.password = password;
    }

    public EmployeeEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
