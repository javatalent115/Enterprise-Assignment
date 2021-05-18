package assignment.controller;

import assignment.entity.OrderDetail;
import assignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/order")
public class OrderDetailController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<OrderDetail> getAllOrders(){
        return orderService.getAllOrders();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addOrder(@RequestBody OrderDetail orderDetail){
        orderService.addOrder(orderDetail);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void deleteAllOrders(){
        orderService.deleteAllOrder();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OrderDetail getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrderById(@PathVariable String id) {
        orderService.deleteByOrderId(id);
    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public void updateOrderById(@PathVariable String id, @RequestBody OrderDetail order) {
//        orderService.updateOrderById(id, order);
//    }
}

