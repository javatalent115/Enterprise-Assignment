package main.java.entity;

import main.java.id_generator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Order")
public class Order {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_seq")
    @GenericGenerator(
            name = "tran_seq",
            strategy = "assignment.entity.DepartmentIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ORD_"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

    @ManyToOne
    @JoinColumn(name = "transaction", referencedColumnName = "id", nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "drug", referencedColumnName = "id", nullable = false)
    private Drug drug;

    @Column
    private int quantity;
}
