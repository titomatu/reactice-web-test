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

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId = Integer.valueOf(request.pathVariable("input"));

        //dao.getCustomersFlux().filter(c->c.getId()==customerId).take(1).single();
        Mono<Customer> customerMono = dao.getCustomersFlux().filter(c->c.getId()==customerId).next();

        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);

        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());

        return ServerResponse.ok().body(saveResponse, String.class);
    }
}
