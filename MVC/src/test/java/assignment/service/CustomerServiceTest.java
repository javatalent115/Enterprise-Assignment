package assignment.service;

import assignment.entity.Customer;
import assignment.entity.Customer;
import assignment.repository.CustomerRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest(classes = {CustomerService.class})
@RunWith(SpringRunner.class)
public class CustomerServiceTest {
    @MockBean
    CustomerRepo customerRepo;

    @Autowired
    CustomerService customerService;

    @Before
    public void init() {
        Customer customer1 = new Customer("c1","c1","c1","c1","c1","c1","c1");
        Customer customer2 = new Customer("c2","c2","c2","c2","c2","c2","c2");
        Customer customer3 = new Customer("c3","c3","c3","c3","c3","c3","c3");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        Mockito.when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(new Customer());

        Mockito.when(customerRepo.findAll()).thenReturn(customers);

        Mockito.when(customerRepo.findById("c1")).thenReturn(Optional.of(customer1));
    }

    @Test
    public void addCustomer() {
        assertNotNull(customerRepo.save(new Customer()));
    }

    @Test
    public void getAllCustomers() {
        Customer customer1 = new Customer("c1","c1","c1","c1","c1","c1","c1");
        Customer customer2 = new Customer("c2","c2","c2","c2","c2","c2","c2");
        Customer customer3 = new Customer("c3","c3","c3","c3","c3","c3","c3");
        List<Customer> mock = Arrays.asList(customer1, customer2, customer3);
        List<Customer> customers = customerService.getAllCustomers();

        assertEquals(mock.size(), customers.size());

        for (int i = 0; i < mock.size(); i++) {
            assertEquals(mock.get(i).getUsername(), customers.get(i).getUsername());
            assertEquals(mock.get(i).getPassword(), customers.get(i).getPassword());
            assertEquals(mock.get(i).getFirstname(), customers.get(i).getFirstname());
            assertEquals(mock.get(i).getLastname(), customers.get(i).getLastname());
            assertEquals(mock.get(i).getEmail(), customers.get(i).getEmail());
            assertEquals(mock.get(i).getAddress(), customers.get(i).getAddress());
            assertEquals(mock.get(i).getLastLogin(), customers.get(i).getLastLogin());
        }
    }

    @Test
    public void getCustomerByUsername() {
        Customer customer1 = new Customer("c1","c1","c1","c1","c1","c1","c1");
        Customer found =  customerService.getCustomerByUsername("c1");
        Customer notfound =  customerService.getCustomerByUsername("c4");

        assertNotNull(found);
        assertNull(notfound);

        assertEquals(customer1.getUsername(), found.getUsername());
        assertEquals(customer1.getPassword(), found.getPassword());
        assertEquals(customer1.getFirstname(), found.getFirstname());
        assertEquals(customer1.getLastname(), found.getLastname());
        assertEquals(customer1.getEmail(), found.getEmail());
        assertEquals(customer1.getAddress(), found.getAddress());
        assertEquals(customer1.getLastLogin(), found.getLastLogin());
    }

    @Test
    public void getCustomerByEmail() {
        Customer customer1 = new Customer("c1","c1","c1","c1","c1","c1","c1");
        Customer found = customerService.getCustomerByEmail("c1");
        Customer notfound = customerService.getCustomerByEmail("c4");

        assertNotNull(found);
        assertNull(notfound);

        assertEquals(customer1.getUsername(), found.getUsername());
        assertEquals(customer1.getPassword(), found.getPassword());
        assertEquals(customer1.getFirstname(), found.getFirstname());
        assertEquals(customer1.getLastname(), found.getLastname());
        assertEquals(customer1.getEmail(), found.getEmail());
        assertEquals(customer1.getAddress(), found.getAddress());
        assertEquals(customer1.getLastLogin(), found.getLastLogin());

    }

    @Test
    public void sortByLastLogin() {
    }

    @Test
    public void save() {
    }
}