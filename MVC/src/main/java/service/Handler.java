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
    static List transactions = service.getTransactionList();
    static List customers = service.getCustomerList();
    static List orders = service.getOrderList();

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

    public static List getAllGroups(){
        return service.getAllGroups();
    }

    public static List getAllTypes(){
        return service.getAllTypes();
    }

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

    public static List searchDrugsByName(String name){
        return service.searchDrugsByName(name);
    }

    public static List getNOP (String producerID){
        return service.getNOP(producerID);
    }


    public static List getAllTransactions() {
        return service.getTransactions();
    }

    public static List getTransactionsByCustomerUsename(String username) {
        return service.getTransactionsByCustomerUsename(username);
    }

    public static List getTransactionsByPurchaseTime(String purchaseTime) {
        return service.getTransactionsByPurchaseTime(purchaseTime);
    }

    public static List getAllCustomers() {
        return service.getCustomers();
    }

    public static String getCustomerByUsername(String username) {
        return service.getCustomerByUsername(username);
    }

    public static List getAllOrders() {
        return service.getOrders();
    }

    public static List getOrdersByTransactionTime(String transactionTime) {
        return service.getOrdersByTransactionTime(transactionTime);
    }

    public static List getOrdersByCustomerUsername(String username) {
        return service.getOrdersByCustomerUsername(username);
    }
}
