package pl.jbed.stud.SomeWebService.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbed.stud.SomeWebService.Entity.Customer;
import pl.jbed.stud.SomeWebService.Service.IService;

@Controller
@RequestMapping("/service")
public class RegisterController {

    private IService customerService;

    public RegisterController(IService theCustomerService) {
        this.customerService = theCustomerService;
    }

    @PostMapping("/save")
    public String saveCustomerData(@ModelAttribute("newCustomer") Customer theCustomer){

        customerService.save(theCustomer);

        return "redirect:/service/greeting";
    }
}
