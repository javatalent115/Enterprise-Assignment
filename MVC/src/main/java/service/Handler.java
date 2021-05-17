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

    public static List deleteDrug(String id){
        return service.deleteDrug(id);
    }

    public static List saveDrug(Drug drug){
        return service.saveDrug(drug);
    }

    public static List saveProducer(Producers producer){
        return service.saveProducer(producer);
    }

    public static List updateDrugMoney(String id, int money){
        return service.updateDrugMoney(id, money);
    }

    public static List getAllProducers(){
        return service.getProducers();
    }

    public static List getAllDrugs(){
        return service.getDrugs();
    }

    public static List getDrugsByGroup(String group){
        return service.getDrugsByGroup(group);
    }

    public static List getDrugsByType(String type){
        return service.getDrugsByType(type);
    }

    public static List getDrugsByTypeAndGroup(String group, String type){ return service.getDrugsByTypeAndGroup(group,type); }

    public static List getDrugSortAsc(){
        List<Drug> list = (List<Drug>) getAllDrugs();
        Collections.sort(list);
        return list;
    }

    public static List getDrugSortDes(){
        List<Drug> list = (List<Drug>) getAllDrugs();
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public static List searchDrugsById(String id){
        return service.searchDrugsById(id);
    }

    public static List getNOP (String producerID){
        return service.getNOP(producerID);
    }

    public static List sortDrugsName(){
        return service.sortDrugsName();
    }
}
