package plugin.atb.booking.model;

import java.time.LocalDateTime;

public class QueueReservation {
    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;
    private int idPlace;
    private String login;

    public QueueReservation () {

    }

    public QueueReservation(LocalDateTime timeBegin, LocalDateTime timeEnd, int idPlace, String login) {
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.idPlace = idPlace;
        this.login = login;
    }

    public LocalDateTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalDateTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
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

    @Override
    public String toString() {
        return "QueueReservation{" +
                "timeBegin=" + timeBegin +
                ", timeEnd=" + timeEnd +
                ", idPlace=" + idPlace +
                ", login='" + login + '\'' +
                '}';
    }
}
