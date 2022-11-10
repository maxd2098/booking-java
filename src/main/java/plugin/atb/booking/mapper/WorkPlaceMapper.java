package plugin.atb.booking.mapper;

import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.dto.WorkPlaceResponseDto;
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

    public static WorkPlaceResponseDto mapWorkPlaceToWorkPlaceResponseDto(WorkPlace workPlace) {
        return new WorkPlaceResponseDto(
                workPlace.getIdWorkPlace(),
                workPlace.isType(),
                workPlace.getNumSeats(),
                workPlace.getNumLevel(),
                workPlace.getInfo()
        );
    }

    public static WorkPlace mapWorkPlaceResponseDtoToWorkPlace(WorkPlaceResponseDto workPlaceResponseDto) {
        return new WorkPlace(
                workPlaceResponseDto.getIdWorkPlace(),
                workPlaceResponseDto.isType(),
                workPlaceResponseDto.getNumSeats(),
                workPlaceResponseDto.getNumLevel(),
                workPlaceResponseDto.getInfo()
        );
    }
}
