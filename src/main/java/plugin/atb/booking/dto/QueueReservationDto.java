package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class QueueReservationDto {

    private LocalDate date;
    private String timeBegin;
    private String timeEnd;
    private boolean adminPermission;
    private Long idEmployee;
    private Long idWorkPlace;

    public QueueReservationDto () {
    }

    public void setDate(String timeEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(timeEnd, formatter);
    }
}
