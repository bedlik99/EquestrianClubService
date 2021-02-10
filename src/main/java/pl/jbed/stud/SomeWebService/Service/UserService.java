package pl.jbed.stud.SomeWebService.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.jbed.stud.SomeWebService.Entity.User;
import pl.jbed.stud.SomeWebService.Entity.UserCode;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);
    public UserCode findCodeById(int id);
    public User getUser(int theId);
    public List<User> getAllUsers();
    public void deleteUser(int theId);
    public void deleteUserRole(int theId);
    public void saveUser(User user);
    public void addFieldForUserCode();
    public void updateUserCode(UserCode userCode);
}
