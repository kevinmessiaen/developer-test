package space.millennium.falcon.odd.calculator;

import lombok.Builder;
import lombok.Data;
import space.millennium.falcon.odd.dto.EmpireDto;
import space.millennium.falcon.odd.configuration.MillenniumFalconConfigurationProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class OddCalculator {

    private final long encounters;
    private final MillenniumFalconConfigurationProperties millenniumFalconConfiguration;
    private final EmpireDto empire;
    private final Galaxy galaxy;
    private final int gas;
    private final int currentDay;
    private final String position;

    /**
     * Return the success rate to arrive at the current position using the current path
     *
     * @return Success rate ranging from 0.0 to 1.0
     */
    public double getOdds() {
        if (currentDay > empire.countdown()) {
            return 0.0;
        } else {
            return 1 - LongStream.range(0, encounters)
                    .mapToDouble(i -> Math.pow(9, i) / Math.pow(10, i + 1))
                    .sum();
        }
    }

    private OddCalculator refill() {
        return this.toBuilder()
                .currentDay(currentDay + 1)
                .gas(millenniumFalconConfiguration.autonomy())
                .encounters(encounters + empire.calculateEncounters(position, currentDay + 1))
                .build();
    }

    private List<String> availableDestination() {
        Planet current = galaxy.getPlanets().get(position);

        return current.getRoutes().entrySet().stream()
                .filter(entry -> entry.getValue() <= gas)
                .map(Map.Entry::getKey)
                .toList();
    }

    private OddCalculator travel(String destination) {
        Planet current = galaxy.getPlanets().get(position);
        int travelTime = current.getRoutes().get(destination);

        return this.toBuilder()
                .currentDay(currentDay + travelTime)
                .gas(gas - travelTime)
                .encounters(encounters + empire.calculateEncounters(destination, currentDay + travelTime))
                .position(destination)
                .build();
    }

    /**
     * Explore all the available route including refilling/resting
     *
     * @param currentBestOdd The best odd found used to filter routes that are not optimal
     * @return The list of potential routes to explore
     */
    public List<OddCalculator> calculateNextSteps(double currentBestOdd) {
        return Stream.concat(
                        Stream.of(refill()),
                        availableDestination().stream().map(this::travel)
                ).filter(dest -> dest.getOdds() > currentBestOdd)
                .toList();
    }

    public boolean isArrived() {
        return this.millenniumFalconConfiguration.arrival().equals(position);
    }

}
