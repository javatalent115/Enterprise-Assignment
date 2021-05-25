package assignment.service;

import assignment.entity.Drug;
import assignment.entity.Order;
import assignment.entity.OrderDetail;
import assignment.repository.OrderDetailRepo;
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

@SpringBootTest(classes = {OrderDetailService.class})
@RunWith(SpringRunner.class)
public class OrderDetailServiceTest {
    @MockBean
    OrderDetailRepo orderDetailRepo;

    @Autowired
    OrderDetailService orderDetailService;

    @Before
    public void init() {
        Order order1 = new Order("o1", null, "o1", "o1", 10);
        Order order2 = new Order("o2", null, "o2", "o2", 20);

        OrderDetail orderDetail1 = new OrderDetail("od1", order1, new Drug(), 2, 40);
        OrderDetail orderDetail2 = new OrderDetail("od2", order2, new Drug(), 3, 90);
        OrderDetail orderDetail3 = new OrderDetail("od3", order2, new Drug(), 5, 90);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail1);
        orderDetails.add(orderDetail2);
        orderDetails.add(orderDetail3);

        Mockito.when(orderDetailRepo.save(Mockito.any(OrderDetail.class))).thenReturn(new OrderDetail());

        Mockito.when(orderDetailRepo.findAll()).thenReturn(orderDetails);

        Mockito.when(orderDetailRepo.findById("od1")).thenReturn(Optional.of(orderDetail1));

        Mockito.when(orderDetailRepo.count()).thenReturn((long) orderDetails.size());
    }


    @Test
    public void addOrderDetail() {
        assertNotNull(orderDetailRepo.save(new OrderDetail()));
    }

    @Test
    public void getAllOrderDetails() {
        Order order1 = new Order("o1", null, "o1", "o1", 10);
        Order order2 = new Order("o2", null, "o2", "o2", 20);

        OrderDetail orderDetail1 = new OrderDetail("od1", order1, new Drug(), 2, 40);
        OrderDetail orderDetail2 = new OrderDetail("od2", order2, new Drug(), 3, 90);
        OrderDetail orderDetail3 = new OrderDetail("od3", order2, new Drug(), 5, 90);
        List<OrderDetail> mock = Arrays.asList(orderDetail1, orderDetail2, orderDetail3);
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();

        assertEquals(mock.size(), orderDetails.size());

        for (int i = 0; i < mock.size(); i++) {
            assertEquals(mock.get(i).getId(), orderDetails.get(i).getId());
            assertEquals(mock.get(i).getOrder().getId(), orderDetails.get(i).getOrder().getId());
            assertEquals(mock.get(i).getDrug().getId(), orderDetails.get(i).getDrug().getId());
            assertEquals(mock.get(i).getQuantity(), orderDetails.get(i).getQuantity());
            assertEquals(mock.get(i).getCost(), orderDetails.get(i).getCost());
        }
    }

    @Test
    public void deleteByOrderId() {
    }

    @Test
    public void getOrderDetailById() {
        Order order1 = new Order("o1", null, "o1", "o1", 10);
        OrderDetail orderDetail1 = new OrderDetail("od1", order1, new Drug(), 2, 40);
        OrderDetail found =  orderDetailService.getOrderDetailById("od1");
        OrderDetail notfound =  orderDetailService.getOrderDetailById("od3");

        assertNotNull(found);
        assertNull(notfound);

        assertEquals(orderDetail1.getId(), found.getId());
        assertEquals(orderDetail1.getOrder().getId(), found.getOrder().getId());
        assertEquals(orderDetail1.getDrug().getId(), found.getDrug().getId());
        assertEquals(orderDetail1.getQuantity(), found.getQuantity());
        assertEquals(orderDetail1.getCost(), found.getCost());
    }

    @Test
    public void getOrderDetailByOrderId() {
        Order order1 = new Order("o1", null, "o1", "o1", 10);
        Order order2 = new Order("o2", null, "o2", "o2", 20);

        OrderDetail orderDetail1 = new OrderDetail("od1", order1, new Drug(), 2, 40);
        OrderDetail orderDetail2 = new OrderDetail("od2", order2, new Drug(), 3, 90);
        OrderDetail orderDetail3 = new OrderDetail("od3", order2, new Drug(), 5, 90);

        List<OrderDetail> mock = Arrays.asList(orderDetail2, orderDetail3);
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId("o2");

        assertEquals(mock.size(), orderDetails.size());
        assertFalse(orderDetails.stream().anyMatch(od -> od.getId().equals(orderDetail1.getId())));
        assertFalse(orderDetails.stream().anyMatch(od -> od.getOrder().getId().equals(orderDetail1.getOrder().getId())));

        for (int i = 0; i < mock.size(); i++) {
            assertEquals(mock.get(i).getId(), orderDetails.get(i).getId());
            assertEquals(mock.get(i).getOrder().getId(), orderDetails.get(i).getOrder().getId());
            assertEquals(mock.get(i).getDrug().getId(), orderDetails.get(i).getDrug().getId());
            assertEquals(mock.get(i).getQuantity(), orderDetails.get(i).getQuantity());
            assertEquals(mock.get(i).getCost(), orderDetails.get(i).getCost());
        }
    }

    @Test
    public void countOrderDetail() {
        assertEquals(3, orderDetailService.countOrderDetail());
    }

    @Test
    public void deleteAllOrderDetail() {
    }
}