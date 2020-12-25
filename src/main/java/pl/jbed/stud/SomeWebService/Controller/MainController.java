package pl.jbed.stud.SomeWebService.Controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jbed.stud.SomeWebService.Entity.Customer;

/**
 * UWAGA SPRAWDZ CZY BAZA DANYCH BEZ KROTKI ENABLED BEDZIE DZIALAC
 */
@Controller
@RequestMapping("/service")
public class MainController {

    @GetMapping("/greeting")
    public String frontPage(){

        return "front-page";
    }

    @GetMapping("/register")
    public String registerForm(Model theModel){

        Customer customer = new Customer();
        theModel.addAttribute("theCustomer", customer);

        return "register-form";
    }

    @GetMapping("/login")
    public String loginForm(){

        return "login-form";
    }


}
