package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plugin.atb.booking.entity.EmployeeEntity;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAll();
}
