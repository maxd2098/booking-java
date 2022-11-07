package plugin.atb.booking.mapper;

import plugin.atb.booking.dto.WorkPlaceDto;
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
}
