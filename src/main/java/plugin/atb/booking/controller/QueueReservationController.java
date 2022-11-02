package plugin.atb.booking.controller;

import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.model.QueueReservation;
import plugin.atb.booking.service.QueueReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/login")
    public List<QueueReservation> readQueueReservationForEmployee(String login) throws Exception {
        return queueReservationService.readQueueReservationForEmployee(login);
    }

    @GetMapping("/idWorkPlace")
    public List<QueueReservation> readQueueReservationForWorkPlace(int idWorkPlace) throws Exception {
        return queueReservationService.readQueueReservationForWorkPlace(idWorkPlace);
    }

    @DeleteMapping("/")
    public void deleteQueueReservation(QueueReservationDto queueReservationDto) {
        queueReservationService.deleteQueueReservation(queueReservationDto);
    }
}
