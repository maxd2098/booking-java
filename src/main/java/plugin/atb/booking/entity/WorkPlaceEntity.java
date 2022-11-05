package plugin.atb.booking.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "work_place")
public class WorkPlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_place", nullable = false)
    private Long idWorkPlace;
    private boolean type;
    private int numSeats;
    private int numLevel;
    private String info;

    public WorkPlaceEntity() {
    }

    public WorkPlaceEntity(Long idWorkPlace, boolean type, int numSeats, int numLevel, String info) {
        this.idWorkPlace = idWorkPlace;
        this.type = type;
        this.numSeats = numSeats;
        this.numLevel = numLevel;
        this.info = info;
    }

    public WorkPlaceEntity(boolean type, int numSeats, int numLevel, String info) {
        this.type = type;
        this.numSeats = numSeats;
        this.numLevel = numLevel;
        this.info = info;
    }
}
