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
    OrderDetailRepo OrderDetailRepo;

    public void addOrder(OrderDetail orderDetail){
        this.OrderDetailRepo.save(orderDetail);
    }

    public List<OrderDetail> getAllOrders(){
        return this.OrderDetailRepo.findAll();
    }

    public void deleteByOrderId(String id) {
        this.OrderDetailRepo.deleteById(id);
    }

    public OrderDetail getOrderById(String id) {
        Optional<OrderDetail> result = this.OrderDetailRepo.findById(id);
        return result.orElse(null);
    }

//    public void updateOrderById(String id, OrderDetail newOrder) {
//        getOrderById(id).replace(newOrder);
//    }

    public long countOrder() {
        return this.OrderDetailRepo.count();
    }

    public void deleteAllOrder() {
        this.OrderDetailRepo.deleteAll();
    }
}
