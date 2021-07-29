package fr.amanganiello.fizzbuzzgame.controller;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;
import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequestStat;
import fr.amanganiello.fizzbuzzgame.service.FizzBuzzService;
import fr.amanganiello.fizzbuzzgame.service.StatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FizzBuzzControllerTest {

    @InjectMocks
    private FizzBuzzController controller;

    @Mock
    private FizzBuzzService fizzBuzzService;

    @Mock
    private StatsService statsService;

    @Test
    void should_call_services_when_getFizzBuzz() {
        // setup
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setMultiple1(0);
        request.setMultiple2(5);
        request.setLimit(10);
        request.setSubstitutionWordForMultiple1("fizz");
        request.setSubstitutionWordForMultiple2("buzz");

        // test
        controller.getFizzBuzz(request);

        // assert
        verify(statsService).addRequestToStat(request);
        verify(fizzBuzzService).buildFizzBuzzResponse(request);
    }

    @Test
    void should_return_the_most_used_request_when_present() {
        // setup
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setMultiple1(2);
        request.setMultiple2(5);
        request.setLimit(10);
        request.setSubstitutionWordForMultiple1("fizz");
        request.setSubstitutionWordForMultiple2("buzz");

        FizzBuzzRequestStat requestStat = new FizzBuzzRequestStat(request);

        when(statsService.getMostUsedRequest()).thenReturn(Optional.of(requestStat));

        // test
        ResponseEntity<String> fizzBuzzStats = controller.getFizzBuzzStats();

        // assert
        assertThat(fizzBuzzStats.getBody()).isEqualTo("The most used request is {multiple1=2, multiple2=5, limit=10, substitutionWordForMultiple1='fizz', substitutionWordForMultiple2='buzz'} with 1 call(s)");
    }

    @Test
    void should_not_return_the_most_used_request_when_absent() {
        // setup
        when(statsService.getMostUsedRequest()).thenReturn(Optional.empty());

        // test
        ResponseEntity<String> fizzBuzzStats = controller.getFizzBuzzStats();

        // assert
        assertThat(fizzBuzzStats.getBody()).isEqualTo("No most used request founded");
    }

}
