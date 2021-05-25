package assignment.service;

import assignment.entity.OrderDetail;
import assignment.repository.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepo orderDetailRepo;

    public void addOrder(OrderDetail orderDetail){
        this.orderDetailRepo.save(orderDetail);
    }

    public List<OrderDetail> getAllOrders(){
        return this.orderDetailRepo.findAll();
    }

    public void deleteByOrderId(String id) {
        this.orderDetailRepo.deleteById(id);
    }

    public OrderDetail getOrderById(String id) {
        Optional<OrderDetail> result = this.orderDetailRepo.findById(id);
        return result.orElse(null);
    }

    public List<OrderDetail> getOrderDetailByOrderId(String orderId) {
        List<OrderDetail> orderDetailList = orderDetailRepo.findAll();
        orderDetailList.removeIf(orderDetail -> !orderDetail.getOrder().getId().equals(orderId));
        return orderDetailList;
    }

//    public void updateOrderById(String id, OrderDetail newOrder) {
//        getOrderById(id).replace(newOrder);
//    }

    public long countOrder() {
        return this.orderDetailRepo.count();
    }

    public void deleteAllOrder() {
        this.orderDetailRepo.deleteAll();
    }
}
