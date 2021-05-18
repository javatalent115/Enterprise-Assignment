package main.java.service;
import main.java.config.AppConfig;
import main.java.entity.Drug;
import main.java.entity.Producers;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.*;

/**
 * Created by CoT on 10/13/17.
 */
public class Handler {
    private static Service service = (Service) new AnnotationConfigApplicationContext(AppConfig.class).getBean("service");
    static List drugs = service.getDrugsList();
    static List producers = service.getProducersList();

    public static void setupDatabase(){ service.setupDatabase(); }


    ///////////////// Drug ///////////////////////
    public static List deleteDrug(String id){
        return service.deleteDrug(id);
    }

    public static List saveDrug(Drug drug){
        return service.saveDrug(drug);
    }

    public static List updateDrug(String id, int money, String sortType){
        return service.updateDrug(id, money, sortType);
    }

    public static List getAllDrugs(){
        return drugs;
    }

    public static List getDrugsByFilter(String group, String type, String sortType){
        return service.getDrugsByFilter(group, type, sortType);
    }


    ///////////////// Producer///////////////////////
    public static List saveProducer(Producers producer){
        return service.saveProducer(producer);
    }

    public static List getAllProducers(){
        return producers;
    }

    public static List getNOP (String producerID){
        return service.getNOP(producerID);
    }

}
