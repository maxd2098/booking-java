package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plugin.atb.booking.controller.EmployeeController;
import plugin.atb.booking.controller.WorkPlaceController;
import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.mapper.EmployeeMapper;
import plugin.atb.booking.mapper.QueueReservationMapper;
import plugin.atb.booking.mapper.WorkPlaceMapper;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.model.QueueReservation;
import plugin.atb.booking.model.WorkPlace;
import plugin.atb.booking.repository.QueueReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class QueueReservationService {
    private final EmployeeService employeeService;
    private final WorkPlaceService workPlaceService;
    private final QueueReservationRepository queueReservationRepository;
    private final EmployeeController employeeController;
    private final WorkPlaceController workPlaceController;

    public void createQueueReservation(QueueReservation queueReservation) {
        queueReservationRepository.save(queueReservation);
    }

    public void deleteQueueReservation(Long idQueueReservation) {
        queueReservationRepository.deleteById(idQueueReservation);
    }

}

