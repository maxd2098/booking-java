package plugin.atb.booking.mapper;

import org.springframework.stereotype.Component;
import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.dto.WorkPlaceResponseDto;
import plugin.atb.booking.model.WorkPlace;

@Component
public class WorkPlaceMapper {
    public WorkPlace mapWorkPlaceDtoToWorkPlace(WorkPlaceDto workPlaceDto) {
        return new WorkPlace(
                workPlaceDto.isType(),
                workPlaceDto.getNumSeats(),
                workPlaceDto.getNumLevel(),
                workPlaceDto.getInfo()
        );
    }

    public WorkPlaceResponseDto mapWorkPlaceToWorkPlaceResponseDto(WorkPlace workPlace) {
        return new WorkPlaceResponseDto(
                workPlace.getIdWorkPlace(),
                workPlace.isType(),
                workPlace.getNumSeats(),
                workPlace.getNumLevel(),
                workPlace.getInfo()
        );
    }
}
