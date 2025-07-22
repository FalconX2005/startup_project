package uz.pdp.startupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.startupproject.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}