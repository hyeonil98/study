package responsibility;

import java.util.List;

public class Store implements Calculable{
    private List<Calculable> orders;
    private long rentalFee;


    @Override
    public long calculateRevenue() {
        return orders.stream().mapToLong(Calculable::calculateRevenue).sum();
    }

    @Override
    public long calculateProfit() {
        return orders.stream().mapToLong(Calculable::calculateProfit).sum() - rentalFee;
    }
    
}
