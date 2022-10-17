package space.millennium.falcon.odd.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MillenniumFalconConfigurationProperties(int autonomy,
                                                      String departure,
                                                      String arrival,
                                                      @JsonProperty("routes_db") String routesDb) {
}
