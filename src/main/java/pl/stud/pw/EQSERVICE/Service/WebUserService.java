package pl.stud.pw.EQSERVICE.Service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.stud.pw.EQSERVICE.Entity.Employee;
import pl.stud.pw.EQSERVICE.Entity.Role;
import pl.stud.pw.EQSERVICE.Entity.WebUser;
import pl.stud.pw.EQSERVICE.Repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WebUserService implements UserDetailsService {

    private final WebUserRepository webUserRepository;
    private final RoleRepository roleRepository;
    private final WebUserExtraSQLRepository webUserExtraSQLRepository;
    private final EmployeeRepository employeeRepository;
    private final OfferReservationRepository offerReservationRepository;
    private final UserRolesRepository userRolesRepository;

    public WebUserService(WebUserRepository webUserRepository,
                          RoleRepository roleRepository,
                          WebUserExtraSQLRepository webUserExtraSQLRepository,
                          EmployeeRepository employeeRepository,
                          OfferReservationRepository offerReservationRepository,
                          UserRolesRepository userRolesRepository) {
        this.roleRepository = roleRepository;
        this.webUserRepository = webUserRepository;
        this.webUserExtraSQLRepository = webUserExtraSQLRepository;
        this.employeeRepository = employeeRepository;
        this.offerReservationRepository = offerReservationRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Transactional
    public void saveWebUser(WebUser theUser){

        if (theUser != null) {
            if (theUser.getRoles() == null || theUser.getRoles().isEmpty()) {
                theUser.setRoles(Arrays.asList(roleRepository.findRoleByRoleName("CLIENT")));
            }
            webUserRepository.save(theUser);
        }
    }

    public boolean isUsernameOrEmailDuplicated(WebUser theUser){
        return webUserRepository.findWebUserByUsername(theUser.getUsername()) != null
                || webUserRepository.findWebUserByEmail(theUser.getEmail()) != null;
    }

    public boolean isUpdateForFieldsAvailable(WebUser theUser){
        String loggedUsername = getLoggedUser().getUsername();
        String loggedEmail = getLoggedUser().getEmail();
        String modelUsername = theUser.getUsername();
        String modelEmail = theUser.getEmail();

        if(!loggedUsername.equals(modelUsername) && webUserRepository.findWebUserByUsername(modelUsername) != null){
            return false;
        }

        return loggedEmail.equals(modelEmail) || webUserRepository.findWebUserByEmail(modelEmail) == null;
    }

    public WebUser getUserById(Long theId) {
        Optional<WebUser> resultOfSearching = webUserRepository.findById(theId);
        WebUser searchedUser = null;

        if (resultOfSearching.isPresent()) {
            searchedUser = resultOfSearching.get();
        } else {
            throw new RuntimeException("Did not find a user with id: " + theId);
        }
        return searchedUser;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebUser user = webUserRepository.findWebUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    public WebUser findUserAndClearPassForm(Long theId) {
        WebUser theUser = getUserById(theId);
        if (theUser != null) {
            theUser.clearPassBeforePopulatingForm();
            return theUser;
        }
        return null; // oops
    }

    @Transactional
    public void updateWebUserData(WebUser theUser) {
        webUserExtraSQLRepository.updateUserInformation(theUser);
        refreshAuthenticationCredentials(theUser);
    }

    @Transactional
    public WebUser getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return webUserRepository.findWebUserByUsername(username);
    }

    @Transactional
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    @Transactional
    public List<WebUser> getWebSystemUsers(){
        return webUserRepository.findAll()
                .stream()
                .filter(this::isAdmin)
                .collect(Collectors.toList());
    }

    private boolean isAdmin(WebUser webUser){
        return webUser.getRoles()
                .stream()
                .noneMatch(role -> role.getRoleName().equals("ADMIN"));
    }

    private void refreshAuthenticationCredentials(WebUser user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    @Transactional
    public void deleteUser(WebUser webUser) {
        if (webUser != null) {
            offerReservationRepository.deleteAllByWebUser(webUser);
            if(webUser.getEmployee()!=null){
                Optional<Employee> employee = employeeRepository.findById(webUser.getEmployee().getId());
                if(employee.isPresent()){
                    offerReservationRepository.deleteAllByEmployee(employee.get());
                    employeeRepository.delete(employee.get());
                }
            }
            userRolesRepository.deleteUserRoles(webUser.getId());
            webUserRepository.delete(webUser);
        }
    }

}
