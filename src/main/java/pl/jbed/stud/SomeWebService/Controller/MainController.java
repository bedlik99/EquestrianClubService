package pl.jbed.stud.SomeWebService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.jbed.stud.SomeWebService.Entity.Customer;
import pl.jbed.stud.SomeWebService.Service.CustomerService;

import javax.validation.Valid;

/**
 * UWAGA SPRAWDZ CZY BAZA DANYCH BEZ KROTKI ENABLED BEDZIE DZIALAC --> BĘDZIE :) !
 */
@Controller
@RequestMapping("/service")
public class MainController {

    private CustomerService customerService;


    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/greeting")
    public String frontPage(){

        if(isAuthenticated()){
            return "redirect:/service/logged";
        }

        return "front-page";
    }

    @GetMapping("/register")
    public String registerForm(Model theModel){
        if(isAuthenticated()){
            return "redirect:/service/logged";
        }

        Customer customer = new Customer();
        theModel.addAttribute("theCustomer", customer);

        return "register-form";
    }


    @GetMapping("/login")
    public String loginForm(){
        if(isAuthenticated()){
            return "redirect:/service/logged";
        }

        return "login-form";
    }

    @PostMapping("/save")
    public String saveCustomerData(@Valid @ModelAttribute("theCustomer") Customer theCustomer,
                                   BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            theCustomer.clearPassBeforePopulatingForm();

            return "register-form";

        }else{
            customerService.save(theCustomer);

            return "redirect:/service/greeting";
        }


    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int theId){

        customerService.deleteCustomer(theId);

        return "redirect:/service/greeting";
    }


    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
