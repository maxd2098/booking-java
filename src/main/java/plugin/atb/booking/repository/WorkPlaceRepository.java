package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plugin.atb.booking.model.WorkPlace;

import java.util.List;

@Repository
public interface WorkPlaceRepository extends CrudRepository<WorkPlace, Long> {
    List<WorkPlace> findAll();
}
