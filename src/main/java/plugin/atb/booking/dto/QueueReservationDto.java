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
    //private int idPlace;
    private Long idEmployee;

    public QueueReservationDto () {
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(String timeEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(timeEnd, formatter);
    }

    //    public LocalTime getTimeBegin() {
//        return timeBegin;
//    }
//
//    public void setTimeBegin(String timeBegin) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        this.timeBegin = LocalTime.parse(timeBegin, formatter);
//    }
//
//    public LocalTime getTimeEnd() {
//        return timeEnd;
//    }
//
//    public void setTimeEnd(String timeEnd) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        this.timeEnd = LocalTime.parse(timeEnd, formatter);
//    }

}
