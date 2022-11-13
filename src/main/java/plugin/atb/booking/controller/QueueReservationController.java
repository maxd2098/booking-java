package plugin.atb.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plugin.atb.booking.dto.AdminPermissionUpdateDto;
import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.dto.QueueReservationUpdateDto;
import plugin.atb.booking.exception.ConflictException;
import plugin.atb.booking.exception.NotFoundException;
import plugin.atb.booking.mapper.QueueReservationMapper;
import plugin.atb.booking.model.Employee;
import plugin.atb.booking.model.QueueReservation;
import plugin.atb.booking.model.WorkPlace;
import plugin.atb.booking.service.EmployeeService;
import plugin.atb.booking.service.QueueReservationService;
import plugin.atb.booking.service.WorkPlaceService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/queueReservation")
@RequiredArgsConstructor
public class QueueReservationController {
    private final QueueReservationService queueReservationService;

    private final EmployeeService employeeService;

    private final WorkPlaceService workPlaceService;

    private final QueueReservationMapper queueReservationMapper;

    @PostMapping("/")
    public ResponseEntity<Object> createQueueReservation(@RequestBody QueueReservationDto queueReservationDto) {
        Employee employee = employeeService.readEmployeeById(queueReservationDto.getIdEmployee());
        if (employee == null) {
            throw new NotFoundException("Пользователь с id " + queueReservationDto.getIdEmployee() + " не найден");
        }

        WorkPlace workPlace = workPlaceService.readWorkPlace(queueReservationDto.getIdWorkPlace());
        if (workPlace == null) {
            throw new NotFoundException("Рабочее место с id " + queueReservationDto.getIdWorkPlace() + " не найдено");
        }

        QueueReservation newQueueReservation = queueReservationMapper.mapDtoToQueueReservation(queueReservationDto);
        LocalTime timeBegin = newQueueReservation.getTimeBegin();
        LocalTime timeEnd = newQueueReservation.getTimeEnd();
        Long idEmployee = newQueueReservation.getIdEmployee();
        Long idWorkPlace = newQueueReservation.getIdWorkPlace();

        if (timeBegin.isAfter(timeEnd) || timeBegin.equals(timeEnd)) {
            throw new ConflictException("Конечное время больше начального либо равно ему");
        }

        List<QueueReservation> readListQueueReservationByIdWorkplace =
                new ArrayList<>(queueReservationService.readQueueReservationByIdWorkplace(idWorkPlace));
        boolean checkWorkPlace = queueReservationService.checkOnBusyTime(
                readListQueueReservationByIdWorkplace,
                newQueueReservation);
        if (checkWorkPlace) {
            throw new ConflictException("Место с id " + idWorkPlace + " на указанное время уже забронировано");
        }

        List<QueueReservation> readListQueueReservationByIdEmployee =
                new ArrayList<>(queueReservationService.readQueueReservationByIdEmployee(idEmployee));
        boolean checkEmployee = queueReservationService.checkOnBusyTime(
                readListQueueReservationByIdEmployee,
                newQueueReservation);
        if (checkEmployee) {
            throw new ConflictException("Вы уже бронировали себе место на данное время");
        }

        queueReservationService.createQueueReservation(newQueueReservation);
        System.out.println(
                "Время с " + timeBegin + " до " + timeEnd + " на стол " + idWorkPlace + " забронировано");
        return ResponseEntity.ok(newQueueReservation);
    }

    @GetMapping("/idQueueReservation/{idQueueReservation}")
    public ResponseEntity<Object> readQueueReservationById(@PathVariable Long idQueueReservation) {
        QueueReservation queueReservation = queueReservationService.readQueueReservationById(idQueueReservation);
        if (queueReservation == null) {
            throw new NotFoundException("Бронь с id " + idQueueReservation + " не найдена");
        }
        return ResponseEntity.ok(queueReservationMapper.mapQueueReservationToResponseDto(queueReservation));
    }

    @GetMapping("/idEmployee/{idEmployee}")
    public ResponseEntity<List<QueueReservation>> readQueueReservationByIdEmployee(@PathVariable Long idEmployee) {
        List<QueueReservation> listQueueReservation =
                new ArrayList<>(queueReservationService.readQueueReservationByIdEmployee(idEmployee));
        if (listQueueReservation.isEmpty()) {
            throw new NotFoundException("Брони для пользователя с id " + idEmployee + " не найдены");
        } else {
            return ResponseEntity.ok(listQueueReservation);
        }
    }

    @GetMapping("/idWorkPlace/{idWorkPlace}")
    public ResponseEntity<List<QueueReservation>> readQueueReservationByIdWorkPlace(@PathVariable Long idWorkPlace) {
        List<QueueReservation> listQueueReservation =
                new ArrayList<>(queueReservationService.readQueueReservationByIdWorkplace(idWorkPlace));
        if (listQueueReservation.isEmpty()) {
            throw new NotFoundException("Брони на рабочий стол с id " + idWorkPlace + " не найдены");
        } else {
            return ResponseEntity.ok(listQueueReservation);
        }
    }

