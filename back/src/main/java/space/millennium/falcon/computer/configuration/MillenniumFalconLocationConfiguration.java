package space.millennium.falcon.computer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.millennium.falcon.odd.configuration.MillenniumFalconLocationProperties;


@Configuration
public class MillenniumFalconLocationConfiguration {

    @Value("${millennium-falcon.location}")
    public String jsonLocation;

    @Bean
    public MillenniumFalconLocationProperties millenniumFalconLocationProperties() {
        return new MillenniumFalconLocationProperties(jsonLocation);
    }

}
