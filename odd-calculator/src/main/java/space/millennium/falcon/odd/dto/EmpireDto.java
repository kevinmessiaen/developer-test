package space.millennium.falcon.odd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record EmpireDto(int countdown, @JsonProperty("bounty_hunters") List<BountyHunterDto> bountyHunters) {

    public long calculateEncounters(String planet, int day) {
        return bountyHunters.stream()
                .filter(bountyHunter -> bountyHunter.day() == day && bountyHunter.planet().equals(planet))
                .count();
    }
}
