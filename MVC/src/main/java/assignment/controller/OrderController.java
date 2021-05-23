package assignment.controller;

import assignment.entity.Order;
import assignment.entity.OrderDetail;
import assignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Order> getAllOrders(){
        List<Order> orderList = orderService.getAllOrders();
//        for (Order order : orderList) {
//            order.setOrderDetailList(new ArrayList<>());
//        }
        return orderList;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addOrder(@RequestBody Order order){
//        System.out.println(order);
        orderService.addOrder(order);
    }

//    @RequestMapping(path = "", method = RequestMethod.DELETE)
//    public void deleteAllOrders(){
//        orderService.deleteAllOrder();
//    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrderById(@PathVariable String id) {
        orderService.deleteByOrderId(id);
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public List<Order> getOrderByCustomerUsername(@PathVariable String username) {
        return orderService.getOrderByCustomerUsername(username);
    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public void updateOrderById(@PathVariable String id, @RequestBody Order order) {
//        orderService.updateOrderById(id, order);
//    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
//    public Order getOrderById(@PathVariable String id) {
//        return orderService.getOrderById(id);
//    }
}

