package space.millennium.falcon.odd.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import space.millennium.falcon.odd.dto.EmpireDto;

import java.util.Collections;
import java.util.stream.Stream;

class OddCalculatorTest {

    @Test
    void getOdds_zero_timesUp() {
        // Given
        OddCalculator oddCalculator = OddCalculator.builder()
                .currentDay(10)
                .empire(new EmpireDto(6, Collections.emptyList()))
                .build();

        // When
        double actual = oddCalculator.getOdds();

        // Then
        Assertions.assertEquals(0.0, actual, 0.0);
    }

    @ParameterizedTest
    @MethodSource("cumulativeOdds")
    void getOdds_cumulative(int encounters, double odds) {
        // Given
        OddCalculator oddCalculator = OddCalculator.builder()
                .encounters(encounters)
                .currentDay(4)
                .empire(new EmpireDto(6, Collections.emptyList()))
                .build();

        // When
        double actual = oddCalculator.getOdds();

        // Then
        Assertions.assertEquals(odds, actual, 0.0001);
    }

    private static Stream<Arguments> cumulativeOdds() {
        return Stream.of(
                Arguments.arguments(0, 1.0),
                Arguments.arguments(1, 0.9),
                Arguments.arguments(2, 0.81),
                Arguments.arguments(3, 0.729)
        );
    }

}
