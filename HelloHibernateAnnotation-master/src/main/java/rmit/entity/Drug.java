package rmit.entity;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Drugs")
public class Drug {
    @Id
    @Column
    private String id;

    @Column (length = 1024)
    private String name;

    @Column (length = 1024)
    private String preparation;

    @Column (length = 1024)
    private String packaging;

    @Column (length = 1024)
    private String drugGroup;

    @Column (length = 1024)
    private String dosage;

    @Column (length = 1024)
    private String type;

    @Column (length = 1024)
    private String ingredients;

    @Column (length = 1024)
    private String country;
    @Column
    private int money;
    @ManyToOne
    @JoinColumn(name="producer", nullable=false)
    private Producers producers;


    public Drug(String id, String name, String preparation, String packaging, String drugGroup, String dosage, String type, String ingredients, Producers producers, String country){
        this.id = id;
        this.name = name;
        this.preparation = preparation;
        this.packaging = packaging;
        this.drugGroup = drugGroup;
        this.dosage = dosage;
        this.type = type;
        this.ingredients = ingredients;
        this.producers = producers;
        this.country = country;
        this.money = new Random().nextInt(50)+8;
    }

    public String getId() {
        return id;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
