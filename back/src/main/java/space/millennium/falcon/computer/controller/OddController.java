package space.millennium.falcon.computer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import space.millennium.falcon.odd.dto.EmpireDto;
import space.millennium.falcon.odd.service.OddsService;

import java.io.IOException;

@RestController
@RequestMapping("/odds")
public class OddController {

    private final ObjectMapper objectMapper;
    private final OddsService oddsService;

    @Autowired
    public OddController(ObjectMapper objectMapper, OddsService oddsService) {
        this.objectMapper = objectMapper;
        this.oddsService = oddsService;
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    private double calculateOdds(final MultipartFile files) throws IOException {
        return oddsService.calculateOdds(objectMapper.readValue(files.getBytes(), EmpireDto.class));
    }

}
