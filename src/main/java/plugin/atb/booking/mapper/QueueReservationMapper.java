package plugin.atb.booking.mapper;

import org.springframework.stereotype.Component;
import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.dto.QueueReservationResponseDto;
import plugin.atb.booking.model.QueueReservation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class QueueReservationMapper {
    public QueueReservation mapDtoToQueueReservation(QueueReservationDto queueReservationDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime timeBegin = LocalTime.parse(queueReservationDto.getTimeBegin(), formatter);
        LocalTime timeEnd = LocalTime.parse(queueReservationDto.getTimeEnd(), formatter);
        return new QueueReservation(
                queueReservationDto.getDate(),
                timeBegin,
                timeEnd,
                queueReservationDto.isAdminPermission(),
                queueReservationDto.getIdEmployee(),
                queueReservationDto.getIdWorkPlace()
        );
    }

    public QueueReservationResponseDto mapQueueReservationToResponseDto(QueueReservation queueReservation) {
        return new QueueReservationResponseDto(
                queueReservation.getIdQueueReservation(),
                queueReservation.getDate(),
                queueReservation.getTimeBegin(),
                queueReservation.getTimeEnd(),
                queueReservation.isAdminPermission(),
                queueReservation.getIdEmployee(),
                queueReservation.getIdWorkPlace()
        );
    }
}
