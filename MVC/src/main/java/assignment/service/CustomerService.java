package assignment.service;

import assignment.entity.Customer;
import assignment.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {
    @Autowired
    CustomerRepo CustomerRepo;

    public void addCustomer(Customer order){
        this.CustomerRepo.save(order);
    }

    public List<Customer> getAllCustomers(){
        return this.CustomerRepo.findAll();
    }

    public void deleteByCustomerId(String id) {
        this.CustomerRepo.deleteById(id);
    }

    public Customer getCustomerById(String id) {
        Optional<Customer> result = this.CustomerRepo.findById(id);
        return result.orElse(null);
    }

    public void updateCustomerById(String id, Customer newCustomer) {
        getCustomerById(id).replace(newCustomer);
    }

    public long countCustomer() {
        return this.CustomerRepo.count();
    }

    public void deleteAllCustomer() {
        this.CustomerRepo.deleteAll();
    }
}
