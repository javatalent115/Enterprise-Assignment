package main.java.entity;

import main.java.id_generator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(
            name = "book_seq",
            strategy = "assignment.id_generator.SequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CUS_"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
}
