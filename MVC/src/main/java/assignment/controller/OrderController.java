package assignment.controller;

import assignment.entity.Order;
import assignment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/transaction")
public class OrderController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Order> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addTransaction(@RequestBody Order order){
        transactionService.addTransaction(order);
    }

//    @RequestMapping(path = "", method = RequestMethod.DELETE)
//    public void deleteAllTransactions(){
//        transactionService.deleteAllTransaction();
//    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void deleteTransactionById(@PathVariable String id) {
        transactionService.deleteByTransactionId(id);
    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public void updateTransactionById(@PathVariable String id, @RequestBody Order transaction) {
//        transactionService.updateTransactionById(id, transaction);
//    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Order getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id);
    }
}

