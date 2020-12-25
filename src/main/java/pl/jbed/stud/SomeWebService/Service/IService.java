package pl.jbed.stud.SomeWebService.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.jbed.stud.SomeWebService.Entity.Customer;

import java.util.List;

public interface IService extends UserDetailsService {

    public Customer findByUserName(String userName);
    public Customer getCustomer(int theId);
    public List<Customer> getAllCustomers();
    public void deleteCustomer(int theId);
    public void save(Customer customer);
}
