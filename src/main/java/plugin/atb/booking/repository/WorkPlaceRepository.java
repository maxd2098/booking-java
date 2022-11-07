package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plugin.atb.booking.entity.WorkPlaceEntity;

import java.util.List;

@Repository
public interface WorkPlaceRepository extends CrudRepository<WorkPlaceEntity, Long> {
    List<WorkPlaceEntity> findAll();
}
