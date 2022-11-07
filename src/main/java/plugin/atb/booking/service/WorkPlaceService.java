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

    public void createWorkPlace(WorkPlaceDto workPlaceDto) {
        try {
            WorkPlace workPlace = WorkPlaceMapper.mapWorkPlaceDtoToWorkPlace(workPlaceDto);
            workPlaceRepository.save(workPlace);
            System.out.println("Новое рабочее место было создано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public WorkPlace readWorkPlace(Long idWorkPlace) {
        try {
            WorkPlace workPlace = workPlaceRepository.findAll().stream()
                    .filter(e -> Objects.equals(e.getIdWorkPlace(), idWorkPlace))
                    .findFirst().orElseThrow(() -> new RuntimeException("Рабочее место с номером " + idWorkPlace
                            + " не найдено"));
            System.out.println("Рабочее место с номером " + idWorkPlace + " было получено");
            return workPlace;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void updateWorkPlace(Long idWorkPlace, WorkPlaceDto workPlaceDto) {
        try {
            WorkPlace workPlace = workPlaceRepository.findAll().stream()
                    .filter(e -> Objects.equals(e.getIdWorkPlace(), idWorkPlace))
                    .findFirst().orElseThrow(() -> new RuntimeException("Рабочее место с номером " + idWorkPlace
                            + " не найдено для изменения"));
            workPlace.setType(workPlaceDto.isType());
            if (workPlaceDto.getNumSeats() >= 0) {
                workPlace.setNumSeats(workPlaceDto.getNumSeats());
            }
            if (workPlaceDto.getNumLevel() >= 0) {
                workPlace.setNumLevel(workPlaceDto.getNumLevel());
            }
            if (workPlaceDto.getInfo() != null) {
                workPlace.setInfo(workPlaceDto.getInfo());
            }
            workPlaceRepository.save(workPlace);
            System.out.println("Рабочее место с номером " + idWorkPlace + " было отреактировано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteWorkPlace(Long idWorkPlace) {
        try {
            boolean checkDeleteWorkPlace = workPlaceRepository.findAll().stream()
                    .anyMatch(e -> Objects.equals(e.getIdWorkPlace(), idWorkPlace));
            if (checkDeleteWorkPlace) {
                workPlaceRepository.deleteById(idWorkPlace);
                System.out.println("Рабочее место с номером " + idWorkPlace + " было удалено");
            } else {
                throw new Exception("Рабочее место с номером " + idWorkPlace + " не найдено для удаления");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

