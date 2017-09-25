package hello.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import hello.helper.InterestRatesListBuilder;
import hello.model.InterestRates;
import hello.model.InterestRatesListModel;
import hello.model.MortgageRequest;
import hello.model.MortgageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Service
public class MortgageService {

    @Autowired
    public InterestRatesListBuilder interestRatesListBuilder;


    @HystrixCommand(fallbackMethod = "reliable")
    public Observable<InterestRatesListModel> getInterestRatesList() {
        Observable<InterestRatesListModel> interestRatesListModel = interestRatesListBuilder.getInterestRatesListModelObservable();
      /*if(true){
        throw new RuntimeException();
      }*/

        return interestRatesListModel;
    }

    public Observable<InterestRatesListModel> reliable() {
        InterestRatesListModel interestRatesListModel = new InterestRatesListModel();
        ArrayList<InterestRates> interestRates = new ArrayList<>(Arrays.asList(new InterestRates(0, 0, new Date())));
        interestRatesListModel.setInterestRates(interestRates);
        return Observable.just(interestRatesListModel);
    }


    public MortgageResponse calculateMorgage(MortgageRequest mortgageRequest) {
        MortgageResponse response = new MortgageResponse(false, 0);
        double loanValue = mortgageRequest.getLoanValue();
        if (mortgageRequest.getIncome() * 4 <= loanValue ||
                loanValue > mortgageRequest.getHomeValue()) {
            return response;
        } else {
            response.setFeasible(true);
            double monthlyCost = (double) this.calcluateMonthlyAmout(loanValue, mortgageRequest.getMaturityPeriod()).getResult();
            response.setMonthly_cost(monthlyCost);
            return response;
        }
    }

    private DeferredResult<Double> calcluateMonthlyAmout(double loanValue, int maturityPeriod) {
        DeferredResult<Double> result = new DeferredResult<Double>();

        Observable<InterestRatesListModel> interestRatesListModelObservable = interestRatesListBuilder.getInterestRatesListModelObservable();
        interestRatesListModelObservable.subscribe((interestRatesListModel -> {
            interestRatesListModel.getInterestRates().forEach(
                    interestRates -> {
                        if (interestRates.getMaturityPeriod() == maturityPeriod) {
                            double interestRate = interestRates.getInterestRate();
                            result.setResult((loanValue + (loanValue * interestRate / 100)) / maturityPeriod);
                        }
                    }
            );
        }));
        return result;
    }
}
