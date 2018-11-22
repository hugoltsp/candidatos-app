package com.teles.candidaturas.vagas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VagasApi {

    public static void main(String... args) {
        SpringApplication.run(VagasApi.class, args);
    }

}
