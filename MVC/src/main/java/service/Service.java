package main.java.service;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map saveDrug(Drug drug){
        List list = getDrugs();
        for (Object o : list) {
            Drug drug1 = (Drug) o;
            if (drug1.getId().equals(drug.getId())) {
                return (Map) new HashMap<>().put("response", "failed");

            }
        }
        sessionFactory.getCurrentSession().save(drug);
        return (Map) new HashMap<>().put("response","success");
    }

    public void saveDrugs(Drug drug){
        sessionFactory.getCurrentSession().save(drug);
    }

    public List getDrugs(){
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Drug> query = builder.createQuery(Drug.class);
        Root<Drug> root = query.from(Drug.class);
        query.select(root);
        Query<Drug> q = sessionFactory.getCurrentSession().createQuery(query);
        return q.getResultList();
    }
    public List getProducers(){
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Producers> query = builder.createQuery(Producers.class);
        Root<Producers> root = query.from(Producers.class);
        query.select(root);
        Query<Producers> q = sessionFactory.getCurrentSession().createQuery(query);
        return q.getResultList();
    }

    public Map updateDrugMoney(String id, int money){
        List list = getDrugs();
        for (Object o : list) {
            Drug drug = (Drug) o;
            if (drug.getId().equals(id)) {
                drug.setMoney(money);
                sessionFactory.getCurrentSession().update(drug);
                return (Map) new HashMap<>().put("response", "success");
            }
        }
        return (Map) new HashMap<>().put("response","failed");
    }

    public List searchDrugsById(String id){
        List list = getDrugs();
        List<String> l = new ArrayList<>();
        for (Object o : list) {
            Drug drug = (Drug) o;
            if (drug.getId().contains(id)) {
                l.add(drug.toString());
            }
        }
        return l;
    }

    public List searchDrugsByName(String name){
        List list = getDrugs();
        List<String> l = new ArrayList<>();
        for (Object o : list) {
            Drug drug = (Drug) o;
            if (drug.getName().contains(name)) {
                l.add(drug.toString());
            }
        }
        return l;
    }

    public Map getNOP (String producerID){
        List list = getDrugs();
        int count = 0;
        for (Object o : list) {
            Drug drug = (Drug) o;
            if (drug.getProducers().getId().equals(producerID)) {
                count++;
            }
        }
        return (Map) new HashMap<>().put("response", Integer.toString(count));
    }

    public Map getAllGroups(){
        List list = getDrugs();
        HashMap<String, String> groups = new HashMap<>();
        for (int i = 0 ; i < list.size(); i++) {
            Drug drug = (Drug) list.get(i);
            if (!groups.containsValue(drug.getDrugGroup())) {
                groups.put(Integer.toString(i),drug.getDrugGroup());
            }
        }
        return groups;
    }

    public Map getAllTypes(){
        List list = getDrugs();
        HashMap<String, String> groups = new HashMap<>();
        for (int i = 0 ; i < list.size(); i++) {
            Drug drug = (Drug) list.get(i);
            if (!groups.containsValue(drug.getType()) && !drug.getType().equals(" ") && !drug.getType().equals("--")) {
                groups.put(Integer.toString(i),drug.getDrugGroup());
            }
        }
        groups.put(Integer.toString(list.size()),"Undefined");
        return groups;
    }

    public Map saveProducer(Producers producers){
        List list = getProducers();
        for (Object o : list) {
            Producers producer = (Producers) o;
            if (producer.getId().equals(producers.getId())) {
                return (Map) new HashMap<>().put("response", "failed");
            }
        }
        sessionFactory.getCurrentSession().save(producers);
        return (Map) new HashMap<>().put("response","success");
    }
    public void saveProducers(Producers producers){
        sessionFactory.getCurrentSession().save(producers);
    }
    public Map deleteProducer(String id){
        List list = getProducers();
        for (Object o : list) {
            Producers producers = (Producers) o;
            if (producers.getId().equals(id)) {
                sessionFactory.getCurrentSession().delete(producers);
                return (Map) new HashMap<>().put("response", "success");
            }
        }
        return (Map) new HashMap<>().put("response","failed");
    }
    //
    public Map deleteDrug(String id){
        List list = getDrugs();
        for (Object o : list) {
            Drug drug = (Drug) o;
            if (drug.getId().equals(id)) {
                sessionFactory.getCurrentSession().delete(drug);
                return (Map) new HashMap<>().put("response", "success");
            }
        }
        return (Map) new HashMap<>().put("response","failed");
    }
}
