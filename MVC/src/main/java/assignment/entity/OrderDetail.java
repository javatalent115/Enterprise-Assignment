package assignment.entity;

import assignment.id_generator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_detail_seq")
    @GenericGenerator(
            name = "ord_detail_seq",
            strategy = "assignment.id_generator.SequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "DET_"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "drug_id", referencedColumnName = "id", nullable = false)
    private Drug drug;

    @Column
    private int quantity;

    @Column
    private int cost;

    public OrderDetail() { }

    public OrderDetail(String id, Order order, Drug drug, int quantity, int cost) {
        this.id = id;
        this.order = order;
        this.drug = drug;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

//    public int getOrderCost() {
//        return quantity * drug.getMoney();
//    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return id + " -- " + order.getId() + " -- " + drug.getId() + " -- " + quantity + " -- " + cost;
    }
}

