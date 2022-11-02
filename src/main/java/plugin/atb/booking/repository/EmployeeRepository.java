package plugin.atb.booking.repository;

import plugin.atb.booking.entity.EmployeeEntity;
import plugin.atb.booking.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAll();
}
