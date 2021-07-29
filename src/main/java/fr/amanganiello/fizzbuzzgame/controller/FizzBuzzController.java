package fr.amanganiello.fizzbuzzgame.controller;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;
import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequestStat;
import fr.amanganiello.fizzbuzzgame.service.FizzBuzzService;
import fr.amanganiello.fizzbuzzgame.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/game/fizzbuzz")
@Validated
public class FizzBuzzController {

    private final FizzBuzzService fizzBuzzService;
    private final StatsService statsService;

    public FizzBuzzController(FizzBuzzService fizzBuzzService, StatsService statsService) {
        this.fizzBuzzService = fizzBuzzService;
        this.statsService = statsService;
    }

    @GetMapping
    public ResponseEntity<String> getFizzBuzz(@Valid FizzBuzzRequest request) {
        statsService.addRequestToStat(request);
        return ResponseEntity.ok(fizzBuzzService.buildFizzBuzzResponse(request));
    }

    @GetMapping("/stats")
    public ResponseEntity<String> getFizzBuzzStats() {
        Optional<FizzBuzzRequestStat> mostUsedRequest = statsService.getMostUsedRequest();
        if (mostUsedRequest.isPresent()) {
            FizzBuzzRequestStat fizzBuzzRequestStat = mostUsedRequest.get();
            String result = String.format("The most used request is %s with %d call(s)", fizzBuzzRequestStat.getRequest(), fizzBuzzRequestStat.getNbCalls());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No most used request founded");
        }
    }


}
