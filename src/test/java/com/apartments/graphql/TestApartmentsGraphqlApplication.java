package com.apartments.graphql;

import org.springframework.boot.SpringApplication;

public class TestApartmentsGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.from(ApartmentsGraphqlApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
