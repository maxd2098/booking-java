package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plugin.atb.booking.model.WorkPlace;

@Repository
public interface WorkPlaceRepository extends CrudRepository<WorkPlace, Long> {
    WorkPlace findByIdWorkPlace(Long idWorkPlace);
}
