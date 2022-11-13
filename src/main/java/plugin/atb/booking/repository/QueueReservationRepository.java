package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plugin.atb.booking.model.QueueReservation;

import java.util.List;

@Repository
public interface QueueReservationRepository extends CrudRepository<QueueReservation, Long> {

    QueueReservation findByIdQueueReservation(Long idQueueReservation);

    List<QueueReservation> findAllByIdEmployee(Long idEmployee);

    List<QueueReservation> findAllByIdWorkPlace(Long idWorkPlace);
}
