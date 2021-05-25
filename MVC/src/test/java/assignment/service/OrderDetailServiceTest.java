//package assignment.service;
//
//import assignment.entity.Customer;
//import assignment.entity.Order;
//import assignment.entity.OrderDetail;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//@SpringBootTest(classes = {OrderDetailService.class})
//public class OrderDetailServiceTest {
//    @Before
//    public void init() {
//        Order order1 = new Order("o1", null, "o1", "o1", 10);
//        Order order2 = new Order("o2", null, "o2", "o2", 20);
//
//        List<Order> orders = Arrays.asList(order1, order2, order3);
//
//        Mockito.when(orderRepo.save(Mockito.any(Order.class))).thenReturn(new Order());
//
//        Mockito.when(orderRepo.findAll()).thenReturn(orders);
//
//        Mockito.when(orderRepo.findById("01")).thenReturn(Optional.of(order1));
//
//        Mockito.when(orderRepo.count()).thenReturn((long) orders.size());
//    }
//}