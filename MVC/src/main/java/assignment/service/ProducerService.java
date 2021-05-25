package assignment.service;

import assignment.entity.Producer;
import assignment.repository.ProducerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProducerService {
    @Autowired
    ProducerRepo producerRepo;

    public void addProducer(Producer producer){
        this.producerRepo.save(producer);
    }

    public List<Producer> getAllProducers(){
        return this.producerRepo.findAll();
    }

    public Producer getProducerById(String id) {
        Optional<Producer> result = this.producerRepo.findById(id);
        return result.orElse(null);
    }
}
