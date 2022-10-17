package space.millennium.falcon.odd.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import space.millennium.falcon.odd.dto.BountyHunterDto;
import space.millennium.falcon.odd.dto.EmpireDto;
import space.millennium.falcon.odd.configuration.MillenniumFalconConfigurationProperties;
import space.millennium.falcon.odd.entity.Route;
import space.millennium.falcon.odd.repository.RouteRepository;

import java.util.List;
import java.util.stream.Stream;

class OddCalculatorServiceTest {
    public static final String HOTH = "Hoth";
    public static final String TATOOINE = "Tatooine";
    public static final String ENDOR = "Endor";
    public static final String DAGOBAH = "Dagobah";

    public static final MillenniumFalconConfigurationProperties MILLENNIUM_FALCON_CONFIGURATION_DTO =
            new MillenniumFalconConfigurationProperties(6, TATOOINE, ENDOR, "no.db");
    public static final List<Route> ROUTES = routes();

    private RouteRepository routeRepository;

    @InjectMocks
    private OddsService oddsService;

    @BeforeEach
    void setUp() {
        routeRepository = Mockito.mock(RouteRepository.class);

        oddsService = new OddsService(MILLENNIUM_FALCON_CONFIGURATION_DTO,
                routeRepository);
    }

    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(routeRepository);
    }

    @ParameterizedTest
    @MethodSource("oddsBasedOnDays")
    void calculateOdds(int days, double odd) {
        // Given
        EmpireDto empireDto = new EmpireDto(days, List.of(
                new BountyHunterDto(HOTH, 6),
                new BountyHunterDto(HOTH, 7),
                new BountyHunterDto(HOTH, 8)
        ));

        Mockito.when(routeRepository.findAll())
                .thenReturn(ROUTES);

        // When
        double actual = oddsService.calculateOdds(empireDto);

        // Then
        Assertions.assertEquals(odd, actual, 0.0);

        Mockito.verify(routeRepository, Mockito.times(1)).findAll();
    }

    private static Stream<Arguments> oddsBasedOnDays() {
        return Stream.of(
                Arguments.arguments(7, 0.0),
                Arguments.arguments(8, 0.81),
                Arguments.arguments(9, 0.9),
                Arguments.arguments(10, 1.0)
        );
    }

    private static List<Route> routes() {
        return List.of(
                Route.builder()
                        .routeId(Route.RouteId.builder()
                                .origin(TATOOINE)
                                .destination(DAGOBAH)
                                .build())
                        .travelTime(6)
                        .build(),
                Route.builder()
                        .routeId(Route.RouteId.builder()
                                .origin(DAGOBAH)
                                .destination(ENDOR)
                                .build())
                        .travelTime(4)
                        .build(),
                Route.builder()
                        .routeId(Route.RouteId.builder()
                                .origin(DAGOBAH)
                                .destination(HOTH)
                                .build())
                        .travelTime(1)
                        .build(),
                Route.builder()
                        .routeId(Route.RouteId.builder()
                                .origin(HOTH)
                                .destination(ENDOR)
                                .build())
                        .travelTime(1)
                        .build(),
                Route.builder()
                        .routeId(Route.RouteId.builder()
                                .origin(TATOOINE)
                                .destination(HOTH)
                                .build())
                        .travelTime(6)
                        .build()
        );
    }

}
