package plugin.atb.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.dto.WorkPlaceResponseDto;
import plugin.atb.booking.mapper.WorkPlaceMapper;
import plugin.atb.booking.model.WorkPlace;
import plugin.atb.booking.repository.WorkPlaceRepository;
import plugin.atb.booking.service.WorkPlaceService;

import java.util.Objects;

@RestController
@RequestMapping("/workPlace")
@RequiredArgsConstructor
public class WorkPlaceController {
    private final WorkPlaceService workPlaceService;
    private final WorkPlaceRepository workPlaceRepository;

    @PostMapping("/")
    public void createWorkPlace(@RequestBody WorkPlaceDto workPlaceDto) {
        try {
            WorkPlace workPlace = WorkPlaceMapper.mapWorkPlaceDtoToWorkPlace(workPlaceDto);
            workPlaceService.createWorkPlace(workPlace);
            System.out.println("Новое рабочее место было создано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @GetMapping("/{idWorkPlace}")
    public WorkPlaceResponseDto readWorkPlace(@PathVariable Long idWorkPlace) {
        try {
            WorkPlace workPlace = workPlaceRepository.findAll().stream()
                    .filter(e -> Objects.equals(e.getIdWorkPlace(), idWorkPlace))
                    .findFirst().orElseThrow(() -> new RuntimeException("Рабочее место с номером " + idWorkPlace
                            + " не найдено"));
            System.out.println("Рабочее место с номером " + idWorkPlace + " было получено");
            return WorkPlaceMapper.mapWorkPlaceToWorkPlaceResponseDto(workPlace);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @PutMapping("/{idWorkPlace}")
    public void updateWorkPlace(@PathVariable Long idWorkPlace, @RequestBody WorkPlaceDto workPlaceDto) {
        try {
            WorkPlace workPlace = workPlaceRepository.findAll().stream()
                    .filter(e -> Objects.equals(e.getIdWorkPlace(), idWorkPlace))
                    .findFirst().orElseThrow(() -> new RuntimeException("Рабочее место с номером " + idWorkPlace
                            + " не найдено для изменения"));
            workPlace.setType(workPlaceDto.isType());
            workPlace.setNumSeats(workPlaceDto.getNumSeats());
            workPlace.setNumLevel(workPlaceDto.getNumLevel());
            if (workPlaceDto.getInfo() != null) {
                workPlace.setInfo(workPlaceDto.getInfo());
            }
            workPlaceService.updateWorkPlace(workPlace);
            System.out.println("Рабочее место с номером " + idWorkPlace + " было отреактировано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @DeleteMapping("/{idWorkPlace}")
    public void deleteWorkPlace(@PathVariable Long idWorkPlace) {
        try {
            boolean checkDeleteWorkPlace = workPlaceRepository.findAll().stream()
                    .anyMatch(e -> Objects.equals(e.getIdWorkPlace(), idWorkPlace));
            if (checkDeleteWorkPlace) {
                workPlaceService.deleteWorkPlace(idWorkPlace);
                System.out.println("Рабочее место с номером " + idWorkPlace + " было удалено");
            } else {
                throw new Exception("Рабочее место с номером " + idWorkPlace + " не найдено для удаления");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
