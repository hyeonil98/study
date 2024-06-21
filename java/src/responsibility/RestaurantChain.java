package responsibility;

import java.util.List;

public class RestaurantChain implements Calculable{
    // 역할에 의존
    private List<Calculable> stores;
    @Override
    public long calculateRevenue() {
        long revenue = 0L;
        for (Calculable store : stores) {
            revenue += store.calculateRevenue();
        }
        return revenue;
    }

    @Override
    public long calculateProfit() {
        long income = 0;
        for (Calculable store : stores) {
            income += store.calculateProfit();
        }
        return income;
    }
}
