package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkPlaceResponseDto {
    private Long idWorkPlace;
    private boolean type;
    private int numSeats;
    private int numLevel;
    private String info;

    public WorkPlaceResponseDto() {
    }

    public WorkPlaceResponseDto(Long idWorkPlace, boolean type, int numSeats, int numLevel, String info) {
        this.idWorkPlace = idWorkPlace;
        this.type = type;
        this.numSeats = numSeats;
        this.numLevel = numLevel;
        this.info = info;
    }
}
