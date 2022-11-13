package plugin.atb.booking.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee", nullable = false)
    private Long idEmployee;

    private String name;

    private String login;

    private String password;

    public Employee(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
