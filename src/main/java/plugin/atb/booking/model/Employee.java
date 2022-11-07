package plugin.atb.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Employee {
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
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
