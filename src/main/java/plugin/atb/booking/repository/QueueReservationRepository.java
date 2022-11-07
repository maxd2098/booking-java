package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import plugin.atb.booking.model.QueueReservation;

public interface QueueReservationRepository extends CrudRepository<QueueReservation, Long> {
}
