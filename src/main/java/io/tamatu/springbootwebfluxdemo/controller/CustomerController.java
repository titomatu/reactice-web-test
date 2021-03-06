package io.tamatu.springbootwebfluxdemo.controller;

import io.tamatu.springbootwebfluxdemo.dto.Customer;
import io.tamatu.springbootwebfluxdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return service.loadAllCustomers();
    }

    /*
    Asynchronous and nonblocking
    subscriber can cancel the request at any time
    chrome broser -> X button i.e.
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream(){
        return service.loadAllCustomersStream();
    }
}
