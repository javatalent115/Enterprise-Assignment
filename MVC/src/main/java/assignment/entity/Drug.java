package assignment.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

import assignment.entity.Producer;

@Entity
@Table(name = "Drugs")
public class Drug implements Comparable {
    @Id
    @Column
    private String id;

    @Column (length = 1024)
    private String name;

    @Column (length = 1024)
    private String preparation = "Undefined";

    @Column (length = 1024)
    private String packaging = "Undefined";

    @Column (length = 1024)
    private String drugGroup = "Undefined";

    @Column (length = 1024)
    private String dosage = "Undefined";

    @Column (length = 1024)
    private String type = "Undefined";

    @Column (length = 1024)
    private String ingredients = "Undefined";

    @Column (length = 1024)
    private String country = "Undefined";

    @Column
    private int money;

    @Column
    private int stock;

    @ManyToOne
    @JoinColumn(name="producer", nullable=false)
    private Producer producer;

    public Drug(){}

    public Drug(String id, String name, String preparation, String packaging, String drugGroup, String dosage, String type, String ingredients, int money, int stock, Producer producer, String country){
        this.id = id;
        this.name = name;
        if (preparation.equals("null") || preparation.equals("")){
            this.preparation = "Undefined";
        }
        else {
            this.preparation = preparation;
        }
        if (packaging.equals("null") || packaging.equals("")) {
            this.packaging = "Undefined";
        }
        else {
            this.packaging = packaging;
        }
        if (drugGroup.equals("null") || drugGroup.equals("")) {
            this.drugGroup = "Undefined";
        }
        else {
            this.drugGroup = drugGroup;
        }
        if (dosage.equals("null") || dosage.equals("--") || dosage.equals(" ")) {
            this.dosage = "Undefined";
        }
        else {
            this.dosage = dosage;
        }
        if (type.equals("null") || type.equals("")) {
            this.type = "Undefined";
        }
        else {
            this.type = type;
        }
        if (ingredients.equals("null") || ingredients.equals("")) {
            this.ingredients = "Undefined";
        }
        else {
            this.ingredients = ingredients;
        }
        this.producer = producer;
        if (country.equals("null") || country.equals("")) {
            this.country = "Undefined";
        }
        else {
            this.country = country;
        }
        this.money = money;
        this.stock = stock;
    }

    public Drug(String id, String name, String preparation, String packaging, String drugGroup, String dosage, String type, String ingredients, Producer producer, String country){
        this.id = id;
        this.name = name;
        if (preparation.equals("null") || preparation.equals("")){
            this.preparation = "Undefined";
        }
        else {
            this.preparation = preparation;
        }
        if (packaging.equals("null") || packaging.equals("")) {
            this.packaging = "Undefined";
        }
        else {
            this.packaging = packaging;
        }
        if (drugGroup.equals("null") || drugGroup.equals("")) {
            this.drugGroup = "Undefined";
        }
        else {
            this.drugGroup = drugGroup;
        }
        if (dosage.equals("null") || dosage.equals("--") || dosage.equals(" ")) {
            this.dosage = "Undefined";
        }
        else {
            this.dosage = dosage;
        }
        if (type.equals("null") || type.equals("")) {
            this.type = "Undefined";
        }
        else {
            this.type = type;
        }
        if (ingredients.equals("null") || ingredients.equals("")) {
            this.ingredients = "Undefined";
        }
        else {
            this.ingredients = ingredients;
        }
        this.producer = producer;
        if (country.equals("null") || country.equals("")) {
            this.country = "Undefined";
        }
        else {
            this.country = country;
        }
        this.money = new Random().nextInt(50)+8;
        this.stock = new Random().nextInt(50)+3;
    }

    public String getName() {
        return name;
    }

    public String getDrugGroup() {
        return drugGroup;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public void setDrugGroup(String drugGroup) {
        this.drugGroup = drugGroup;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return id +" -- " + name +" -- " +  preparation +" -- " + packaging + " -- " + drugGroup + " -- " + dosage + " -- " + type + " -- "+ ingredients + " -- " + country +" -- "+  money + " -- " + stock + " -- "+ producer.getId();
    }

    @Override
    public int compareTo(Object drug) {
        int compareMoney=((Drug)drug).getMoney();
        return this.money-compareMoney;
    }
}
