package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
public class QueueReservationDto {
    private LocalDate date;

    private String timeBegin;

    private String timeEnd;

    private boolean adminPermission;

    private Long idEmployee;

    private Long idWorkPlace;
}
