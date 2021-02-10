package pl.jbed.stud.SomeWebService.DAO.User;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jbed.stud.SomeWebService.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUserName(String username);

}