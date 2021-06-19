package pl.stud.pw.EQSERVICE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.stud.pw.EQSERVICE.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
