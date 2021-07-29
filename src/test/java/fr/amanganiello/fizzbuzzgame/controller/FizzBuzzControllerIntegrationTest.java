package fr.amanganiello.fizzbuzzgame.controller;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequestStat;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FizzBuzzControllerIntegrationTest {

    public static final String MULTIPLE_1 = "multiple1";
    public static final String MULTIPLE_2 = "multiple2";
    public static final String LIMIT = "limit";
    public static final String SUBSTITUTION_WORD_FOR_MULTIPLE_1 = "substitutionWordForMultiple1";
    public static final String SUBSTITUTION_WORD_FOR_MULTIPLE_2 = "substitutionWordForMultiple2";

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
        assertThat(response).isEmpty();
    }

    @Test
    void should_return_stats_of_most_used_request() {
        // setup
        buildRequestSpecification().when().get(BASE_URL + port + FIZZ_BUZZ_ENDPOINT);

        // test
        FizzBuzzRequestStat response = when().get(BASE_URL + port + FIZZ_BUZZ_ENDPOINT + "/stats")
                .then()
                .statusCode(200)
                .extract().as(FizzBuzzRequestStat.class);

        // assert
        assertThat(response.getNbCalls()).isEqualTo(1);
        assertThat(response.getRequest()).isNotNull();
        assertThat(response.getRequest().getMultiple1()).isEqualTo(3);
        assertThat(response.getRequest().getMultiple2()).isEqualTo(5);
        assertThat(response.getRequest().getLimit()).isEqualTo(15);
        assertThat(response.getRequest().getSubstitutionWordForMultiple1()).isEqualTo("fizz");
        assertThat(response.getRequest().getSubstitutionWordForMultiple2()).isEqualTo("buzz");
    }

    @Test
    void should_return_error_when_multiples_are_wrong() {
        // setup
        RequestSpecification wrongRequest = given()
                .queryParam(MULTIPLE_1, "0")
                .queryParam(MULTIPLE_2, "0")
                .queryParam(LIMIT, "15")
                .queryParam(SUBSTITUTION_WORD_FOR_MULTIPLE_1, "fizz")
                .queryParam(SUBSTITUTION_WORD_FOR_MULTIPLE_2, "buzz");


        // test & assert
        wrongRequest.when().get(BASE_URL + port + FIZZ_BUZZ_ENDPOINT)
                .then()
                .contentType(JSON)
                .statusCode(400);

    }

    private RequestSpecification buildRequestSpecification() {
        return given()
                .queryParam(MULTIPLE_1, "3")
                .queryParam(MULTIPLE_2, "5")
                .queryParam(LIMIT, "15")
                .queryParam(SUBSTITUTION_WORD_FOR_MULTIPLE_1, "fizz")
                .queryParam(SUBSTITUTION_WORD_FOR_MULTIPLE_2, "buzz");
    }

}
