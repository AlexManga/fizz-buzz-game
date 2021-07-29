package fr.amanganiello.fizzbuzzgame.service.internal;

import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequest;
import fr.amanganiello.fizzbuzzgame.model.FizzBuzzRequestStat;
import fr.amanganiello.fizzbuzzgame.service.StatsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService {

    private final List<FizzBuzzRequestStat> fizzBuzzRequestStats;

    public StatsServiceImpl() {
        this.fizzBuzzRequestStats = new ArrayList<>();
    }

    @Override
    public void addRequestToStat(FizzBuzzRequest fizzBuzzRequest) {
        Optional<FizzBuzzRequestStat> stat = this.fizzBuzzRequestStats.stream()
                .filter(fizzBuzzRequestStat -> fizzBuzzRequestStat.getRequest().equals(fizzBuzzRequest))
                .findFirst();
        if (stat.isPresent()) {
            stat.get().incrementCountCall();
        } else {
            this.fizzBuzzRequestStats.add(new FizzBuzzRequestStat(fizzBuzzRequest));
        }
    }

    @Override
    public Optional<FizzBuzzRequestStat> getMostUsedRequest() {
        return this.fizzBuzzRequestStats.stream().max(Comparator.comparingInt(FizzBuzzRequestStat::getNbCalls));
    }

    public List<FizzBuzzRequestStat> getRequestStats() {
        return fizzBuzzRequestStats;
    }
}
