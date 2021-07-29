package fr.amanganiello.fizzbuzzgame.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;
import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequestStat;
import fr.amanganiello.fizzbuzzgame.service.FizzBuzzService;
import fr.amanganiello.fizzbuzzgame.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/game/fizzbuzz")
@Validated
public class FizzBuzzController {

    private static final String START_INCOMING_REQ_LOG = "----- INCOMING REQUEST ----";
    private static final String END_LOG = "-----------------";
    private final FizzBuzzService fizzBuzzService;
    private final StatsService statsService;

    Logger logger = LoggerFactory.getLogger(FizzBuzzController.class);

    public FizzBuzzController(FizzBuzzService fizzBuzzService, StatsService statsService) {
        this.fizzBuzzService = fizzBuzzService;
        this.statsService = statsService;
    }

    @GetMapping
    public ResponseEntity<String> getFizzBuzz(@Valid FizzBuzzRequest request) throws JsonProcessingException {
        logRequest(request);
        statsService.addRequestToStat(request);
        return ResponseEntity.ok(fizzBuzzService.buildFizzBuzzResponse(request));
    }

    private void logRequest(FizzBuzzRequest request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestToJson = objectMapper.writeValueAsString(request);
        Stream.of(START_INCOMING_REQ_LOG, requestToJson, END_LOG).forEach(logger::info);
    }

    @GetMapping("/stats")
    public ResponseEntity<FizzBuzzRequestStat> getFizzBuzzStats() {
        Optional<FizzBuzzRequestStat> mostUsedRequest = statsService.getMostUsedRequest();
        return mostUsedRequest
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


}
