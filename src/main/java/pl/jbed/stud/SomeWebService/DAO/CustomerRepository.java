package pl.jbed.stud.SomeWebService.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jbed.stud.SomeWebService.Entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer findByUsername(String username);

}