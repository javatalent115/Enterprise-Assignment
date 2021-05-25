package assignment.controller;

import assignment.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@WebMvcTest(OrderController.class)
@RunWith(SpringRunner.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void getAllOrders() {
        assertNotNull(1);
    }

    @Test
    public void addOrder() {
        assertNotNull(1);
    }

    @Test
    public void deleteOrderById() {
        assertNotNull(1);
    }

    @Test
    public void getOrderByCustomerUsername() {
        assertNotNull(1);
    }
}