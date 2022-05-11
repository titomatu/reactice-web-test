package io.tamatu.springbootwebfluxdemo.router;

import io.tamatu.springbootwebfluxdemo.handler.CustomerHandler;
import io.tamatu.springbootwebfluxdemo.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers) //functional endpoint
                //n-number of endpoints PUT, GET, POST...
                .GET("/router/customers/{input}", handler::findCustomer)
                .POST("/router/customers", handler::saveCustomer)
                .GET("/router/customers/stream", streamHandler::getCustomers) //stream
                .build();
    }
}
