package main.java.service;
import main.java.Handler;
import main.java.entity.Drug;
import main.java.entity.Producers;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List saveDrug(Drug drug){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.drugs) {
            Drug drug1 = (Drug) o;
            if (drug1.getId().equals(drug.getId())) {
                result.add("failed");
                return result;

            }
        }
        sessionFactory.getCurrentSession().save(drug);
        result.add("success");
        return result;
    }

    public void saveDrugs(Drug drug){
        sessionFactory.getCurrentSession().save(drug);
    }

    public List getDrugsList(){
        assert sessionFactory != null;
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Drug> query = builder.createQuery(Drug.class);
        Root<Drug> root = query.from(Drug.class);
        query.select(root);
        Query<Drug> q = sessionFactory.getCurrentSession().createQuery(query);
        return q.getResultList();
    }
    public List getProducersList(){
        assert sessionFactory != null;
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Producers> query = builder.createQuery(Producers.class);
        Root<Producers> root = query.from(Producers.class);
        query.select(root);
        Query<Producers> q = sessionFactory.getCurrentSession().createQuery(query);
        return q.getResultList();
    }

    public List getDrugs(){
        return Handler.drugs;
    }

    public List getProducers(){
        return Handler.producers;
    }

    public List updateDrugMoney(String id, int money){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.drugs) {
            Drug drug = (Drug) o;
            if (drug.getId().equals(id)) {
                drug.setMoney(money);
                sessionFactory.getCurrentSession().update(drug);
                Handler.drugs = getDrugs();
                result.add("success");
                return result;
            }
        }
        result.add("failed");
        return result;
    }

    public List searchDrugsById(String id){
        List<String> l = new ArrayList<>();
        for (Object o : Handler.drugs) {
            Drug drug = (Drug) o;
            if (drug.getId().contains(id)) {
                l.add(drug.toString());
            }
        }
        return l;
    }

    public List searchDrugsByName(String name){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.drugs) {
            Drug drug = (Drug) o;
            if (drug.getName().contains(name)) {
                result.add(drug.toString());
            }
        }
        return result;
    }

    public List getNOP (String producerID){
        List<String> result = new ArrayList<>();
        int count = 0;
        for (Object o : Handler.producers) {
            Drug drug = (Drug) o;
            if (drug.getProducers().getId().equals(producerID)) {
                count++;
            }
        }
        result.add(Integer.toString(count));
        return result;
    }

    public List getAllGroups(){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Handler.drugs.size(); i++) {
            Drug drug = (Drug) Handler.drugs.get(i);
            if (!result.contains(drug.getDrugGroup())) {
                result.add(drug.getDrugGroup());
            }
        }
        return result;
    }

    public List getAllTypes(){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Handler.drugs.size(); i++) {
            Drug drug = (Drug) Handler.drugs.get(i);
            if (!result.contains(drug.getType()) && !drug.getType().equals(" ") && !drug.getType().equals("--")) {
                result.add(drug.getDrugGroup());
            }
        }
        result.add("Undefined");
        return result;
    }

    public List saveProducer(Producers newProducer){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.producers) {
            Producers producer = (Producers) o;
            if (producer.getId().equals(newProducer.getId())) {
                result.add("failed");
                return result;
            }
        }
        sessionFactory.getCurrentSession().save(newProducer);
        result.add("success");
        return result;
    }
    public void saveProducers(Producers producers){

        sessionFactory.getCurrentSession().save(producers);
    }

    public List deleteProducer(String id){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.producers) {
            Producers producer = (Producers) o;
            if (producer.getId().equals(id)) {
                sessionFactory.getCurrentSession().delete(producer);
                result.add("success");
                return result;
            }
        }
        result.add("failed");
        return result;
    }
    //
    public List deleteDrug(String id){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.drugs) {
            Drug drug = (Drug) o;
            if (drug.getId().equals(id)) {
                sessionFactory.getCurrentSession().delete(drug);
                Handler.drugs = getDrugs();
                result.add("success");
                return result;
            }
        }
        result.add("failed");
        return result;
    }
}
