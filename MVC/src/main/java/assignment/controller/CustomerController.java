package assignment.controller;

import assignment.entity.Customer;
import assignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void deleteAllCustomers(){
        customerService.deleteAllCustomer();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteCustomerById(@PathVariable String id) {
        customerService.deleteByCustomerId(id);
    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public void updateCustomerById(@PathVariable String id, @RequestBody Customer customer) {
//        customerService.updateCustomerById(id, customer);
//    }
}

