package assignment.service;

import assignment.entity.Customer;
import assignment.entity.Order;
import assignment.entity.Producer;
import assignment.repository.OrderRepo;
import assignment.repository.ProducerRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest(classes = {OrderService.class})
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @MockBean
    OrderRepo orderRepo;

    @Autowired
    OrderService orderService;

    @Before
    public void init() {
        Order order1 = new Order("O1", new Customer("a", "a", "a", "a", "a", "a", "a"), "today", "COD", 10);
        Order order2 = new Order("O2", new Customer("b", "b", "b", "b", "b", "b", "b"), "yesterday", "COD", 10);
        Order order3 = new Order("O3", new Customer("a", "a", "a", "a", "a", "a", "a"), "today", "COD", 10);
        List<Order> orders = Arrays.asList(order1, order2, order3);

        Mockito.when(orderRepo.save(Mockito.any(Order.class))).thenReturn(new Order());

        Mockito.when(orderRepo.findAll()).thenReturn(orders);

        Mockito.when(orderRepo.findById("01")).thenReturn(Optional.of(order1));

        Mockito.when(orderRepo.count()).thenReturn((long) orders.size());
    }

    @Test
    public void getAllOrders() {
        Order order1 = new Order("O1", new Customer("a", "a", "a", "a", "a", "a", "a"), "today", "COD", 10);
        Order order2 = new Order("O2", new Customer("b", "b", "b", "b", "b", "b", "b"), "yesterday", "COD", 10);
        Order order3 = new Order("O3", new Customer("a", "a", "a", "a", "a", "a", "a"), "today", "COD", 10);

        List<Order> orders = Arrays.asList(order1, order2, order3);
        for (int i = 0; i < orders.size(); i++) {
            Order found =  orderService.getAllOrders().get(i);
            assertEquals(orders.get(i).getId(), found.getId());

            assertEquals(orders.get(i).getPurchaseTime(), found.getPurchaseTime());
            assertEquals(orders.get(i).getPurchaseType(), found.getPurchaseType());
            assertEquals(orders.get(i).getTotal(), found.getTotal());
        }
    }

    @Test
    public void getOrderById() {
        Order order1 = new Order("O1", new Customer("a", "a", "a", "a", "a", "a", "a"), "today", "COD", 10);
        Order found =  orderService.getOrderById("01");
        assertNotNull(found);

        assertEquals(order1.getId(), found.getId());

        assertEquals(order1.getPurchaseTime(), found.getPurchaseTime());
        assertEquals(order1.getPurchaseType(), found.getPurchaseType());
        assertEquals(order1.getTotal(), found.getTotal());
    }

    @Test
    public void getOrderByCustomerUsername() {
        Order order1 = new Order("O1", new Customer("a", "a", "a", "a", "a", "a", "a"), "today", "COD", 10);
        Order order3 = new Order("O3", new Customer("a", "a", "a", "a", "a", "a", "a"), "today", "COD", 10);

        List<Order> orders = Arrays.asList(order1, order3);
        List<Order> orderList = orderService.getOrderByCustomerUsername("a");

        for (int i = 0; i < orders.size(); i++) {
            Order found =  orderList.get(i);
            assertEquals(orders.get(i).getId(), found.getId());

            assertEquals(orders.get(i).getPurchaseTime(), found.getPurchaseTime());
            assertEquals(orders.get(i).getPurchaseType(), found.getPurchaseType());
            assertEquals(orders.get(i).getTotal(), found.getTotal());
        }
    }

    @Test
    public void countOrder() {
        assertEquals(3, orderService.countOrder());
    }

    @Test
    public void addOrder() {
        assertNotNull(orderRepo.save(new Order()));
    }

    @Test
    public void deleteByOrderId() {
    }

    @Test
    public void deleteAll() {
    }
}