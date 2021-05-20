package assignment.controller;

import assignment.entity.Customer;
import assignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String addCustomer(@RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
            return "succeeded";
        } catch (Exception e) {
            return "failed";
        }
    }

//    @RequestMapping(path = "", method = RequestMethod.DELETE)
//    public void deleteAllCustomers(){
//        customerService.deleteAllCustomer();
//    }

    @RequestMapping(path = "/deleteCustomer", method = RequestMethod.DELETE)
    public void deleteCustomerByUsername(@RequestBody String username) {
        customerService.deleteByCustomerUsername(username.trim());
    }

    @RequestMapping(path = "/updateCustomer", method = RequestMethod.PUT)
    public void updateCustomerByUsername(@RequestBody Customer customer) {
        customerService.updateCustomerByUsername(customer.getUsername(), customer);
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String username) {
        return customerService.getCustomerByUsername(username.trim());
    }
}

