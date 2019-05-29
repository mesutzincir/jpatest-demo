package com.jpatest.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table( indexes = {@Index(name = "customer_name_x1", columnList = "name", unique = false)} )
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    String name;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn
    Country nation;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, Country country) {
        this.name = name;
        this.nation = country;
    }

}
