package pl.stud.pw.EQSERVICE.Entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class WebUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Size(min = 4,message = " length must be at least 4 symbols")
    @NotBlank(message = " is required")
    private String username;

    @Size(min = 6,message = " length must be at least 6 symbols")
    @NotBlank(message = " is required")
    private String password;

    @NotNull
    @NotBlank(message = " is required")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "webUser")
    private List<OfferReservation> offers;

    public WebUser(){}

    public WebUser(String firstName, String lastName,
                   @Size(min = 4, message = " length must be at least 4 symbols")
                   @NotBlank(message = " can not be blank") String username,
                   @Size(min = 6, message = " length must be at least 6 symbols")
                   @NotBlank(message = " can not be blank") String password,
                   String email, List<Role> roles, Employee employee, List<OfferReservation> offers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.employee = employee;
        this.offers = offers;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OfferReservation> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferReservation> offers) {
        this.offers = offers;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.isBlank() || password.length() < 6){
            this.password="";
        }else{
            this.password = new BCryptPasswordEncoder().encode(password.trim());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void clearPassBeforePopulatingForm() {
        password="";
    }
    public void setNonBlankPassAfterError(){
        password="Password";
    }

    public void fillFieldsWithLoggedUserDetails(WebUser theUser) {
        password="Password";
        username = theUser.getUsername();
        email = theUser.getEmail();
    }

    @Override
    public String toString() {
        return "WebUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", employee=" + employee +
                ", offers=" + offers +
                '}';
    }

}
