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
    //@Column(columnDefinition = "NUMERIC(19,0)")
    private Long id_employee;
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
}
