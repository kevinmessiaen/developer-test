package space.millennium.falcon.odd.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import space.millennium.falcon.odd.service.OddsService;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({MillenniumFalconConfiguration.class, SqliteDatasourceConfiguration.class, OddsService.class})
@EntityScan(basePackages = "space.millennium.falcon.odd")
@EnableJpaRepositories(basePackages  = "space.millennium.falcon.odd")
@Configuration
public @interface EnableOddCalculator {
}
