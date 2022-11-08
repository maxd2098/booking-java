package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plugin.atb.booking.model.QueueReservation;

@Repository
public interface QueueReservationRepository extends CrudRepository<QueueReservation, Long> {
}
