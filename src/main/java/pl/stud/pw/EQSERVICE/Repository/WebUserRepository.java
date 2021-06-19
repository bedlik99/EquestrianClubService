package pl.stud.pw.EQSERVICE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.stud.pw.EQSERVICE.Entity.WebUser;

@Repository
public interface WebUserRepository extends JpaRepository<WebUser,Long> {

    WebUser findWebUserByUsername(String username);
    WebUser findWebUserByEmail(String email);

}
