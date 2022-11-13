package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plugin.atb.booking.model.WorkPlace;
import plugin.atb.booking.repository.WorkPlaceRepository;

@Service
@RequiredArgsConstructor
public class WorkPlaceService {
    private final WorkPlaceRepository workPlaceRepository;

    public void createWorkPlace(WorkPlace workPlace) {
        workPlaceRepository.save(workPlace);
    }

    public WorkPlace readWorkPlace(Long idWorkPlace) {
        return workPlaceRepository.findByIdWorkPlace(idWorkPlace);
    }

    public void updateWorkPlace(WorkPlace workPlace) {
        workPlaceRepository.save(workPlace);
    }

    public void deleteWorkPlace(Long idWorkPlace) {
        workPlaceRepository.deleteById(idWorkPlace);
    }
}

