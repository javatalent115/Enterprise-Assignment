package assignment.controller;

import assignment.entity.Customer;
import assignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        List<Customer> customerList = customerService.getAllCustomers();
//        for (Customer c : customerList) {
//            c.setOrderList(new ArrayList<>());
//        }
        return customerList;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String addCustomer(@RequestBody Customer customer) {
        Customer existedUsername = customerService.getCustomerByUsername(customer.getUsername());
        Customer existedEmail = customerService.getCustomerByEmail(customer.getEmail());
        if (existedUsername != null) {
            return "username";
        } else if (existedEmail != null) {
            return "email";
        } else {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aa");
            customer.setLastLogin(dateFormat.format(date));
            customerService.addCustomer(customer);
            return "";
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

