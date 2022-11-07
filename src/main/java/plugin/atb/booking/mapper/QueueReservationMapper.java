package plugin.atb.booking.mapper;


import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.entity.EmployeeEntity;
import plugin.atb.booking.entity.QueueReservationEntity;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.model.QueueReservation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class QueueReservationMapper {

    public static QueueReservation mapEntityToQueueReservation(QueueReservationEntity queueReservationEntity) {
        return new QueueReservation(queueReservationEntity.getDate(),
                queueReservationEntity.getTimeBegin(),
                queueReservationEntity.getTimeEnd(),
                queueReservationEntity.isAdminPermission(),
                EmployeeMapper.mapEmployeeEntityToEmployee(queueReservationEntity.getIdEmployee())
        );
    }

    public static QueueReservation mapDtoToQueueReservation(QueueReservationDto queueReservationDto, Employee employee) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime timeBegin = LocalTime.parse(queueReservationDto.getTimeBegin(), formatter);
        LocalTime timeEnd = LocalTime.parse(queueReservationDto.getTimeEnd(), formatter);
        return new QueueReservation(queueReservationDto.getDate(),
                timeBegin,
                timeEnd,
                queueReservationDto.isAdminPermission(),
                employee
        );
    }

    public static QueueReservationEntity mapQueueReservationToEntity(QueueReservation queueReservation) {
        EmployeeEntity employeeEntity = EmployeeMapper.mapEmployeeToEmployeeEntityForQueue(queueReservation.getEmployee());
        return new QueueReservationEntity(queueReservation.getDate(),
                queueReservation.getTimeBegin(),
                queueReservation.getTimeEnd(),
                queueReservation.isAdminPermission(),
                employeeEntity
        );
    }

}
