package com.jpatest.demo;

import com.jpatest.demo.model.Country;
import com.jpatest.demo.model.Customer;
import com.jpatest.demo.repository.ICountryRepository;
import com.jpatest.demo.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    ICountryRepository countryRepository;
    @Autowired
    ICustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Override
    public void run(String... args) {
        // Create a couple of Book and BookCategory
        //bookCategoryRepository.save(new BookCategory("Category 1", new Book("Hello Koding 1"), new Book("Hello Koding 2")));
        countryRepository.save(new Country("TR","TURKEY", "TRY",new Customer("mesut"), new Customer("akif")));
        countryRepository.save(new Country("GB","UK","GBP", new Customer("jack")));
    }
}
