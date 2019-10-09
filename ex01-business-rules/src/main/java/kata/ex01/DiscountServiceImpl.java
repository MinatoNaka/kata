package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.rule.DiscountRule;

import java.util.List;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    private List<DiscountRule> rules;

    public DiscountServiceImpl(List<DiscountRule> rules) {
        this.rules = rules;
    }

    @Override
    public long calc(HighwayDrive drive) {
        int discountRate = 0;
        for (DiscountRule rule : this.rules) {
            int newDiscountRate = rule.getDiscountRate(drive);
            discountRate = Math.max(discountRate, newDiscountRate);
        }
        return discountRate;
    }
}
