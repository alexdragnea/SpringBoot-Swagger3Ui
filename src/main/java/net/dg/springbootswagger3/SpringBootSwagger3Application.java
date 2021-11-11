package net.dg.springbootswagger3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "1.0", description = "Employees Information"))
public class SpringBootSwagger3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSwagger3Application.class, args);
    }


}
