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
public class TransactionService {
    @Autowired
    OrderRepo OrderRepo;

    public void addTransaction(Order order){
        this.OrderRepo.save(order);
    }

    public List<Order> getAllTransactions(){
        return this.OrderRepo.findAll();
    }

    public void deleteByTransactionId(String id) {
        this.OrderRepo.deleteById(id);
    }

    public Order getTransactionById(String id) {
        Optional<Order> result = this.OrderRepo.findById(id);
        return result.orElse(null);
    }

//    public void updateTransactionById(String id, Order newTransaction) {
//        getTransactionById(id).replace(newTransaction);
//    }

    public long countTransaction() {
        return this.OrderRepo.count();
    }

    public void deleteAllTransaction() {
        this.OrderRepo.deleteAll();
    }
}
