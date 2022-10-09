package pl.stud.pw.EQSERVICE.DTO;

import pl.stud.pw.EQSERVICE.Entity.WebUser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WebUserDTO {

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

    public WebUserDTO() {
    }

    public WebUserDTO(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static WebUserDTO createWebUserDTO(WebUser webUser) {
        if (webUser == null) return null;
        return new WebUserDTO(webUser.getFirstName(), webUser.getLastName(),
                webUser.getUsername(), webUser.getPassword(), webUser.getEmail());
    }

    public void clearPassBeforePopulatingForm() {
        password = "";
    }

    public void fillFieldsWithLoggedUserDetails(WebUser theUser) {
        password = "Password";
        username = theUser.getUsername();
        email = theUser.getEmail();
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
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
