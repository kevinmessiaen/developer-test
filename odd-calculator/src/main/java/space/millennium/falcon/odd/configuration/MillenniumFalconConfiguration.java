package space.millennium.falcon.odd.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Configuration
public class MillenniumFalconConfiguration {

    @Bean
    public MillenniumFalconConfigurationProperties millenniumFalconConfigurationDto(final MillenniumFalconLocationProperties millenniumFalconLocationProperties,
                                                                                    final ObjectMapper objectMapper) throws IOException {
        final File jsonConfigurationFile = Path.of(millenniumFalconLocationProperties.location()).toFile();
        return objectMapper.readValue(jsonConfigurationFile, MillenniumFalconConfigurationProperties.class);
    }

}
