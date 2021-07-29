package fr.amanganiello.fizzbuzzgame.service;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;
import fr.amanganiello.fizzbuzzgame.service.internal.StatsServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatsServiceImplTest {

    private final StatsServiceImpl statsService = new StatsServiceImpl();

    @Test
    void should_increment_nb_calls() {
        // setup
        FizzBuzzRequest request = createFizzBuzzRequest();

        // test
        statsService.addRequestToStat(request);

        // assert
        assertThat(statsService.getRequestStats()).hasSize(1);
        assertThat(statsService.getRequestStats().get(0).getNbCalls()).isEqualTo(1);

        // test
        statsService.addRequestToStat(request);

        // assert
        assertThat(statsService.getRequestStats().get(0).getNbCalls()).isEqualTo(2);
    }

    @Test
    void should_create_new_request() {
        // setup
        FizzBuzzRequest request = createFizzBuzzRequest();
        FizzBuzzRequest request2 = createFizzBuzzRequest();
        request2.setLimit(8);

        // test
        statsService.addRequestToStat(request);
        statsService.addRequestToStat(request2);

        // assert
        assertThat(statsService.getRequestStats()).hasSize(2);
        assertThat(statsService.getRequestStats().get(0).getNbCalls()).isEqualTo(1);
        assertThat(statsService.getRequestStats().get(1).getNbCalls()).isEqualTo(1);
    }

    private FizzBuzzRequest createFizzBuzzRequest() {
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setMultiple1(0);
        request.setMultiple2(5);
        request.setLimit(10);
        request.setSubstitutionWordForMultiple1("fizz");
        request.setSubstitutionWordForMultiple2("buzz");
        return request;
    }

}
