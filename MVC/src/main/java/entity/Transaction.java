package main.java.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Transaction")
public class Transaction implements Comparable {
    @Column
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Id
    @GenericGenerator(name = "purchase_time", strategy = "id_generation.DateIdGenerator")
    @GeneratedValue(generator = "purchase_time")
    private String purchase_time;

    @Column
    private String purchase_type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
    List<Order> orderList;

    @Override
    public int compareTo(Object o) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd MM:mm:ss");
        try {
            Date thisDate = simpleDateFormat.parse(purchase_time);
            Date thatDate = simpleDateFormat.parse(((String) o));
            if (thisDate.after(thatDate)) return 1;
            else return -1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPurchase_time() {
        return purchase_time;
    }

    public void setPurchase_time(String purchase_time) {
        this.purchase_time = purchase_time;
    }

    public String getPurchase_type() {
        return purchase_type;
    }

    public void setPurchase_type(String purchase_type) {
        this.purchase_type = purchase_type;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}

