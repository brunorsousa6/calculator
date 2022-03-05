package br.com.erudio.springbootaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class SpringBootAwsApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootAwsApplication.class, args);
    }

}
