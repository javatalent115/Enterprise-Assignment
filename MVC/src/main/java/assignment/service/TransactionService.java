package assignment.service;

import assignment.entity.Transaction;
import assignment.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TransactionService {
    @Autowired
    TransactionRepo TransactionRepo;

    public void addTransaction(Transaction transaction){
        this.TransactionRepo.save(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return this.TransactionRepo.findAll();
    }

    public void deleteByTransactionId(String id) {
        this.TransactionRepo.deleteById(id);
    }

    public Transaction getTransactionById(String id) {
        Optional<Transaction> result = this.TransactionRepo.findById(id);
        return result.orElse(null);
    }

//    public void updateTransactionById(String id, Transaction newTransaction) {
//        getTransactionById(id).replace(newTransaction);
//    }

    public long countTransaction() {
        return this.TransactionRepo.count();
    }

    public void deleteAllTransaction() {
        this.TransactionRepo.deleteAll();
    }
}
