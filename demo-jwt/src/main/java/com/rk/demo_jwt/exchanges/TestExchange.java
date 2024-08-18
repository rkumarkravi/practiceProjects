package com.rk.demo_jwt.exchanges;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;

import java.util.Map;

@HttpExchange(url = "/users", accept = "application/json", contentType = "application/json")
public interface TestExchange {
    public record User(Long id, String name, String username, String email) {}
    @GetExchange("/")
    Flux<User> getAll();
}

