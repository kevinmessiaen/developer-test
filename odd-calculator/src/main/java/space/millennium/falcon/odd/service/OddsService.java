package space.millennium.falcon.odd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import space.millennium.falcon.odd.dto.EmpireDto;
import space.millennium.falcon.odd.configuration.MillenniumFalconConfigurationProperties;
import space.millennium.falcon.odd.repository.RouteRepository;
import space.millennium.falcon.odd.calculator.Galaxy;
import space.millennium.falcon.odd.calculator.OddCalculator;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OddsService {

    private final MillenniumFalconConfigurationProperties millenniumFalconConfigurationProperties;
    private final RouteRepository routeRepository;

    @Autowired
    public OddsService(final MillenniumFalconConfigurationProperties millenniumFalconConfigurationProperties,
                       final RouteRepository routeRepository) {
        this.millenniumFalconConfigurationProperties = millenniumFalconConfigurationProperties;
        this.routeRepository = routeRepository;
    }

    /**
     * Get the galaxy with mapping of planets and routes
     *
     * @return The current s {@link Galaxy}
     */
    @Transactional(readOnly = true)
    public Galaxy getGalaxy() {
        Galaxy galaxy = new Galaxy();
        routeRepository.findAll().forEach(galaxy::addRoute);
        return galaxy;
    }


    /**
     * Calculate the odds for the Millennium Falcon to reach Endor based on the empire plan
     *
     * @param empireDto Plan of the empire
     * @return The odds ranging between 0.0 and 1.0
     */
    public double calculateOdds(final EmpireDto empireDto) {
        OddCalculator departure = OddCalculator.builder()
                .galaxy(getGalaxy())
                .empire(empireDto)
                .gas(millenniumFalconConfigurationProperties.autonomy())
                .millenniumFalconConfiguration(millenniumFalconConfigurationProperties)
                .position(millenniumFalconConfigurationProperties.departure())
                .encounters(empireDto.calculateEncounters(millenniumFalconConfigurationProperties.departure(), 0))
                .build();

        double best = 0.0;
        Set<OddCalculator> exploredRoutes = Collections.singleton(departure);

        while (!exploredRoutes.isEmpty()) {
            double currentBest = best;
            // Breadth first search algorithm
            exploredRoutes = exploredRoutes.stream()
                    .flatMap(path -> path.calculateNextSteps(currentBest).stream())
                    .collect(Collectors.toSet());

            double newBest = exploredRoutes.stream()
                    .filter(OddCalculator::isArrived)
                    .mapToDouble(OddCalculator::getOdds)
                    .max()
                    .orElse(0.0);

            best = Math.max(best, newBest);
        }

        return best;
    }

}
