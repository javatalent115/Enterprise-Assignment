package assignment.entity;

import assignment.id_generator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order implements Comparable{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_seq")
    @GenericGenerator(
            name = "ord_seq",
            strategy = "assignment.id_generator.SequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ORD_"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_username", referencedColumnName = "username", nullable = false)
    private Customer customer;

    @Column
    private String purchaseTime;

    @Column
    private String purchaseType;

//    @Column
//    private String total;
    //TODO can be added later

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    List<OrderDetail> orderDetailList;

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

    public List<OrderDetail> getOrderList() {
        return orderDetailList;
    }

    public void setOrderList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public int getTransactionCost() {
        int sum = 0;
        for (OrderDetail orderDetail : orderDetailList) {
            sum += orderDetail.getOrderCost();
        }
        return sum;
    }

    @Override
    public String toString() {
        return customer.getUsername() + " -- " + purchaseTime + " -- " + purchaseType + " -- " + getTransactionCost();
    }
}
