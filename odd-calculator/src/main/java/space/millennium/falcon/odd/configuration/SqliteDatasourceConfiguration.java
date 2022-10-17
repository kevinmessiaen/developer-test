package space.millennium.falcon.odd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.nio.file.Path;

@Configuration
public class SqliteDatasourceConfiguration {

    public static final String SQL_LITE_DRIVER = "org.sqlite.JDBC";
    private static final String SQL_LITE_URL_PATTERN = "jdbc:sqlite:%s";

    @Bean
    public DriverManagerDataSource dataSource(final MillenniumFalconLocationProperties millenniumFalconLocationProperties,
                                              MillenniumFalconConfigurationProperties millenniumFalconConfigurationProperties) {
        Path dbPath = Path.of(millenniumFalconLocationProperties.location())
                .getParent()
                .resolve(Path.of(millenniumFalconConfigurationProperties.routesDb()));

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(SQL_LITE_DRIVER);
        dataSource.setUrl(String.format(SQL_LITE_URL_PATTERN, dbPath));

        return dataSource;
    }

}
