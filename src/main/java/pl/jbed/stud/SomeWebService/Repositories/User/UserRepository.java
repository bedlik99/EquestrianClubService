package pl.jbed.stud.SomeWebService.Repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jbed.stud.SomeWebService.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUserName(String username);

}