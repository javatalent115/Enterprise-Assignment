package assignment.service;

import assignment.entity.Order;
import assignment.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderService {
    @Autowired
    OrderRepo OrderRepo;

    public void addOrder(Order order){
        this.OrderRepo.save(order);
    }

    public List<Order> getAllOrders(){
        return this.OrderRepo.findAll();
    }

    public void deleteByOrderId(String id) {
        this.OrderRepo.deleteById(id);
    }

    public Order getOrderById(String id) {
        Optional<Order> result = this.OrderRepo.findById(id);
        return result.orElse(null);
    }

//    public void updateOrderById(String id, Order newOrder) {
//        getOrderById(id).replace(newOrder);
//    }

    public long countOrder() {
        return this.OrderRepo.count();
    }

    public void deleteAllOrder() {
        this.OrderRepo.deleteAll();
    }
}