    @PutMapping("/updateQueueReservation")
    public ResponseEntity<Object> updateQueueReservation(
            @RequestBody QueueReservationUpdateDto queueReservationUpdateDto) {

        Long idQueueReservation = queueReservationUpdateDto.getIdQueueReservation();
        Long idWorkPlace = queueReservationUpdateDto.getIdWorkPlace();

        QueueReservation queueReservation = queueReservationService.readQueueReservationById(idQueueReservation);
        if (queueReservation == null) {
            throw new NotFoundException("Бронь с id " + idQueueReservation + " не найдена для изменения");
        }

        WorkPlace workPlace = workPlaceService.readWorkPlace(idWorkPlace);
        if (workPlace == null) {
            throw new NotFoundException("Рабочее место с id " + idWorkPlace + " не найдено для изменения брони");
        }

        LocalTime timeBegin;
        LocalTime timeEnd;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        if (queueReservationUpdateDto.getTimeBegin() == null) {
            timeBegin = queueReservation.getTimeBegin();
        } else {
            timeBegin = LocalTime.parse(queueReservationUpdateDto.getTimeBegin(), formatter);
            queueReservation.setTimeBegin(timeBegin);
        }

        if (queueReservationUpdateDto.getTimeEnd() == null) {
            timeEnd = queueReservation.getTimeEnd();
        } else {
            timeEnd = LocalTime.parse(queueReservationUpdateDto.getTimeEnd(), formatter);
            queueReservation.setTimeEnd(timeEnd);
        }

        if (timeBegin.isAfter(timeEnd) || timeBegin.equals(timeEnd)) {
            throw new ConflictException("Конечное время больше начального либо равно ему");
        }

        queueReservation.setIdWorkPlace(idWorkPlace);
        List<QueueReservation> readListQueueReservationByIdWorkplace =
                new ArrayList<>(queueReservationService.readQueueReservationByIdWorkplace(idWorkPlace));
        readListQueueReservationByIdWorkplace.remove(queueReservation);
        boolean checkWorkPlace = queueReservationService.checkOnBusyTime(
                readListQueueReservationByIdWorkplace,
                queueReservation);
        if (checkWorkPlace) {
            throw new ConflictException("Место с id " + idWorkPlace + " на указанное время уже забронировано");
        }

        Long idEmployee = queueReservation.getIdEmployee();
        List<QueueReservation> readListQueueReservationByIdEmployee =
                new ArrayList<>(queueReservationService.readQueueReservationByIdEmployee(idEmployee));
        readListQueueReservationByIdEmployee.remove(queueReservation);
        boolean checkEmployee = queueReservationService.checkOnBusyTime(
                readListQueueReservationByIdEmployee,
                queueReservation);
        if (checkEmployee) {
            throw new ConflictException("Вы уже бронировали себе место на данное время");
        }

        queueReservationService.updateQueueReservation(queueReservation);
        System.out.println("Время с " + timeBegin + " до " + timeEnd + " на стол " + idWorkPlace + " забронировано");
        return ResponseEntity.ok(queueReservation);
    }

    @PutMapping("/updateAdminPermission")
    public ResponseEntity<Object> updateAdminPermission(
            @RequestBody AdminPermissionUpdateDto adminPermissionUpdateDto) {

        Long idQueueReservation = adminPermissionUpdateDto.getIdQueueReservation();
        boolean adminPermission = adminPermissionUpdateDto.isAdminPermission();
        QueueReservation queueReservation = queueReservationService.readQueueReservationById(idQueueReservation);
        if (queueReservation == null) {
            throw new NotFoundException("Бронь с id " + idQueueReservation + " не найдена для изменения статуса");
        }
        queueReservation.setAdminPermission(adminPermission);
        queueReservationService.updateQueueReservation(queueReservation);
        System.out.println("У брони с id " + idQueueReservation + " изменен статус на " + adminPermission);
        return ResponseEntity.ok(queueReservation);
    }

    @DeleteMapping("/{idQueueReservation}")
    public ResponseEntity<Object> deleteQueueReservation(@PathVariable Long idQueueReservation) {
        QueueReservation queueReservation = queueReservationService.readQueueReservationById(idQueueReservation);
        if (queueReservation == null) {
            throw new NotFoundException("Бронь с id " + idQueueReservation + " не найдена");
        } else {
            queueReservationService.deleteQueueReservation(idQueueReservation);
            System.out.println("Бронь с id " + idQueueReservation + " была удалена");
            return ResponseEntity.ok(queueReservation);
        }
    }
}
