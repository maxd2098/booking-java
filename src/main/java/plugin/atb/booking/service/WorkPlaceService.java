package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.mapper.WorkPlaceMapper;
import plugin.atb.booking.model.WorkPlace;
import plugin.atb.booking.repository.WorkPlaceRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkPlaceService {
    private final WorkPlaceRepository workPlaceRepository;

    public void createWorkPlace(WorkPlace workPlace) {
        workPlaceRepository.save(workPlace);
    }

    public void updateWorkPlace(WorkPlace workPlace) {
        workPlaceRepository.save(workPlace);
    }

    public void deleteWorkPlace(Long idWorkPlace) {
        workPlaceRepository.deleteById(idWorkPlace);
    }

}

