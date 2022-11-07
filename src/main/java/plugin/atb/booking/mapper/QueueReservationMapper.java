package plugin.atb.booking.mapper;


import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.model.QueueReservation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class QueueReservationMapper {

    public static QueueReservation mapDtoToQueueReservation(QueueReservationDto queueReservationDto,
                                                            Long idEmployee,
                                                            Long idWorkPlace) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime timeBegin = LocalTime.parse(queueReservationDto.getTimeBegin(), formatter);
        LocalTime timeEnd = LocalTime.parse(queueReservationDto.getTimeEnd(), formatter);
        return new QueueReservation(queueReservationDto.getDate(),
                timeBegin,
                timeEnd,
                queueReservationDto.isAdminPermission(),
                idEmployee,
                idWorkPlace
        );
    }

}
