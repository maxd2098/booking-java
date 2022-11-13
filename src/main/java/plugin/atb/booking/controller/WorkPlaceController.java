package plugin.atb.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.exception.NotFoundException;
import plugin.atb.booking.exception.RangeNotSatisfiableException;
import plugin.atb.booking.mapper.WorkPlaceMapper;
import plugin.atb.booking.model.WorkPlace;
import plugin.atb.booking.service.WorkPlaceService;

@RestController
@RequestMapping("/workPlace")
@RequiredArgsConstructor
public class WorkPlaceController {
    private final WorkPlaceService workPlaceService;

    private final WorkPlaceMapper workPlaceMapper;

    @PostMapping("/")
    public ResponseEntity<Object> createWorkPlace(@RequestBody WorkPlaceDto workPlaceDto) {
        if (workPlaceDto.getNumSeats() <= 0) {
            throw new RangeNotSatisfiableException("Число мест должно быть больше 0");
        }
        WorkPlace workPlace = workPlaceMapper.mapWorkPlaceDtoToWorkPlace(workPlaceDto);
        workPlaceService.createWorkPlace(workPlace);
        System.out.println("Новое рабочее место было создано");
        return ResponseEntity.ok(workPlace);
    }

    @GetMapping("/{idWorkPlace}")
    public ResponseEntity<Object> readWorkPlace(@PathVariable Long idWorkPlace) {
        WorkPlace workPlace = workPlaceService.readWorkPlace(idWorkPlace);
        if (workPlace == null) {
            throw new NotFoundException("Место с номером " + idWorkPlace + " не найдено");
        }
        System.out.println("Место с номером " + idWorkPlace + " было получено");
        return ResponseEntity.ok(workPlaceMapper.mapWorkPlaceToWorkPlaceResponseDto(workPlace));
    }

    @PutMapping("/{idWorkPlace}")
    public ResponseEntity<Object> updateWorkPlace(
            @PathVariable Long idWorkPlace,
            @RequestBody WorkPlaceDto workPlaceDto) {
        WorkPlace workPlace = workPlaceService.readWorkPlace(idWorkPlace);
        if (workPlace == null) {
            throw new NotFoundException("Место с номером " + idWorkPlace + " не найдено для изменения");
        }
        workPlace.setType(workPlaceDto.isType());
        if (workPlaceDto.getNumSeats() <= 0) {
            throw new RangeNotSatisfiableException("Число мест должно быть больше 0");
        } else {
            workPlace.setNumSeats(workPlaceDto.getNumSeats());
        }
        workPlace.setNumLevel(workPlaceDto.getNumLevel());
        if (workPlaceDto.getInfo() != null) {
            workPlace.setInfo(workPlaceDto.getInfo());
        }
        workPlaceService.updateWorkPlace(workPlace);
        System.out.println("Место с номером " + idWorkPlace + " было отреактировано");
        return ResponseEntity.ok(workPlace);
    }

    @DeleteMapping("/{idWorkPlace}")
    public ResponseEntity<Object> deleteWorkPlace(@PathVariable Long idWorkPlace) {
        WorkPlace workPlace = workPlaceService.readWorkPlace(idWorkPlace);
        if (workPlace == null) {
            throw new NotFoundException("Место с номером " + idWorkPlace + " не найдено для удаления");
        } else {
            workPlaceService.deleteWorkPlace(idWorkPlace);
            System.out.println("Место с номером " + idWorkPlace + " было удалено");
            return ResponseEntity.ok(workPlace);
        }
    }
}
