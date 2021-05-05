package main.java;
import main.java.config.AppConfig;
import main.java.entity.Drug;
import main.java.entity.Producers;
import main.java.service.Service;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by CoT on 10/13/17.
 */
public class Handler {
    private static Service service = (Service) new AnnotationConfigApplicationContext(AppConfig.class).getBean("service");
    public static List drugs = service.getDrugsList();
    public static List producers = service.getProducersList();
    public static void setupDatabase(){
        ArrayList<Drug> drugs = new ArrayList<>();
        ArrayList<Producers> producers = new ArrayList<>();
        try {
            File myObj = new File("message.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                boolean isExist = false;
                for (Producers value : producers) {
                    if (value.getId().equals(split[9])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    Producers producer = new Producers();
                    producer.setId(split[9]);
                    producer.setName(split[8]);
                    producers.add(producer);
                }
            }
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                for (Producers producer : producers) {
                    if (producer.getId().equals(split[9])) {
                        Drug drug = new Drug(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], producer, split[10]);
                        drugs.add(drug);
                        break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException ignored) {}

        for (Producers producer: producers) {
            service.saveProducers(producer);
        }
        for (Drug drug: drugs) {
            service.saveDrugs(drug);
        }
    }

    static List deleteDrug(String id){
        return service.deleteDrug(id);

    }

    static List saveDrug(Drug drug){
        return service.saveDrug(drug);
    }

    static List saveProducer(Producers producer){
        return service.saveProducer(producer);
    }

    static List deleteProducer(String id){
        return service.deleteProducer(id);
    }

    static List updateDrugMoney(String id, int money){
        return service.updateDrugMoney(id, money);
    }

    static List getAllProducers(){
        return service.getProducers();
    }

    static List getAllDrugs(){
        return service.getDrugs();
    }

    static List getAllGroups(){
        return service.getAllGroups();
    }

    static List getAllTypes(){
        return service.getAllTypes();
    }

    static List getDrugSortAsc(){
        List<Drug> list = (List<Drug>) getAllDrugs();
        Collections.sort(list);
        return list;
    }

    static List getDrugSortDes(){
        List<Drug> list = (List<Drug>) getAllDrugs();
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    static List searchDrugsById(String id){
        return service.searchDrugsById(id);
    }

    static List searchDrugsByName(String name){
        return service.searchDrugsByName(name);
    }

    static List getNOP (String producerID){
        return service.getNOP(producerID);
    }
}
