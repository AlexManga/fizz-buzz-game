package fr.amanganiello.fizzbuzzgame.model;

public class FizzBuzzRequestStat {

    private int nbCalls;
    private final FizzBuzzRequest request;

    public FizzBuzzRequestStat(FizzBuzzRequest request) {
        this.nbCalls = 1;
        this.request = request;
    }

    // for serialization
    public FizzBuzzRequestStat() {
        this.request = new FizzBuzzRequest();
    }

    public int getNbCalls() {
        return nbCalls;
    }

    public FizzBuzzRequest getRequest() {
        return request;
    }

    public void incrementCountCall() {
        this.nbCalls++;
    }

}
