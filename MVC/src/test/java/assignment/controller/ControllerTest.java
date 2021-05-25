package assignment.controller;

import assignment.entity.Customer;
import assignment.entity.Drug;
import assignment.entity.Producer;
import assignment.service.CustomerService;
import assignment.service.DrugService;
import assignment.service.ProducerService;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static assignment.TestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
@RunWith(SpringRunner.class)
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DrugService drugService;

    @MockBean
    private ProducerService producerService;

    @MockBean
    private CustomerService customerService;


    @Test
    public void authenticate() throws Exception {
        Customer customer1 = new Customer("c1","c1","c1","c1","c1","c1","c1");
        BDDMockito.given(customerService.getCustomerByUsername("c1")).willReturn(customer1);

        Map<String, String> admin = new HashMap<>();
        admin.put("username", "admin123");
        admin.put("password", "admin123");

        Map<String, String> validCustomer = new HashMap<>();
        validCustomer.put("username", "c1");
        validCustomer.put("password", "c1");

        Map<String, String> inValidCustomer = new HashMap<>();
        inValidCustomer.put("username", "user123");
        inValidCustomer.put("password", "user123");

        String adminStr = toJson(admin);
        String validCustomerStr = toJson(validCustomer);
        String invalidCustomerStr = toJson(inValidCustomer);

        MvcResult result1 = mvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON)
                                .content(adminStr))
                                .andExpect(status().isOk())
                                .andReturn();
        String respond1 = result1.getResponse().getContentAsString();

        assertEquals("admin", respond1);

        MvcResult result2 = mvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON)
                                .content(validCustomerStr))
                                .andExpect(status().isOk())
                                .andReturn();
        String respond2 = result2.getResponse().getContentAsString();

        assertEquals("user", respond2);

        MvcResult result3 = mvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON)
                                .content(invalidCustomerStr))
                                .andExpect(status().isOk())
                                .andReturn();
        String respond3 = result3.getResponse().getContentAsString();

        assertEquals("invalid", respond3);
    }

    @Test
    public void getProducerData() throws Exception {
        Producer producer = new Producer("p1", "p1");
        BDDMockito.given(producerService.getProducerById("p1")).willReturn(producer);
        Drug drug = new Drug("d1","d1","d1","d1","d1","d1","d1","d1",10,10,producer,"d1");
        BDDMockito.given(drugService.getDrugListByProducer("p1")).willReturn(Collections.singletonList("c1---c1"));

        Map<String, String> map = new HashMap<>();
        map.put("id", producer.getId());
        map.put("name", producer.getName());
        map.put("list", Arrays.toString(drugService.getDrugListByProducer(producer.getId()).toArray()));

        MvcResult result1 = mvc.perform(post("/getProducerData").contentType(MediaType.APPLICATION_JSON)
                                .content("p1"))
                                .andExpect(status().isOk())
                                .andReturn();
        String respond1 = result1.getResponse().getContentAsString();

        assertEquals(respond1, mapToString(map));
    }

    @Test
    public void setDatabase() {
    }
}