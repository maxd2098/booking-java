package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.mapper.QueueReservationMapper;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.model.QueueReservation;
import plugin.atb.booking.repository.QueueReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class QueueReservationService {
    //public ArrayList<QueueReservation> queue = new ArrayList<>();
    @Autowired
    EmployeeService employeeService;

    private final QueueReservationRepository queueReservationRepository;

    public void createQueueReservation(QueueReservationDto queueReservationDto) {
        Employee employee = employeeService.readEmployeeById(queueReservationDto.getIdEmployee());
        QueueReservation newQueueReservation = QueueReservationMapper.mapDtoToQueueReservation(queueReservationDto, employee);
        LocalDate date = newQueueReservation.getDate();
        LocalTime timeBegin = newQueueReservation.getTimeBegin();
        LocalTime timeEnd = newQueueReservation.getTimeEnd();
        boolean adminPermission = newQueueReservation.isAdminPermission();
        List<QueueReservation> queueReservations = new ArrayList<QueueReservation>();
        StreamSupport.stream(queueReservationRepository.findAll().spliterator(), false)
                .forEach(tt -> queueReservations.add(QueueReservationMapper.mapEntityToQueueReservation(tt)));

        try {
            if (timeBegin.isAfter(timeEnd) || timeBegin.equals(timeEnd)) {
                throw new Exception("Конечное время больше начального либо равно ему");
            }
            boolean checkTime = queueReservations.stream()
                    .filter(r -> r.getDate().equals(date))
                    //.filter(r -> r.getIdPlace() == idPlace)
                    .anyMatch(r -> !(timeBegin.isBefore(r.getTimeBegin()) &&
                            (timeEnd.isBefore(r.getTimeBegin()) || timeEnd.equals(r.getTimeBegin()))
                            || (timeBegin.isAfter(r.getTimeEnd()) || timeBegin.equals(r.getTimeEnd()))
                            && timeEnd.isAfter(r.getTimeEnd())));
            if (checkTime) {
                throw new Exception("Данное место на указанное время уже забронировано");
            }
            boolean checkEmployee = queueReservations.stream()
                    .filter(r -> r.getDate().equals(date))
                    .filter(r -> r.getEmployee().getIdEmployee().equals(employee.getIdEmployee()))
                    .anyMatch(r -> !(timeBegin.isBefore(r.getTimeBegin()) &&
                            (timeEnd.isBefore(r.getTimeBegin()) || timeEnd.equals(r.getTimeBegin()))
                            || (timeBegin.isAfter(r.getTimeEnd()) || timeBegin.equals(r.getTimeEnd()))
                            && timeEnd.isAfter(r.getTimeEnd())));
            if (checkEmployee) {
                throw new Exception("Вы уже бронировали себе место на данное время");
            }
            queueReservationRepository.save(QueueReservationMapper.mapQueueReservationToEntity(newQueueReservation));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            System.out.println("Время с " + formatter.format(timeBegin) + " до " + formatter.format(timeEnd) + " на стол " + newQueueReservation.getWorkPlace().getIdWorkPlace() +
                    " успешно забронировано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<QueueReservation> readQueueReservationByEmployeeId(Long id) throws Exception {
        List<QueueReservation> queueReservations = new ArrayList<QueueReservation>();
        StreamSupport.stream(queueReservationRepository.findAll().spliterator(), false)
                .forEach(tt -> queueReservations.add(QueueReservationMapper.mapEntityToQueueReservation(tt)));
        try {
            return queueReservations.stream()
                    .filter(q -> q.getEmployee().getIdEmployee().equals(id))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new Exception("Брони для пользователя с id" + id + " не найдены");
        }
    }

    public void deleteQueueReservation(QueueReservationDto queueReservationDto) {
        try {
            List<QueueReservation> queueReservations = new ArrayList<QueueReservation>();
            StreamSupport.stream(queueReservationRepository.findAll().spliterator(), false)
                    .forEach(tt -> queueReservations.add(QueueReservationMapper.mapEntityToQueueReservation(tt)));
            Employee employee = employeeService.readEmployeeById(queueReservationDto.getIdEmployee());
            QueueReservation newQueueReservation = QueueReservationMapper.mapDtoToQueueReservation(queueReservationDto, employee);
            LocalDate date = newQueueReservation.getDate();
            LocalTime timeBegin = newQueueReservation.getTimeBegin();
            Long idWorkPlace = newQueueReservation.getWorkPlace().getIdWorkPlace();
            if (queueReservations.removeIf(wp -> wp.getWorkPlace().getIdWorkPlace().equals(idWorkPlace)
                    && wp.getTimeBegin().equals(timeBegin)
                    && wp.getDate().equals(date))) {
                System.out.println("Бронь на стол " + idWorkPlace + " на время " + timeBegin + " была удалена");
            } else {
                throw new Exception("Бронь на стол " + idWorkPlace + " на время " + timeBegin + " не найдена");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

