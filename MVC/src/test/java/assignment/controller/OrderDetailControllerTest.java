package assignment.controller;

import assignment.service.OrderDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@WebMvcTest(OrderDetailController.class)
@RunWith(SpringRunner.class)
public class OrderDetailControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderDetailService orderDetailService;

    @Test
    public void getAllOrders() {
        assertNotNull(1);
    }

    @Test
    public void addOrderDetail() {
        assertNotNull(1);
    }

    @Test
    public void getOrderDetailByOrderId() {
        assertNotNull(1);
    }

    @Test
    public void deleteOrderById() {
        assertNotNull(1);
    }
}