package assignment.service;

import assignment.entity.Customer;
import assignment.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    Comparator<Customer> customerId = Comparator.comparing(Customer::getUsername); //Sort by Id

    public void addCustomer(Customer order){
        this.customerRepo.save(order);
    }

    public List<Customer> getAllCustomers(){
        return this.customerRepo.findAll();
    }

    public void deleteByCustomerUsername(String username) {
        this.customerRepo.deleteById(username);
    }

    public Customer getCustomerByUsername(String username) {
        Optional<Customer> result = this.customerRepo.findById(username);
        return result.orElse(null);
    }

    public void updateCustomerByUsername(String username, Customer newCustomer) {
        Customer oldCustomer = getCustomerByUsername(username);
        newCustomer.setTransactionList(oldCustomer.getTransactionList());
        oldCustomer = newCustomer;
        customerRepo.save(oldCustomer);
    }

    public long countCustomer() {
        return this.customerRepo.count();
    }

    public void deleteAllCustomer() {
        this.customerRepo.deleteAll();
    }
}
