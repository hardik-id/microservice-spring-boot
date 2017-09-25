package hello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class InterestRates {

    private int maturityPeriod;
    private double interestRate;
    @JsonIgnore
    private Date lastUpdated;

    public InterestRates(int maturityPeriod, double interestRate, Date lastUpdated) {
        this.maturityPeriod = maturityPeriod;
        this.interestRate = interestRate;
        this.lastUpdated = lastUpdated;
    }

    public int getMaturityPeriod() {
        return maturityPeriod;
    }

    public void setMaturityPeriod(int maturityPeriod) {
        this.maturityPeriod = maturityPeriod;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "InterestRates{" +
                "maturityPeriod=" + maturityPeriod +
                ", interestRate=" + interestRate +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
