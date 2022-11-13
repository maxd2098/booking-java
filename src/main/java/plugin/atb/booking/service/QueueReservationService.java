package plugin.atb.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plugin.atb.booking.model.QueueReservation;
import plugin.atb.booking.repository.QueueReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueReservationService {
    private final QueueReservationRepository queueReservationRepository;

    public void createQueueReservation(QueueReservation queueReservation) {
        queueReservationRepository.save(queueReservation);
    }

    public QueueReservation readQueueReservationById(Long idQueueReservation) {
        return queueReservationRepository.findByIdQueueReservation(idQueueReservation);
    }

    public List<QueueReservation> readQueueReservationByIdEmployee(Long idEmployee) {
        return queueReservationRepository.findAllByIdEmployee(idEmployee);
    }

    public List<QueueReservation> readQueueReservationByIdWorkplace(Long idWorkPlace) {
        return queueReservationRepository.findAllByIdWorkPlace(idWorkPlace);
    }

    public void updateQueueReservation(QueueReservation queueReservation) {
        queueReservationRepository.save(queueReservation);
    }

    public void deleteQueueReservation(Long idQueueReservation) {
        queueReservationRepository.deleteById(idQueueReservation);
    }

    public boolean checkOnBusyTime(List<QueueReservation> queueReservationList, QueueReservation queueReservation) {
        LocalDate date = queueReservation.getDate();
        LocalTime timeBegin = queueReservation.getTimeBegin();
        LocalTime timeEnd = queueReservation.getTimeEnd();
        return queueReservationList.stream()
                .filter(r -> r.getDate().equals(date))
                .anyMatch(r -> !(timeBegin.isBefore(r.getTimeBegin())
                        && (timeEnd.isBefore(r.getTimeBegin()) || timeEnd.equals(r.getTimeBegin()))
                        || (timeBegin.isAfter(r.getTimeEnd()) || timeBegin.equals(r.getTimeEnd()))
                        && timeEnd.isAfter(r.getTimeEnd())));
    }
}

