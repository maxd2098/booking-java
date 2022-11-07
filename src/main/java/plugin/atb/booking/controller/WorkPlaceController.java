package plugin.atb.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.entity.WorkPlaceEntity;
import plugin.atb.booking.service.WorkPlaceService;

@RestController
@RequestMapping("/workPlace")
public class WorkPlaceController {
    @Autowired
    WorkPlaceService workPlaceService;

    @PostMapping("/")
    public void createWorkPlace(@RequestBody WorkPlaceDto workPlaceDto) {
        workPlaceService.createWorkPlace(workPlaceDto);
    }

    @GetMapping("/{idWorkPlace}")
    public WorkPlaceEntity readWorkPlace(@PathVariable Long idWorkPlace) {
        return workPlaceService.readWorkPlace(idWorkPlace);
    }

    @PutMapping("/{idWorkPlace}")
    public void updateWorkPlace(@PathVariable Long idWorkPlace, @RequestBody WorkPlaceDto workPlaceDto) {
        workPlaceService.updateWorkPlace(idWorkPlace, workPlaceDto);
    }

    @DeleteMapping("/{idWorkPlace}")
    public void deleteWorkPlace(@PathVariable Long idWorkPlace) {
        workPlaceService.deleteWorkPlace(idWorkPlace);
    }
}
