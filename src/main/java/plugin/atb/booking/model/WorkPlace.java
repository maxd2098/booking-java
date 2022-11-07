package plugin.atb.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class WorkPlace {
    private Long idWorkPlace;
    private boolean type;
    private int numSeats;
    private int numLevel;
    private String info;

    public WorkPlace () {
    }

    public WorkPlace(Long idWorkPlace, boolean type, int numSeats, int numLevel, String info) {
        this.idWorkPlace = idWorkPlace;
        this.type = type;
        this.numSeats = numSeats;
        this.numLevel = numLevel;
        this.info = info;
    }

    public WorkPlace(boolean type, int numSeats, int numLevel, String info) {
        this.type = type;
        this.numSeats = numSeats;
        this.numLevel = numLevel;
        this.info = info;
    }
}
