package assignment.service;

import assignment.entity.Customer;
import assignment.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public void addCustomer(Customer order){
        this.customerRepo.save(order);
    }

    public List<Customer> getAllCustomers(){
        List<Customer> list = this.customerRepo.findAll();
        list.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer customer, Customer customer1)  {
                return customer.getUsername().toLowerCase().compareTo(customer1.getUsername().toLowerCase());
            }
        });
        return list;
    }

    public Customer getCustomerByUsername(String username) {
        Optional<Customer> result = this.customerRepo.findById(username);
        return result.orElse(null);
    }

    public Customer getCustomerByEmail(String email) {
        List<Customer> customerList = getAllCustomers();
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> sortByLastLogin(){
        List<Customer> list = getAllCustomers();
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        list.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer customer, Customer customer1)  {
                try {
                    return formatter.parse(customer.getLastLogin()).compareTo(formatter.parse(customer1.getLastLogin()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return list;
    }

    public void save(Customer customer) {
        customerRepo.save(customer);
    }
}
