package assignment.entity;

import javax.persistence.*;

/**
 * Created by CoT on 10/13/17.
 */

@Entity
@Table(name = "Producer")
public class Producer {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    public Producer() {}

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

    public String toString() {
        return id +" -- " + name ;
    }

    public void replace(Producer producer) {
        this.name = producer.getName();
    }
}
