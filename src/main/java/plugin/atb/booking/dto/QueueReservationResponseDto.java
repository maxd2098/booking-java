package plugin.atb.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QueueReservationResponseDto {
    private Long idQueueReservation;

    private LocalDate date;

    private LocalTime timeBegin;

    private LocalTime timeEnd;

    private boolean adminPermission;

    private Long idEmployee;

    private Long idWorkPlace;
}
