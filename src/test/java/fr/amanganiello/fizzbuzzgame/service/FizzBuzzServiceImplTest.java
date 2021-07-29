package fr.amanganiello.fizzbuzzgame.service;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;
import fr.amanganiello.fizzbuzzgame.service.internal.FizzBuzzServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FizzBuzzServiceImplTest {

    private final FizzBuzzServiceImpl fizzBuzzService = new FizzBuzzServiceImpl();

    @Test
    void should_replace_int_with_words() {
        // setup
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setMultiple1(2);
        request.setMultiple2(5);
        request.setLimit(10);
        request.setSubstitutionWordForMultiple1("fizz");
        request.setSubstitutionWordForMultiple2("buzz");

        // test
        String result = fizzBuzzService.buildFizzBuzzResponse(request);

        // assert
        Assertions.assertThat(result).isEqualTo("1,fizz,3,fizz,buzz,fizz,7,fizz,9,fizzbuzz");
    }
}
