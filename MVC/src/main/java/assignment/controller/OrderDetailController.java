package assignment.controller;

import assignment.entity.OrderDetail;
import assignment.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<OrderDetail> getAllOrders(){
        return orderDetailService.getAllOrders();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addOrderDetail(@RequestBody OrderDetail orderDetail){
        orderDetailService.addOrder(orderDetail);
    }

//    @RequestMapping(path = "", method = RequestMethod.DELETE)
//    public void deleteAllOrders(){
//        orderDetailService.deleteAllOrder();
//    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.GET)
    public List<OrderDetail> getOrderDetailByOrderId(@PathVariable String orderId) {
        return orderDetailService.getOrderDetailByOrderId(orderId);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrderById(@PathVariable String id) {
        orderDetailService.deleteByOrderId(id);
    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public void updateOrderById(@PathVariable String id, @RequestBody OrderDetail orderDetail) {
//        orderDetailService.updateOrderById(id, orderDetail);
//    }
}

