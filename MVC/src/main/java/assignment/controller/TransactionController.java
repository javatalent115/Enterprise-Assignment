package assignment.controller;

import assignment.entity.Transaction;
import assignment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addTransaction(@RequestBody Transaction transaction){
        transactionService.addTransaction(transaction);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void deleteAllTransactions(){
        transactionService.deleteAllTransaction();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Transaction getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteTransactionById(@PathVariable String id) {
        transactionService.deleteByTransactionId(id);
    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public void updateTransactionById(@PathVariable String id, @RequestBody Transaction transaction) {
//        transactionService.updateTransactionById(id, transaction);
//    }
}

