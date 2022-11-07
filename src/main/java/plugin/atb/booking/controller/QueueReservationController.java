package plugin.atb.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.model.QueueReservation;
import plugin.atb.booking.service.QueueReservationService;

import java.util.List;

@RestController
@RequestMapping("/queueReservation")
public class QueueReservationController {

    @Autowired
    QueueReservationService queueReservationService;

    @PostMapping("/")
    public void createQueueReservation(QueueReservationDto queueReservationDto) {
        queueReservationService.createQueueReservation(queueReservationDto);
    }

    @GetMapping("/{idEmployee}")
    public List<QueueReservation> readQueueReservationForEmployee(@PathVariable Long idEmployee) throws Exception {
        return queueReservationService.readQueueReservationByEmployeeId(idEmployee);
    }

//    @GetMapping("/idWorkPlace")
//    public List<QueueReservation> readQueueReservationForWorkPlace(int idWorkPlace) throws Exception {
//        return queueReservationService.readQueueReservationForWorkPlace(idWorkPlace);
//    }

    @DeleteMapping("/")
    public void deleteQueueReservation(QueueReservationDto queueReservationDto) {
        queueReservationService.deleteQueueReservation(queueReservationDto);
    }
}
