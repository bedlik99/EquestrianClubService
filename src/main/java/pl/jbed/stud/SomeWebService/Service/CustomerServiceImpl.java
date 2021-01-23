package pl.jbed.stud.SomeWebService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jbed.stud.SomeWebService.DAO.CustomerRepository;
import pl.jbed.stud.SomeWebService.DAO.RoleRepo;
import pl.jbed.stud.SomeWebService.Entity.Customer;
import pl.jbed.stud.SomeWebService.Entity.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private RoleRepo roleRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, RoleRepo roleRepository) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Customer findByUserName(String userName) {
        // check the database if the user already exists
        return customerRepository.findByUserName(userName);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findByUserName(username);
        if (customer == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new org.springframework.security.core.userdetails.User(customer.getUserName(), customer.getPassword(),
                mapRolesToAuthorities(customer.getRoles()));
    }


    // CRUD OPERATIONS --> @Transactional musi byc uzyta z powodu wywolania funkcji nie-crudowej
    // .findRoleByName w ktorej nie otwieram sesji. Otwarcie/zamkniecie sesji zostaje obsluzone na poziomie warsty serwisowej
    @Override
    @Transactional
    public void save(Customer customer) {

        if(customer != null){

            if(customer.getRoles() == null){
                customer.setRoles(Arrays.asList(roleRepository.findRoleByName("CUSTOMER")));
            }

                customerRepository.save(customer);
        }

    }


    @Override
    public Customer getCustomer(int theId) {
        Optional<Customer> resultOfSearching = customerRepository.findById(theId);
        Customer searchedCustomer = null;
        if (resultOfSearching.isPresent()) {
            searchedCustomer = resultOfSearching.get();
        } else {
            throw new RuntimeException("Did not find a customer with id: " + theId);
        }

        return searchedCustomer;
    }

    @Override
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }


    @Override
    public void deleteCustomer(int theId) {
        Optional<Customer> resultOfSearching = customerRepository.findById(theId);

        if (resultOfSearching.isPresent()) {
            System.out.println("No jest element");
            customerRepository.deleteById(theId);
        } else {
            throw new RuntimeException("No nie ma! --> Did not find a customer with id: " + theId);
        }

    }


}

