package assignment.entity;

import assignment.id_generator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @Column
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_username", referencedColumnName = "username", nullable = false)
    private Customer customer;

    @Column
    private String purchaseTime;

    @Column
    private String purchaseType;

    @Column
    private int total;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
//    List<OrderDetail> orderDetailList;

    public Order() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public List<OrderDetail> getOrderDetailList() {
//        return orderDetailList;
//    }
//
//    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
//        this.orderDetailList = orderDetailList;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchase_time) {
        this.purchaseTime = purchase_time;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchase_type) {
        this.purchaseType = purchase_type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void increaseTotal(int cost) {
        total += cost;
    }

    //    public int getTransactionCost() {
//        int sum = 0;
//        for (OrderDetail orderDetail : orderDetailList) {
//            sum += orderDetail.getOrderCost();
//        }
//        return sum;
//    }

    @Override
    public String toString() {
        return customer.getUsername() + " -- " + purchaseTime + " -- " + purchaseType;
    }
}
