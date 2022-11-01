package plugin.atb.booking.controller;

import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.model.WorkPlace;
import plugin.atb.booking.service.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workPlace")
public class WorkPlaceController {
    @Autowired
    WorkPlaceService workPlaceService;

    @PostMapping("/")
    public void createWorkPlace(WorkPlaceDto workPlaceDto) {
        workPlaceService.createWorkPlace(workPlaceDto);
    }

    @GetMapping("/")
    public WorkPlace readWorkPlace(int idWorkPlace) {
        return workPlaceService.readWorkPlace(idWorkPlace);
    }

    @PutMapping("/")
    public void updateWorkPlace(WorkPlaceDto workPlaceDto) {
        workPlaceService.updateWorkPlace(workPlaceDto);
    }

    @DeleteMapping("/")
    public void deleteWorkPlace(int idWorkPlace) {
        workPlaceService.deleteWorkPlace(idWorkPlace);
    }
}
