package space.millennium.falcon.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import space.millennium.falcon.cli.configuration.MillenniumFalconLocationConfiguration;
import space.millennium.falcon.odd.configuration.EnableOddCalculator;
import space.millennium.falcon.odd.dto.EmpireDto;
import space.millennium.falcon.odd.service.OddsService;

import java.io.IOException;
import java.nio.file.Path;

@SpringBootApplication
@EnableOddCalculator
public class CliApplication implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final OddsService oddsService;

    @Autowired
    public CliApplication(ObjectMapper objectMapper, OddsService oddsService) {
        this.objectMapper = objectMapper;
        this.oddsService = oddsService;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: r2d2 path-to-millennium-falcon.json path-to-empire.json");
            System.exit(-1);
        }

        MillenniumFalconLocationConfiguration.JSON_LOCATION = args[0];

        SpringApplication.run(CliApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        System.out.println(100 * oddsService.calculateOdds(objectMapper.readValue(Path.of(args[1]).toFile(), EmpireDto.class)));
    }

}
