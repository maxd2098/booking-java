package plugin.atb.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@ToString
public class QueueReservation {
    private LocalDate date;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private boolean adminPermission;
    private WorkPlace workPlace;

    private Employee employee;

    public QueueReservation(LocalDate date,
                            LocalTime timeBegin,
                            LocalTime timeEnd,
                            boolean admin_permission,
                            Employee employee) {
        this.date = date;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.adminPermission = admin_permission;
        //this.idPlace = idPlace;
        this.employee = employee;
    }
}
