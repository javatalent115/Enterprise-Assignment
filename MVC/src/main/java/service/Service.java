package main.java.service;
import main.java.entity.Drug;
import main.java.entity.Producers;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by CoT on 10/13/17.
 */

@Transactional
public class Service {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveDrug(Drug drug){
        sessionFactory.getCurrentSession().save(drug);
    }

    public List getDrugs(){
        // Get all obj
        return sessionFactory.getCurrentSession().createQuery("from Drugs").list();
//        // Get 1 by query

//        for (int i = 0; i < listt.size(); i++) {
//            Object[] ob = (Object[]) listt.get(i);
//            System.out.println(ob[0]);
//        }
//
//        // Get 1 by not query
//        Person person = (Person) sessionFactory.getCurrentSession().load(Person.class, 5);
//        System.out.println(person.getName());
    }

    public void updateDrugMoney(String id, int money){
        List list = sessionFactory.getCurrentSession().createQuery("from Drugs").list();
        for (int i = 0; i< list.size(); i++) {
            Drug drug = (Drug) list.get(i);
            if (drug.getId().equals(id)){
                drug = (Drug) sessionFactory.getCurrentSession().load(Drug.class, i+1);
                drug.setMoney(money);
                sessionFactory.getCurrentSession().update(drug);
                break;
            }
        }
    }
    public void updateProducer(String id, Producers producer){
        List list = sessionFactory.getCurrentSession().createQuery("from Producers").list();
        for (int i = 0; i< list.size(); i++) {
            Producers producers = (Producers) list.get(i);
            if (producers.getId().equals(id)){
                producers = (Producers) sessionFactory.getCurrentSession().load(Producers.class, i+1);
                producers.setId(producer.getId());
                producers.setName(producer.getName());
                sessionFactory.getCurrentSession().update(producers);
                break;
            }
        }
    }

    public void saveProducer(Producers producers){
        sessionFactory.getCurrentSession().save(producers);
    }

    public void deleteProducer(String id){
        List list = sessionFactory.getCurrentSession().createQuery("from Producers").list();
        for (int i = 0; i< list.size(); i++) {
            Producers producers = (Producers) list.get(i);
            if (producers.getId().equals(id)){
                producers = (Producers) sessionFactory.getCurrentSession().load(Producers.class, i+1);
                sessionFactory.getCurrentSession().delete(producers);
                break;
            }
        }
    }

    public void deleteDrug(String id){
        List list = sessionFactory.getCurrentSession().createQuery("from Drugs").list();
        for (int i = 0; i< list.size(); i++) {
            Drug drug = (Drug) list.get(i);
            if (drug.getId().equals(id)){
                drug = (Drug) sessionFactory.getCurrentSession().load(Drug.class, i+1);
                sessionFactory.getCurrentSession().delete(drug);
                break;
            }
        }
    }
}
