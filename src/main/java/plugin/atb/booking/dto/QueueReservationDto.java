package plugin.atb.booking.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QueueReservationDto {
    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;
    private int idPlace;
    private String login;

    public QueueReservationDto () {

    }

    public LocalDateTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timeBegin = LocalDateTime.parse(timeBegin, formatter);
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timeEnd = LocalDateTime.parse(timeEnd, formatter);
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
