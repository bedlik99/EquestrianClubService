package pl.jbed.stud.SomeWebService.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jbed.stud.SomeWebService.Entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {


}
