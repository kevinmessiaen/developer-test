package space.millennium.falcon.computer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import space.millennium.falcon.odd.configuration.EnableOddCalculator;

@SpringBootApplication
@EnableOddCalculator
public class ComputerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerApplication.class, args);
    }

}
