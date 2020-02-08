package com.telran.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class UsersApplication {

    @GetMapping("/")
    public String hello(){
        return "Hello from Users";
    }

    @GetMapping("/userid")
    public String getId(){
        return "User id";
    }
    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

}
