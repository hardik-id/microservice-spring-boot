package hello.model;

import java.util.List;

public class InterestRatesListModel {

    private List<InterestRates> interestRates;

    public List<InterestRates> getInterestRates() {
        return this.interestRates;
    }

    public void setInterestRates(List<InterestRates> interestRates) {
        this.interestRates = interestRates;
    }

    @Override
    public String toString() {
        return "InterestRatesListModel{" +
                "interestRates=" + interestRates +
                '}';
    }

}
