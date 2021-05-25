package assignment.controller;

import assignment.entity.Customer;
import assignment.entity.Drug;
import assignment.entity.Producer;
import assignment.service.CustomerService;
import assignment.service.DrugService;
import assignment.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
    @Autowired
    private DrugService drugService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public String authenticate(@RequestBody Map<String, String> user) {
        if (user.get("username").equals("admin123") && user.get("password").equals("admin123")) {
            return "admin";
        }
        Customer customer = customerService.getCustomerByUsername(user.get("username"));
        if (customer == null) {
            return "invalid";
        } else if (customer.getPassword().equals(user.get("password"))) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aa");
            customer.setLastLogin(dateFormat.format(date));
            customerService.save(customer);
            return "user";
        }
        return "invalid";
    }

    @PostMapping(path = "/getProducerData")
    public Map<String,String> getProducerData(@RequestBody String id){
        Producer producer = producerService.getProducerById(id);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", producer.getName());
        map.put("list", Arrays.toString(drugService.getDrugListByProducer(id).toArray()));
        return map;
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
                        Drug drug = new Drug(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], Integer.parseInt(split[11]), Integer.parseInt(split[12]), producer, split[10]);
                        drugs.add(drug);
                        break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException ignored) {
            return "Cannot find file";
        }
        setCustomer();

        for (Producer producer: producers) {
            producerService.addProducer(producer);
        }
        for (Drug drug: drugs) {
            drugService.addDrug(drug);
        }
        drugService.getAllDrugs();
        return "Success";
    }

    private void setCustomer() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aa");
        Customer customer1 = new Customer("user123", "user123", "Hoang", "Doan", "a@gmail.com", "", dateFormat.format(date));
        Customer customer2 = new Customer("user456", "user456", "Bao", "Tran", "a@gmail.com", "", dateFormat.format(date));
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);
    }

}
