package fr.amanganiello.fizzbuzzgame.service;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;
import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequestStat;

import java.util.Optional;

public interface StatsService {
    void addRequestToStat(FizzBuzzRequest fizzBuzzRequest);
    Optional<FizzBuzzRequestStat> getMostUsedRequest();
}
