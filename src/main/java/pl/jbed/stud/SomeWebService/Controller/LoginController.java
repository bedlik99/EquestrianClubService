package pl.jbed.stud.SomeWebService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jbed.stud.SomeWebService.Entity.Customer;
import pl.jbed.stud.SomeWebService.Service.CustomerService;

@Controller
@RequestMapping("/service")
public class LoginController {

    @Autowired
    private CustomerService service;

    public LoginController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/logged/showDetails")
    public String showCustomerInfo(){

        return "customer-form";
    }

    @GetMapping("/logged")
    public String loggedIn(Model theModel){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof UserDetails){
             username = ((UserDetails)principal).getUsername();
        }else{
             username = principal.toString();
        }

        Customer theCustomer = service.findByUserName(username);
        theModel.addAttribute("loggedCustomer",theCustomer);

        return "logged-form";
    }



    @GetMapping("/logged/update")
    public String updateData(@RequestParam("customerId") int theId,
                             Model theModel){

        // get the employee from the service
        Customer theCustomer = service.getCustomer(theId);

        theCustomer.clearPassBeforePopulatingForm();
        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("theCustomer",theCustomer);

        return "update-form";
    }



}
