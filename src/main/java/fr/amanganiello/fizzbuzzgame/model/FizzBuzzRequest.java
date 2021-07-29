package fr.amanganiello.fizzbuzzgame.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class FizzBuzzRequest {

    @NotNull
    @Min(1)
    private Integer multiple1;

    @NotNull
    @Min(1)
    private Integer multiple2;

    @NotNull
    @Min(1)
    private Integer limit;

    @NotNull
    @NotBlank
    private String substitutionWordForMultiple1;

    @NotNull
    @NotBlank
    private String substitutionWordForMultiple2;

    public FizzBuzzRequest() {
        // for serialization
    }

    public Integer getMultiple1() {
        return multiple1;
    }

    public void setMultiple1(Integer multiple1) {
        this.multiple1 = multiple1;
    }

    public Integer getMultiple2() {
        return multiple2;
    }

    public void setMultiple2(Integer multiple2) {
        this.multiple2 = multiple2;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSubstitutionWordForMultiple1() {
        return substitutionWordForMultiple1;
    }

    public void setSubstitutionWordForMultiple1(String substitutionWordForMultiple1) {
        this.substitutionWordForMultiple1 = substitutionWordForMultiple1;
    }

    public String getSubstitutionWordForMultiple2() {
        return substitutionWordForMultiple2;
    }

    public void setSubstitutionWordForMultiple2(String substitutionWordForMultiple2) {
        this.substitutionWordForMultiple2 = substitutionWordForMultiple2;
    }

    @Override
    public String toString() {
        return "{" +
                "multiple1=" + multiple1 +
                ", multiple2=" + multiple2 +
                ", limit=" + limit +
                ", substitutionWordForMultiple1='" + substitutionWordForMultiple1 + '\'' +
                ", substitutionWordForMultiple2='" + substitutionWordForMultiple2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FizzBuzzRequest that = (FizzBuzzRequest) o;
        return Objects.equals(multiple1, that.multiple1) && Objects.equals(multiple2, that.multiple2) && Objects.equals(limit, that.limit) && Objects.equals(substitutionWordForMultiple1, that.substitutionWordForMultiple1) && Objects.equals(substitutionWordForMultiple2, that.substitutionWordForMultiple2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(multiple1, multiple2, limit, substitutionWordForMultiple1, substitutionWordForMultiple2);
    }
}
