package com.jpatest.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@Data
//@EqualsAndHashCode(exclude = "customers") // bu satir olmaz ise infinite loop oluyor.
//@ToString(exclude={"customers"})
// @Data kullanirsak @EqualsAndHashCode ve @ToString kullanip collection alanlari haric turmak gerekiyor yoksa sonsuz dongu seklinde birbirini select edip duruyor.
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Country {
    @Id
    String id;
    String name;
    String currency;

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @OneToMany(mappedBy = "nation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Customer> customers;

    public  Country()
    {

    }
    public Country(String id, String name, String currency, Customer... customers) {
        this.id = id;
        this.name= name;
        this.currency = currency;
        this.customers = Stream.of(customers).collect(Collectors.toSet());
        this.customers.forEach(x -> x.setNation(this));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
