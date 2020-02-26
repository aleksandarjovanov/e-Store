package mk.ukim.finki.emit.sports_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ServletComponentScan
public class SportsShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsShopApplication.class, args);
    }

}
