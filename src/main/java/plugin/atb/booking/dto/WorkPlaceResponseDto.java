package plugin.atb.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkPlaceResponseDto {
    private Long idWorkPlace;

    private boolean type;

    private int numSeats;

    private int numLevel;

    private String info;
}
