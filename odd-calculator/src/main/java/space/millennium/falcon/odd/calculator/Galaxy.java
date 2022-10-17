package space.millennium.falcon.odd.calculator;

import lombok.Getter;
import space.millennium.falcon.odd.entity.Route;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Galaxy {

    private final Map<String, Planet> planets = new HashMap<>();
    public void addRoute(Route route) {
        Planet origin = planets.computeIfAbsent(route.getRouteId().getOrigin(), Planet::new);
        Planet destination = planets.computeIfAbsent(route.getRouteId().getDestination(), Planet::new);

        origin.addRoute(destination, route.getTravelTime());
        destination.addRoute(origin, route.getTravelTime());
    }

}
