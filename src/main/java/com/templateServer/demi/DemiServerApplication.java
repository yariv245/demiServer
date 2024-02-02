package com.templateServer.demi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info (
                title = "Demi server test ",
                description = "This service created using Eazybytes course"
        )
)
public class DemiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemiServerApplication.class, args);
    }

}
