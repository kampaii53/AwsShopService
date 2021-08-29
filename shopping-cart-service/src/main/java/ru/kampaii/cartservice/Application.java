package ru.kampaii.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;

@SpringBootApplication
@EnableRdsInstance(dbInstanceIdentifier="cart-service-db", databaseName="${cloud.aws.rds.cart-service-db.databaseName}",
        username="${cloud.aws.rds.cart-service-db.username}", password="${cloud.aws.rds.cart-service-db.password}")
@EntityScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
