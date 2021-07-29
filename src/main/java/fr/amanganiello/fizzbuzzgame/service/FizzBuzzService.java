package fr.amanganiello.fizzbuzzgame.service;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;

public interface FizzBuzzService {
    String buildFizzBuzzResponse(FizzBuzzRequest request);
}
