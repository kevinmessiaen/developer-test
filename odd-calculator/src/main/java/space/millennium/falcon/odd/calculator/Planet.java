package space.millennium.falcon.odd.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Planet {

    private final String name;
    private final Map<String, Integer> routes = new HashMap<>();

    protected void addRoute(Planet destination, int travelTime) {
        routes.put(destination.name, travelTime);
    }

}
