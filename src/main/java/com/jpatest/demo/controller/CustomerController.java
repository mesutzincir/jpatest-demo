package com.jpatest.demo.controller;

import com.jpatest.demo.DTO.CustomerDTO;
import com.jpatest.demo.model.Customer;
import com.jpatest.demo.repository.ICustomerRepository;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
public class CustomerController {
    ICustomerRepository customerRepository;

    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("alldto")
    public List<CustomerDTO> alldto() {
        ModelMapper mapper;
        mapper = new ModelMapper();
        List<Customer> listCustomer = customerRepository.findAll();
        List<CustomerDTO> listDto = listCustomer.stream().map(c -> mapper.map(c, CustomerDTO.class)).collect(Collectors.toList());
        return listDto;
    }
    @GetMapping("getnames")
    public Map<Long, String> getnames() {
        ModelMapper mapper;
        mapper = new ModelMapper();
        List<Customer> listCustomer = customerRepository.findAll();
        Map<Long, String> listDto =  listCustomer.stream().collect(Collectors.toMap(c-> c.getId(), c->c.getName()));
        return listDto;
    }

    @GetMapping("find/{name}")
    public CustomerDTO find(@PathVariable(value = "name") String name) {
        ModelMapper mapper = new ModelMapper();
        Customer customer = customerRepository.findByName(name);
        return mapper.map(customer, CustomerDTO.class);
    }

    @PostMapping("insert")
    public CustomerDTO insert(@RequestBody CustomerDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Customer cust = mapper.map(dto, Customer.class);
        cust = customerRepository.save(cust);
        return mapper.map(cust, CustomerDTO.class);
    }
}
