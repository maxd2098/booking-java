package plugin.atb.booking.entity;

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
public class QueueReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_queue_reservation", nullable = false)
    private Long idQueueReservation;

    private LocalDate date;
    private LocalTime timeBegin;
    private LocalTime timeEnd;
    private boolean adminPermission;
//    @ManyToOne
//    private WorkPlaceEntity id_work_place;
    @ManyToOne
    @JoinColumn(name="id_employee")
    private EmployeeEntity idEmployee;

    public QueueReservationEntity(LocalDate date,
                                  LocalTime timeBegin,
                                  LocalTime timeEnd,
                                  boolean adminPermission,
                                  EmployeeEntity idEmployee) {
        this.date = date;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.adminPermission = adminPermission;
        this.idEmployee = idEmployee;
        //this.id_work_place = id_work_place;
    }

    public QueueReservationEntity() {

    }
}
