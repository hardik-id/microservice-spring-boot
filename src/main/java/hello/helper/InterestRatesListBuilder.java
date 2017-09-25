package hello.helper;

import hello.model.InterestRates;
import hello.model.InterestRatesListModel;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
public class InterestRatesListBuilder {
    private Observable<InterestRatesListModel> interestRatesListModelObservable;

    public InterestRatesListBuilder(){
        InterestRatesListModel interestRatesListModel = new InterestRatesListModel();
        InterestRates interestRate1 = new InterestRates(120, 5, new Date());
        InterestRates interestRate2 = new InterestRates(180, 4, new Date());
        InterestRates interestRate3 = new InterestRates(240, 4, new Date());
        InterestRates interestRate4 = new InterestRates(300, 3.5, new Date());

        ArrayList<InterestRates> interestRates =
                new ArrayList<>(Arrays.asList(interestRate1, interestRate2, interestRate3,interestRate4));
        interestRatesListModel.setInterestRates(interestRates);
        interestRatesListModelObservable = Observable.just(interestRatesListModel);
    }

    public Observable<InterestRatesListModel> getInterestRatesListModelObservable() {
        return interestRatesListModelObservable;
    }
}
