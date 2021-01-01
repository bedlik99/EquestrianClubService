package pl.jbed.stud.SomeWebService.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.jbed.stud.SomeWebService.Entity.Customer;
import pl.jbed.stud.SomeWebService.Entity.Role;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends UserDetailsService {

    public Customer findByUserName(String userName);
    public Customer getCustomer(int theId);
    public List<Customer> getAllCustomers();
    public void deleteCustomer(int theId);
    public void save(Customer customer);
}
