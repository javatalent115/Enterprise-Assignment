package assignment.controller;

import assignment.entity.Drug;
import assignment.entity.Producer;
import assignment.service.DrugService;
import assignment.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
    @Autowired
    private DrugService drugService;

    @Autowired
    private ProducerService producerService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String hello(){
        return "Hello";
    }

    @RequestMapping(path = "/setDatabase", method = RequestMethod.GET)
    public String setDatabase(){
        ArrayList<Drug> drugs = new ArrayList<>();
        ArrayList<Producer> producers = new ArrayList<>();
        try {
            File myObj = new File("message.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                boolean isExist = false;
                for (Producer value : producers) {
                    if (value.getId().equals(split[9])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    Producer producer = new Producer();
                    producer.setId(split[9]);
                    producer.setName(split[8]);
                    producers.add(producer);
                }
            }
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                for (assignment.entity.Producer producer : producers) {
                    if (producer.getId().equals(split[9])) {
                        Drug drug = new Drug(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], producer, split[10]);
                        drugs.add(drug);
                        break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException ignored) {
            return "Cannot find file";
        }

        for (Producer producer: producers) {
            producerService.addProducer(producer);
        }
        for (Drug drug: drugs) {
            drugService.addDrug(drug);
        }
        return "Success";
    }

}
