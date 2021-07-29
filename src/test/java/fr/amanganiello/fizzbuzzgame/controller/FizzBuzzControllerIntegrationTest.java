package fr.amanganiello.fizzbuzzgame.controller;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FizzBuzzControllerIntegrationTest {

    @LocalServerPort
    private int port;
    private final static String BASE_URL = "http://localhost:";
    private final static String FIZZ_BUZZ_ENDPOINT = "/game/fizzbuzz";

    @Test
    void should_return_200_with_list_of_strings() {
        // setup
        RequestSpecification requestSpecification = buildRequestSpecification();

        // test
        String response = requestSpecification
                .when().get(BASE_URL + port + FIZZ_BUZZ_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().asString();

        // assert
        assertThat(response).isEqualTo("1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz");
    }

    @Test
    void should_return_404_with_no_stats() {
        // test
        String response = when().get(BASE_URL + port + FIZZ_BUZZ_ENDPOINT + "/stats")
                .then()
                .statusCode(404)
                .extract().asString();

        // assert
        assertThat(response).isEqualTo("No most used request founded");
    }

    @Test
    void should_return_stats_of_most_used_request() {
        // setup
        buildRequestSpecification().when().get(BASE_URL + port + FIZZ_BUZZ_ENDPOINT);

        // test
        String response = when().get(BASE_URL + port + FIZZ_BUZZ_ENDPOINT + "/stats")
                .then()
                .statusCode(200)
                .extract().asString();

        // assert
        assertThat(response)
                .isEqualTo("The most used request is Request {multiple1=3, multiple2=5, limit=15, substitutionWordForMultiple1='fizz', substitutionWordForMultiple2='buzz'} with 1 call(s)");
    }

    private RequestSpecification buildRequestSpecification() {
        return given()
                .queryParam("multiple1", "3")
                .queryParam("multiple2", "5")
                .queryParam("limit", "15")
                .queryParam("substitutionWordForMultiple1", "fizz")
                .queryParam("substitutionWordForMultiple2", "buzz");
    }

}
