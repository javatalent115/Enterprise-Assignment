package main.java.entity;

import main.java.id_generator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {
    @Column
    @Id
    private String username;

    @Column
    private String password;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Transaction> transactionList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return username + " -- " + password + " -- " + firstname + " -- " + lastname + " -- " + address;
    }
}
