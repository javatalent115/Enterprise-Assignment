package main.java;
import main.java.config.AppConfig;
import main.java.entity.Drug;
import main.java.entity.Producers;
import main.java.service.Service;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.*;

/**
 * Created by CoT on 10/13/17.
 */
public class Handler {
    private static Service service = (Service) new AnnotationConfigApplicationContext(AppConfig.class).getBean("service");
    public static List drugs = service.getDrugsList();
    public static List producers = service.getProducersList();

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
