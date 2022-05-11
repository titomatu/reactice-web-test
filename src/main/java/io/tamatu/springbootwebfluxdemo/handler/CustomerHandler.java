package io.tamatu.springbootwebfluxdemo.handler;

import io.tamatu.springbootwebfluxdemo.dao.CustomerDao;
import io.tamatu.springbootwebfluxdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    CustomerDao dao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customersFlux = dao.getCustomersFlux();

        return ServerResponse.ok().body(customersFlux, Customer.class);
    }
}