package kata.ex01;

import kata.ex01.rate.DiscountRate;
import kata.ex01.model.HighwayDrive;
import java.util.List;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    private List<DiscountRate> discountRates;

    public DiscountServiceImpl(List<DiscountRate> discountRates) {
        this.discountRates = discountRates;
    }

    @Override
    public long calc(HighwayDrive drive) {
        int resultRate = 0;
        for (DiscountRate discountRate : this.discountRates) {
            int newDiscountRate = discountRate.Get(drive);
            resultRate = Math.max(resultRate, newDiscountRate);
        }
        return resultRate;
    }
}
