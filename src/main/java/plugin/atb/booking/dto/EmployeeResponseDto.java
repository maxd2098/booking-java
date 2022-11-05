package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeResponseDto {
    private Long idEmployee;
    private String name;
    private String login;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(Long idEmployee, String name, String login) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.login = login;
    }
}
