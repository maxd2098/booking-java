package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class QueueReservationUpdateDto {
    private Long idQueueReservation;

    private String timeBegin;

    private String timeEnd;

    private Long idWorkPlace;
}
