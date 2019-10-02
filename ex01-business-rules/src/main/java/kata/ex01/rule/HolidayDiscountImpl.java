package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;

public class HolidayDiscountImpl implements DiscountRule {
    @Override
    public int getDiscountRate(HighwayDrive drive) {
        return 0;
    }
}
