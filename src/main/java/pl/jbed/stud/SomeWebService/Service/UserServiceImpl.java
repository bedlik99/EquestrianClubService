package pl.jbed.stud.SomeWebService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jbed.stud.SomeWebService.DAO.Role.RoleRepo;
import pl.jbed.stud.SomeWebService.DAO.User.UserRepository;
import pl.jbed.stud.SomeWebService.DAO.UserCode.UserCodeRepository;
import pl.jbed.stud.SomeWebService.DAO.UserRole.UserRoleRepo;
import pl.jbed.stud.SomeWebService.Entity.User;
import pl.jbed.stud.SomeWebService.Entity.Role;
import pl.jbed.stud.SomeWebService.Entity.UserCode;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepo roleRepository;
    private final UserCodeRepository userCodeRepo;
    private final UserRoleRepo userRoleRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepo roleRepository,
                           UserCodeRepository userCodeRepo, UserRoleRepo userRoleRepo) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userCodeRepo = userCodeRepo;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public User findByUserName(String userName) {
        // check the database if the user already exists
        return userRepository.findByUserName(userName);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }


    // CRUD OPERATIONS --> @Transactional musi byc uzyta z powodu wywolania funkcji nie-crudowej
    // .findRoleByName w ktorej nie otwieram sesji. Otwarcie/zamkniecie sesji zostaje obsluzone na poziomie warsty serwisowej
    @Override
    @Transactional
    public void saveUser(User user) {
        if(user != null){

            if(user.getRoles() == null){
                user.setRoles(Arrays.asList(roleRepository.findRoleByName("CUSTOMER")));
            }
            userRepository.save(user);

        }

    }


    @Override
    public User getUser(int theId) {
        Optional<User> resultOfSearching = userRepository.findById(theId);
        User searchedUser = null;
        if (resultOfSearching.isPresent()) {
            searchedUser = resultOfSearching.get();
        } else {
            throw new RuntimeException("Did not find a customer with id: " + theId);
        }

        return searchedUser;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUserRole(int theId) {
        userRoleRepo.deleteUserRole(theId);
    }

    @Override
    public void deleteUser(int theId) {
        Optional<User> resultOfSearching = userRepository.findById(theId);

        if (resultOfSearching.isPresent()) {
            System.out.println("Id of deleted user: " + theId);
            userRepository.deleteById(theId);
        } else {
            throw new RuntimeException("Did not find a customer with id: " + theId);
        }
    }

    @Override
    @Transactional
    public UserCode findCodeById(int id) {
       return userCodeRepo.findById(id);
    }

    @Override
    @Transactional
    public void addFieldForUserCode() {
        userCodeRepo.addFieldForUserCode();
    }

    @Override
    @Transactional
    public void updateUserCode(UserCode userCode) {
        userCodeRepo.updateUserCode(userCode);
    }
}

