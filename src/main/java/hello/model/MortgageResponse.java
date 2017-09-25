package hello.model;

public class MortgageResponse {
    private boolean feasible;
    private double monthly_cost;

    public MortgageResponse(boolean feasible, double monthly_cost) {
        this.feasible = feasible;
        this.monthly_cost = monthly_cost;
    }

    public boolean isFeasible() {
        return feasible;
    }

    public void setFeasible(boolean feasible) {
        this.feasible = feasible;
    }

    public double getMonthly_cost() {
        return monthly_cost;
    }

    public void setMonthly_cost(double monthly_cost) {
        this.monthly_cost = monthly_cost;
    }
}
