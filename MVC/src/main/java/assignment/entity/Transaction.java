package assignment.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Transaction")
public class Transaction implements Comparable{
    @ManyToOne
    @JoinColumn(name = "customer_username", referencedColumnName = "username")
    private Customer customer;

    @Id
    @GenericGenerator(name = "purchase_time", strategy = "id_generation.DateIdGenerator")
    @GeneratedValue(generator = "purchase_time")
    private String purchaseTime;

    @Column
    private String purchaseType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
    List<Order> orderList;

    @Override
    public int compareTo(Object o) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd MM:mm:ss");
        try {
            Date thisDate = simpleDateFormat.parse(purchaseTime);
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public int getTransactionCost() {
        int sum = 0;
        for (Order order : orderList) {
            sum += order.getOrderCost();
        }
        return sum;
    }

    @Override
    public String toString() {
        return customer.getUsername() + " -- " + purchaseTime + " -- " + purchaseType + " -- " + getTransactionCost();
    }
}
