package plugin.atb.booking.service;

import plugin.atb.booking.dto.QueueReservationDto;
import plugin.atb.booking.model.QueueReservation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueueReservationService {
    public ArrayList<QueueReservation> queue = new ArrayList<>();

    public void createQueueReservation(QueueReservationDto queueReservationDto) {
        LocalDateTime timeBegin = queueReservationDto.getTimeBegin();
        LocalDateTime timeEnd = queueReservationDto.getTimeEnd();
        int idPlace = queueReservationDto.getIdPlace();
        String login = queueReservationDto.getLogin();
        try {
            if (timeBegin.isAfter(timeEnd) || timeBegin.equals(timeEnd)) {
                throw new Exception("Конечное время больше начального либо равно ему");
            }
            boolean checkTime = queue.stream()
                    .filter(r -> r.getIdPlace() == idPlace)
                    .anyMatch(r -> !(timeBegin.isBefore(r.getTimeBegin()) &&
                            (timeEnd.isBefore(r.getTimeBegin()) || timeEnd.isEqual(r.getTimeBegin()))
                            || (timeBegin.isAfter(r.getTimeEnd()) || timeBegin.isEqual(r.getTimeEnd()))
                            && timeEnd.isAfter(r.getTimeEnd())));
            if (checkTime) {
                throw new Exception("Данное место на указанное время уже забронировано");
            }
            boolean checkEmployee = queue.stream()
                    .filter(r -> r.getLogin().equals(login))
                    .anyMatch(r -> !(timeBegin.isBefore(r.getTimeBegin()) &&
                            (timeEnd.isBefore(r.getTimeBegin()) || timeEnd.isEqual(r.getTimeBegin()))
                            || (timeBegin.isAfter(r.getTimeEnd()) || timeBegin.isEqual(r.getTimeEnd()))
                            && timeEnd.isAfter(r.getTimeEnd())));
            if (checkEmployee) {
                throw new Exception("Вы уже бронировали себе место на данное время");
            }
            QueueReservation newQueue = new QueueReservation(timeBegin, timeEnd, idPlace, login);
            queue.add(newQueue);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            System.out.println("Время с " + formatter.format(timeBegin) + " до " + formatter.format(timeEnd) + " на стол " + idPlace +
                    " успешно забронировано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<QueueReservation> readQueueReservationForEmployee(String login) throws Exception {
        try {
            return queue.stream()
                    .filter(q -> q.getLogin().equals(login))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new Exception("Брони для пользователя " + login + " не найдены");
        }
    }

    public List<QueueReservation> readQueueReservationForWorkPlace(int idWorkPlace) throws Exception {
        try {
            return queue.stream()
                    .filter(q -> q.getIdPlace() == idWorkPlace)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new Exception("Брони для стола " + idWorkPlace + " не найдены");
        }
    }

    public void deleteQueueReservation(QueueReservationDto queueReservationDto) {
        try {
            LocalDateTime timeBegin = queueReservationDto.getTimeBegin();
            int idWorkPlace = queueReservationDto.getIdPlace();
            if (queue.removeIf(wp -> wp.getIdPlace() == idWorkPlace && wp.getTimeBegin().isEqual(timeBegin))) {
                System.out.println("Бронь на стол " + idWorkPlace + " на время " + timeBegin + " была удалена");
            } else {
                throw new Exception("Бронь на стол " + idWorkPlace + " на время " + timeBegin + " не найдена");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

