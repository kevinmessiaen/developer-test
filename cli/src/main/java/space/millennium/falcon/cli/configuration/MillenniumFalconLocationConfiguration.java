package space.millennium.falcon.cli.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.millennium.falcon.odd.configuration.MillenniumFalconLocationProperties;

@Configuration
public class MillenniumFalconLocationConfiguration {

    public static String JSON_LOCATION;
    @Bean
    public MillenniumFalconLocationProperties millenniumFalconLocationProperties() {
        return new MillenniumFalconLocationProperties(JSON_LOCATION);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
