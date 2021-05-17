package assignment.controller;

import assignment.entity.Producer;
import assignment.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Producer> getAllProducers(){
        return producerService.getAllProducers();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addProducer(@RequestBody Producer producer){
        producerService.addProducer(producer);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void deleteAllProducers(){
        producerService.deleteAllProducer();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Producer getProducerById(@PathVariable String id) {
        return producerService.getProducerById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteProducerById(@PathVariable String id) {
        producerService.deleteByProducerId(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateProducerById(@PathVariable String id, @RequestBody Producer producer) {
        producerService.updateProducerById(id, producer);
    }
}

