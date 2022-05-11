package io.tamatu.springbootwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
    /*
    1- Suscribe to Publisher
    2- Publisher sends Suscription event
    3- Suscriber Request(n) call
    4- onNext(data) will be published from the publisher
    5- onComplete() or on Error() from the publisher
     */

    @Test
    public void testMono(){
        Mono<String> mono = Mono.just("Hello World").log();//Publisher

        mono.subscribe(System.out::println);//Suscriber
    }

    @Test
    public void testFlux(){
        Flux<String> flux = Flux.just("Winter", "Spring", "Summer", "Fall") //Publisher
                .concatWithValues("Snowfall")
                .log();

        flux.subscribe(System.out::println);//Suscriber
    }
}
