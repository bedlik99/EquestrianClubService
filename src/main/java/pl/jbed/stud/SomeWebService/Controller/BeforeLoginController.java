package pl.jbed.stud.SomeWebService.Controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jbed.stud.SomeWebService.Entity.User;
import pl.jbed.stud.SomeWebService.Entity.UserCode;
import pl.jbed.stud.SomeWebService.Service.UserService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * UWAGA SPRAWDZ CZY BAZA DANYCH BEZ KROTKI ENABLED BEDZIE DZIALAC --> BĘDZIE :) !
 */

@Controller
public class BeforeLoginController {

    private final static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
    private UserService userService;

    @Autowired
    public void setCustomerService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public String showReactPage() {

        if (isAuthenticated()) {
            return "redirect:/logged";
        } else {
            return "redirect:/greeting";
        }
    }

    @GetMapping("/greeting")
    public String frontPage() {

        if (isAuthenticated()) {
            return "redirect:/logged";
        }
        return "front-page";
    }

    @GetMapping("/register")
    public String registerForm(Model theModel) {
        if (isAuthenticated()) {
            return "redirect:/logged";
        }
        User user = new User();
        theModel.addAttribute("theUser", user);

        return "register-form";
    }

    @GetMapping("/login")
    public String loginForm() {
        if (isAuthenticated()) {
            return "redirect:/logged";
        }
        return "login-form";
    }

    @GetMapping("/logged")
    public String loggedIn(Model theModel) {

        User theUser = getLoggedUser();
        UserCode theCode = userService.findCodeById(theUser.getId());

        if(theCode.getInviteCode() == null){
            theCode.setInviteCode(RandomStringUtils.random(6, characters));
            userService.updateUserCode(theCode);
        }

        theModel.addAttribute("loggedUser", theUser);
        theModel.addAttribute("loggedUserCode", theCode);

        return "logged-form";
    }

    @GetMapping("/logged/details")
    public String showCustomerInfo() {

        if (isAuthenticated()) {
            return "redirect://localhost:3000";
        } else {
            return "redirect:/greeting";
        }

    }

    @GetMapping("/logged/update")
    public String updateData(@RequestParam("userId") int theId,
                             Model theModel) {

        User loggedUser = getLoggedUser();
        User theUser = userService.getUser(theId);

        if (loggedUser.getId() == theUser.getId()) {
            theUser.clearPassBeforePopulatingForm();
            // set employee as a model attribute to pre-populate the form
            theModel.addAttribute("theUser", theUser);
            return "update-form";
        } else {
            return "redirect:/logged";
        }

    }


    @PostMapping("/saveOrUpdate")
    public String saveCustomerData(@Valid @ModelAttribute("theUser") User theUser,
                                   BindingResult bindingResult) {
        if(isAuthenticated()){
            if (bindingResult.hasErrors()) {
                theUser.clearPassBeforePopulatingForm();
                return "update-form";
            } else {
                userService.saveUser(theUser);
                return "redirect:/logged/update?userId=" +theUser.getId();
            }
        }else{
            if (bindingResult.hasErrors()) {
                theUser.clearPassBeforePopulatingForm();
                return "register-form";
            } else {

                userService.saveUser(theUser);
                userService.addFieldForUserCode(theUser);
                return "redirect:/greeting";
            }
        }

    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int theId) {
        userService.deleteUserRole(theId);
        userService.deleteUser(theId);

        return "redirect:/greeting";
    }

    private User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userService.findByUserName(username);
    }


    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

}
