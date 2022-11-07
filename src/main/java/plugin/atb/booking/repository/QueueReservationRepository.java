package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import plugin.atb.booking.entity.QueueReservationEntity;

public interface QueueReservationRepository extends CrudRepository<QueueReservationEntity, Long> {
}
