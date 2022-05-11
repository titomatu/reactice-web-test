package io.tamatu.springbootwebfluxdemo.dao;

import io.tamatu.springbootwebfluxdemo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int i){
        //To make a synchoronous and blocking demostration
        //this will be the publisher and the brower or insomnia the suscriber
        // a million of record doesnt need to sleep a thread
        // until it proceses all the record the suscriber won't be able to fetch all the response
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 50)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("processing count : " + i))
                .mapToObj(i->new Customer(i, "customer"+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i->new Customer(i, "customer"+i));
    }

    public Flux<Customer> getCustomersFlux() {
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i->new Customer(i, "customer"+i));
    }
}
