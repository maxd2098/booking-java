package plugin.atb.booking.mapper;

import plugin.atb.booking.dto.EmployeeDto;
import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.entity.EmployeeEntity;
import plugin.atb.booking.entity.WorkPlaceEntity;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.model.WorkPlace;

public class WorkPlaceMapper {
    public static WorkPlace mapWorkPlaceDtoToWorkPlace(WorkPlaceDto workPlaceDto) {
        return new WorkPlace(
                workPlaceDto.isType(),
                workPlaceDto.getNumSeats(),
                workPlaceDto.getNumLevel(),
                workPlaceDto.getInfo()
        );
    }

    public static WorkPlaceEntity mapWorkPlaceToWorkPlaceEntity(WorkPlace workPlace) {
        return new WorkPlaceEntity(
                workPlace.isType(),
                workPlace.getNumSeats(),
                workPlace.getNumLevel(),
                workPlace.getInfo()
        );
    }
}
