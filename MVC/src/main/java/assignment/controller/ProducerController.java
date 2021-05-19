package assignment.controller;

import assignment.entity.Producer;
import assignment.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @RequestMapping(path = "/getProducers", method = RequestMethod.POST)
    public Map<String, String> getAllProducers(){
        List<Producer> list = producerService.getAllProducers();
        Map<String, String> map = new HashMap<>();
        for (int i = 0 ; i < list.size(); i++){
            map.put(String.valueOf(i), list.get(i).toString());
        }
        return map;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addProducer(@RequestBody Producer producer){
        producerService.addProducer(producer);
    }

//    @RequestMapping(path = "", method = RequestMethod.DELETE)
//    public void deleteAllProducers(){
//        producerService.deleteAllProducer();
//    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteProducerById(@PathVariable String id) {
        producerService.deleteByProducerId(id);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Producer getProducerById(@PathVariable String id) {
        return producerService.getProducerById(id);
    }
}

