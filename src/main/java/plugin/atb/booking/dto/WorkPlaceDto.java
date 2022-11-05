package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkPlaceDto {
    private boolean type;
    private int numSeats;
    private int numLevel;
    private String info;

    public WorkPlaceDto () {
    }
}
