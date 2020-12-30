package pl.jbed.stud.SomeWebService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jbed.stud.SomeWebService.Entity.Customer;
import pl.jbed.stud.SomeWebService.Service.IService;

/**
 * UWAGA SPRAWDZ CZY BAZA DANYCH BEZ KROTKI ENABLED BEDZIE DZIALAC --> BĘDZIE :) !
 */
@Controller
@RequestMapping("/service")
public class MainController {

    @Autowired
    private IService customerService;

    public MainController(IService theCustomerService) {
        this.customerService = theCustomerService;
    }


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

    @PostMapping("/save")
    public String saveCustomerData(@ModelAttribute("newCustomer") Customer theCustomer){

        customerService.save(theCustomer);

        return "redirect:/service/greeting";
    }


    @GetMapping("/loggedIn/showDetails")
    public String showCustomerInfo(){

        return "customer-form";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int theId){

        customerService.deleteCustomer(theId);

        return "redirect:/service/greeting";
    }


}
