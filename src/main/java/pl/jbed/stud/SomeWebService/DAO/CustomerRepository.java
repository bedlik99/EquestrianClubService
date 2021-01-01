package pl.jbed.stud.SomeWebService.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jbed.stud.SomeWebService.Entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer findByUserName(String username);


}