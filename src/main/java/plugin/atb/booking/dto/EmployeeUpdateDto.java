package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeUpdateDto {
    private String name;
    private String password;

    public EmployeeUpdateDto() {

    }
}
