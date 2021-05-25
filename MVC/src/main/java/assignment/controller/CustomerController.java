package assignment.controller;

import assignment.entity.Customer;
import assignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "/getAll", method = RequestMethod.POST)
    public List<Customer> getAllCustomers(@RequestBody String sortType){
        if (sortType.equals("Name (A-Z)")) return customerService.getAllCustomers();
        else if (sortType.equals("Last Login (newest)")) return customerService.sortByLastLogin();
        else if (sortType.equals("Last Login (oldest)")) return sortByLastLoginDes();
        else return sortByIdDes();
    }

    private List<Customer> sortByIdDes(){
        List<Customer> list = customerService.getAllCustomers();
        Collections.reverse(list);
        return list;
    }


    private List<Customer> sortByLastLoginDes(){
        List<Customer> list = customerService.sortByLastLogin();
        Collections.reverse(list);
        return list;
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

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String username) {
        return customerService.getCustomerByUsername(username.trim());
    }
}

