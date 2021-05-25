package assignment.controller;

import assignment.entity.Customer;
import assignment.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static assignment.TestHelper.*;

import static org.junit.Assert.*;

@WebMvcTest(CustomerController.class)
@RunWith(SpringRunner.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void addCustomer() {
    }

    @Test
    public void getCustomerById() throws Exception {
        Customer customer1 = new Customer("c1","c1","c1","c1","c1","c1","c1");
        BDDMockito.given(customerService.getCustomerByUsername("c1")).willReturn(customer1);
        MvcResult result1 = mvc.perform(get("/customer/{username}", "c1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String respond1 = result1.getResponse().getContentAsString();
        assertEquals(mapToString(objectToMap(customer1)), respond1);
    }

    @Test
    public void getAllCustomers() {
    }
}