package assignment.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CoT on 10/13/17.
 */

@Entity
@Table(name = "Producers")
public class Producer {

    @Id
    @Column(length = 1024)
    private String id;

    @Column(length = 1024)
    private String name;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
//    private List<Drug> drugList;

    public Producer() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Drug> getDrugList() {
//        return drugList;
//    }
//
//    public void setDrugList(List<Drug> drugList) {
//        this.drugList = drugList;
//    }

    @Override
    public String toString() {
        return id +" -- " + name ;
    }

    public void replace(Producer producer) {
        this.name = producer.getName();
    }
}
