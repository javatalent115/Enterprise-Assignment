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
    OrderRepo orderRepo;

    public void addOrder(Order order){
        this.orderRepo.save(order);
    }

    public List<Order> getAllOrders(){
        return this.orderRepo.findAll();
    }

    public void deleteByOrderId(String id) {
        this.orderRepo.deleteById(id);
    }

    public Order getOrderById(String id) {
        Optional<Order> result = this.orderRepo.findById(id);
        return result.orElse(null);
    }

    public List<Order> getOrderByCustomerUsername(String username) {
        List<Order> orderList = orderRepo.findAll();
        orderList.removeIf(order -> !order.getCustomer().getUsername().equals(username));
        return orderList;
    }

    public long countOrder() {
        return orderRepo.count();
    }

    public void deleteAllOrder() {
        orderRepo.deleteAll();
    }
}
