package fr.amanganiello.fizzbuzzgame.service.internal;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;
import fr.amanganiello.fizzbuzzgame.service.FizzBuzzService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FizzBuzzServiceImpl implements FizzBuzzService {

    public static final String COMMA_DELIMITER = ",";

    @Override
    public String buildFizzBuzzResponse(FizzBuzzRequest request) {
        return IntStream.rangeClosed(1, request.getLimit())
                .mapToObj(value -> replaceIntBySubstitutionWord(value, request))
                .collect(Collectors.joining(COMMA_DELIMITER));
    }

    private String replaceIntBySubstitutionWord(int value, FizzBuzzRequest request) {
        boolean isValueMultipleOfFirstMultiple = isMultipleOf(value, request.getMultiple1());
        boolean isValueMultipleOfSecondMultiple = isMultipleOf(value, request.getMultiple2());

        if (isValueMultipleOfFirstMultiple && isValueMultipleOfSecondMultiple) {
            return request.getSubstitutionWordForMultiple1().concat(request.getSubstitutionWordForMultiple2());
        } else if (isValueMultipleOfFirstMultiple) {
            return request.getSubstitutionWordForMultiple1();
        } else if (isValueMultipleOfSecondMultiple) {
            return request.getSubstitutionWordForMultiple2();
        } else {
            return String.valueOf(value);
        }
    }

    private static boolean isMultipleOf(int value, Integer multiple) {
        return value % multiple == 0;
    }
}
