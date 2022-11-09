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

    @GetMapping("/idEmployee/{idEmployee}")
    public List<QueueReservation> readQueueReservationByEmployeeId(@PathVariable Long idEmployee) throws Exception {
        return queueReservationService.readQueueReservationByEmployeeId(idEmployee);
    }

    @GetMapping("/idWorkPlace/{idWorkPlace}")
    public List<QueueReservation> readQueueReservationByWorkPlaceId(@PathVariable Long idWorkPlace) throws Exception {
        return queueReservationService.readQueueReservationByWorkPlaceId(idWorkPlace);
    }

    @DeleteMapping("/")
    public void deleteQueueReservation(QueueReservationDto queueReservationDto) {
        queueReservationService.deleteQueueReservation(queueReservationDto);
    }
}
