package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String name;
    private String login;
    private String password;

    public EmployeeDto() {
    }
}
