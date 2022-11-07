package plugin.atb.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "queue_reservation")
public class QueueReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_queue_reservation", nullable = false)
    private Long idQueueReservation;

    private LocalDate date;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private boolean adminPermission;

    @JoinColumn(name="id_employee")
    private Long idEmployee;

    @JoinColumn(name="id_work_place")
    private Long idWorkPlace;

    public QueueReservation(LocalDate date,
                            LocalTime timeBegin,
                            LocalTime timeEnd,
                            boolean adminPermission,
                            Long idEmployee,
                            Long idWorkPlace) {
        this.date = date;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.adminPermission = adminPermission;
        this.idEmployee = idEmployee;
        this.idWorkPlace = idWorkPlace;
    }

    public QueueReservation() {

    }
}
