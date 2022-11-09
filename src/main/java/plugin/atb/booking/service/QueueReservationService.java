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

    public void createQueueReservation(QueueReservationDto queueReservationDto) {
        try {
            Employee employee = EmployeeMapper.mapResponseDtoToEmployee(employeeController.readEmployeeById(queueReservationDto.getIdEmployee()));
            WorkPlace workPlace = WorkPlaceMapper.mapWorkPlaceDtoToWorkPlace(workPlaceController.readWorkPlace(queueReservationDto.getIdWorkPlace()));
            QueueReservation newQueueReservation = QueueReservationMapper.mapDtoToQueueReservation(queueReservationDto,
                    employee.getIdEmployee(), workPlace.getIdWorkPlace());
            LocalDate date = newQueueReservation.getDate();
            LocalTime timeBegin = newQueueReservation.getTimeBegin();
            LocalTime timeEnd = newQueueReservation.getTimeEnd();
            boolean adminPermission = newQueueReservation.isAdminPermission();
            List<QueueReservation> queueReservations = new ArrayList<>();
            StreamSupport.stream(queueReservationRepository.findAll().spliterator(), false)
                    .forEach(queueReservations::add);
            if (timeBegin.isAfter(timeEnd) || timeBegin.equals(timeEnd)) {
                throw new Exception("Конечное время больше начального либо равно ему");
            }
            boolean checkTime = queueReservations.stream()
                    .filter(r -> r.getDate().equals(date))
                    .filter(r -> Objects.equals(r.getIdWorkPlace(), workPlace.getIdWorkPlace()))
                    .anyMatch(r -> !(timeBegin.isBefore(r.getTimeBegin()) &&
                            (timeEnd.isBefore(r.getTimeBegin()) || timeEnd.equals(r.getTimeBegin()))
                            || (timeBegin.isAfter(r.getTimeEnd()) || timeBegin.equals(r.getTimeEnd()))
                            && timeEnd.isAfter(r.getTimeEnd())));
            if (checkTime) {
                throw new Exception("Данное место на указанное время уже забронировано");
            }
            boolean checkEmployee = queueReservations.stream()
                    .filter(r -> r.getDate().equals(date))
                    .filter(r -> r.getIdEmployee().equals(employee.getIdEmployee()))
                    .anyMatch(r -> !(timeBegin.isBefore(r.getTimeBegin()) &&
                            (timeEnd.isBefore(r.getTimeBegin()) || timeEnd.equals(r.getTimeBegin()))
                            || (timeBegin.isAfter(r.getTimeEnd()) || timeBegin.equals(r.getTimeEnd()))
                            && timeEnd.isAfter(r.getTimeEnd())));
            if (checkEmployee) {
                throw new Exception("Вы уже бронировали себе место на данное время");
            }
            queueReservationRepository.save(newQueueReservation);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            System.out.println("Время с " + formatter.format(timeBegin) + " до " + formatter.format(timeEnd) + " на стол " + newQueueReservation.getIdWorkPlace() +
                    " успешно забронировано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<QueueReservation> readQueueReservationByEmployeeId(Long idEmployee) throws Exception {
        try {
            List<QueueReservation> queueReservations = new ArrayList<>();
            StreamSupport.stream(queueReservationRepository.findAll().spliterator(), false)
                    .forEach(queueReservations::add);
            return queueReservations.stream()
                    .filter(q -> q.getIdEmployee().equals(idEmployee))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new Exception("Брони для пользователя с id" + idEmployee + " не найдены");
        }
    }

    public List<QueueReservation> readQueueReservationByWorkPlaceId(Long idWorkPlace) throws Exception {
        try {
            List<QueueReservation> queueReservations = new ArrayList<>();
            StreamSupport.stream(queueReservationRepository.findAll().spliterator(), false)
                    .forEach(queueReservations::add);
            return queueReservations.stream()
                    .filter(q -> q.getIdWorkPlace().equals(idWorkPlace))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new Exception("Брони на рабочий стол с id" + idWorkPlace + " не найдены");
        }
    }

    public void deleteQueueReservation(QueueReservationDto queueReservationDto) {
        try {
            List<QueueReservation> queueReservations = new ArrayList<>();
            StreamSupport.stream(queueReservationRepository.findAll().spliterator(), false)
                    .forEach(queueReservations::add);
            Employee employee = EmployeeMapper.mapResponseDtoToEmployee(employeeController.readEmployeeById(queueReservationDto.getIdEmployee()));
            WorkPlace workPlace = WorkPlaceMapper.mapWorkPlaceDtoToWorkPlace(workPlaceController.readWorkPlace(queueReservationDto.getIdWorkPlace()));
            QueueReservation newQueueReservation = QueueReservationMapper.mapDtoToQueueReservation(queueReservationDto,
                    employee.getIdEmployee(), workPlace.getIdWorkPlace());
            LocalDate date = newQueueReservation.getDate();
            LocalTime timeBegin = newQueueReservation.getTimeBegin();
            Long idWorkPlace = newQueueReservation.getIdWorkPlace();
            QueueReservation checkTimeForDelete = queueReservations.stream()
                    .filter(wp -> wp.getIdWorkPlace().equals(idWorkPlace)
                            && wp.getTimeBegin().equals(timeBegin)
                            && wp.getDate().equals(date))
                    .findFirst().orElseThrow(() -> new RuntimeException("Бронь на стол " + idWorkPlace +
                            " на время " + timeBegin + " не найдена"));
            queueReservationRepository.deleteById(checkTimeForDelete.getIdQueueReservation());
            System.out.println("Бронь на стол " + idWorkPlace + " на время " + timeBegin + " была удалена");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

