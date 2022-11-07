package plugin.atb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plugin.atb.booking.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAll();
}
